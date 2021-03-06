package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.BookDto.Request;
import kz.halykacademy.bookstore.dto.BookDto.Response;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.mapper.MapStructMapper;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.repository.PublisherRepository;
import kz.halykacademy.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;
    private final MapStructMapper mapper;

    @Autowired
    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, GenreRepository genreRepository, PublisherRepository publisherRepository, MapStructMapper mapper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.publisherRepository = publisherRepository;
        this.mapper = mapper;
    }

    @Override
    public Response.All save(final Request.Create request) {
        Book bookToDb = mapper.toBook(request);
        bookToDb.setAuthors(new HashSet<>());
        bookToDb.setGenres(new HashSet<>());
        bookToDb.setOrders(new HashSet<>());
        for (Request.AuthorIds authorIds : request.getAuthorIds()) {
            Author author = authorRepository.findById(authorIds.getId()).orElseThrow(
                    () -> new IllegalArgumentException("Author not found. ID: " + authorIds.getId()));
            bookToDb.addAuthor(author);
        }
        for (Request.GenreIds genreIds : request.getGenreIds()) {
            Genre genre = genreRepository.findById(genreIds.getId()).orElseThrow(
                    () -> new IllegalArgumentException("Genre not found. ID: " + genreIds.getId()));
            bookToDb.addGenre(genre);
        }
        Publisher publisher = publisherRepository.findById(request.getPublisherId()).orElseThrow(
                () -> new IllegalArgumentException("Publisher not found. ID: " + request.getPublisherId()));
        bookToDb.addPublisher(publisher);
        return mapper.toBookDtoResponseAll(bookRepository.save(bookToDb));
    }

    @Override
    public Response.Slim update(final Long id, final Request.Update request) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Book not found. id: " + id);
        }
        Book bookToDb = mapper.toBook(request);
        bookToDb.setId(id);
        return mapper.toBookDtoResponseSlim(bookRepository.save(bookToDb));
    }

    @Override
    public Response.All find(final Long id) {
        return mapper.toBookDtoResponseAll(
                bookRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("Book not found. id: " + id))
        );
    }

    @Override
    public Page<Response.All> findAll(final String name, final String genres, final Pageable pageable) {
        Page<Book> booksPage = bookRepository.findByNameAndGenres(name, parseGenres(genres), pageable);
        return new PageImpl<>(
                mapper.toBookDtoResponseAll(booksPage.getContent()), pageable, booksPage.getTotalElements()
        );
    }

    @Override
    public void deleteAuthorFromBook(final Long bookId, final Long authorId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new IllegalArgumentException("Book not found. id: " + bookId));
        if (!authorRepository.existsById(authorId)) {
            throw new IllegalArgumentException("Author not found. id: " + authorId);
        }
        book.removeAuthor(authorId);
        bookRepository.save(book);
    }

    @Override
    public void delete(final Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Book not found. ID: " + id));
        for (Author author : book.getAuthors()) {
            book.removeAuthor(author.getId());
        }
        bookRepository.save(book);
        bookRepository.deleteById(id);
    }

    private List<String> parseGenres(String genres) {
        List<String> genresList = null;
        if (genres != null) {
            String[] genresArray = genres.split(",");
            genresList = new ArrayList<>();
            Collections.addAll(genresList, genresArray);
        }
        return genresList;
    }
}
