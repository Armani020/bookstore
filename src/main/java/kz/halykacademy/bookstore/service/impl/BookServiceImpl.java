package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.BookDto.*;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.mapper.MapStructMapper;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final MapStructMapper mapper;

    @Autowired
    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, MapStructMapper mapper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    @Override
    public Response.All save(final Request.Create request) {

        Book book = mapper.toBook(request);
        book.setAuthors(new HashSet<>());

        for (Long id : request.getAuthorId()) {
            Author authorFromDb = authorRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("Author not found. id: " + id)
            );
            book.getAuthors().add(authorFromDb);
        }

        return mapper.toBookDtoResponseAll(bookRepository.save(book));
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
                        () -> new IllegalArgumentException("Book not found. id: " + id)
                )
        );
    }

    @Override
    public List<Response.All> findAll(final String name) {
        return mapper.toBookDtoResponseAll(
                bookRepository.findBookByNameContainingIgnoreCase(name)
        );
    }

    @Override
    public void deleteAuthorFromBook(final Long bookId, final Long authorId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new IllegalArgumentException("Book not found. id: " + bookId)
        );
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new IllegalArgumentException("Author not found. id: " + authorId)
        );
        book.getAuthors().remove(author);
        bookRepository.save(book);
    }

    @Override
    public void delete(final Long id) {
        bookRepository.deleteById(id);
    }
}
