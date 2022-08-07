package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.BookDto.Request;
import kz.halykacademy.bookstore.dto.BookDto.Response;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.mapper.BookMapperImpl;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.repository.PublisherRepository;
import kz.halykacademy.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Book service.
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final PublisherRepository publisherRepository;
    private final BookMapperImpl bookMapper;

    @Override
    public Response.All save(final Request.Create request) {
        Book bookToDb = bookMapper.toEntity(request);
        request.getAuthorIdList().forEach(id -> bookToDb.addAuthor(authorRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Author not found. ID: " + id))));
        request.getGenreIdList().forEach(id -> bookToDb.addGenre(genreRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Genre not found. ID: " + id))));
        bookToDb.addPublisher(publisherRepository.findById(request.getPublisherId()).orElseThrow(
                () -> new IllegalArgumentException("Publisher not found. ID: " + request.getPublisherId())));
        return bookMapper.toDtoResponseAll(bookRepository.save(bookToDb));
    }

    @Override
    public Response.Slim update(final Long id, final Request.Update request) {
        Book bookFromDb = bookRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Book not found. id: " + id));
        bookFromDb.setPrice(request.getPrice());
        bookFromDb.setName(request.getName());
        bookFromDb.setNumberOfPages(request.getNumberOfPages());
        bookFromDb.setReleaseYear(request.getReleaseYear());
        return bookMapper.toDtoResponseSlim(bookRepository.save(bookFromDb));
    }

    @Override
    public Response.All find(final Long id) {
        return bookMapper.toDtoResponseAll(
                bookRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("Book not found. id: " + id))
        );
    }

    @Override
    public Page<Response.All> findAll(final String name, final String genres, final Pageable pageable) {
        Page<Book> booksPage = bookRepository.findByNameAndGenres(name, parseGenres(genres), pageable);
        return new PageImpl<>(
                bookMapper.toDtoResponseAll(booksPage.getContent()), pageable, booksPage.getTotalElements()
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
