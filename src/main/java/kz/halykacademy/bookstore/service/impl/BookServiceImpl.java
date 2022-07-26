package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.BookDto.*;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.mapper.MapStructMapper;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.PublisherRepository;
import kz.halykacademy.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    private final MapStructMapper mapper;

    @Autowired
    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository, MapStructMapper mapper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.mapper = mapper;
    }

    @Override
    public Response.All save(final Request.Create request) {
        Book bookToDb = mapper.toBook(request);
        bookToDb.setAuthors(new HashSet<>());
        for (Request.AuthorIds authorIds : request.getAuthorIds()) {
            Author author = authorRepository.findById(authorIds.getId()).orElseThrow(
                    () -> new IllegalArgumentException("Author not found. ID: " + authorIds.getId()));
            bookToDb.addAuthor(author);
        }
        Publisher publisher = publisherRepository.findById(request.getPublisherId()).orElseThrow(
                () -> new IllegalArgumentException("Publisher not found. ID: " + request.getPublisherId()));
        bookToDb.addPublisher(publisher);
//        Book book = authorRepository.findById(authorId).map(author -> {
//            author.addBook(bookToDb);
//            return bookRepository.save(bookToDb);
//        }).orElseThrow(
//                () -> new IllegalArgumentException("Author not found. ID: " + authorId)
//        );
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
    public List<Response.All> findAll(final String name, final String genres) {
        if (genres.equals("null")) {
            return mapper.toBookDtoResponseAll(bookRepository.findBookByNameContainingIgnoreCase(name));
        }
        String[] genresArray = genres.split(",");
        List<String> genresList = new ArrayList<>();
        Collections.addAll(genresList, genresArray);
        return mapper.toBookDtoResponseAll(
                bookRepository.findByNameAndGenres(name, genresList)
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
}
