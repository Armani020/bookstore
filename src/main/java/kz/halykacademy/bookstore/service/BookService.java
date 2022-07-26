package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.BookDto.*;

import java.util.List;

/**
 * Service for working with Book.
 */
public interface BookService {

    Response.All save(Request.Create request);

    Response.Slim update(Long id, Request.Update request);

    Response.All find(Long id);

    List<Response.All> findAll(String name, String genres);

    void deleteAuthorFromBook(Long bookId, Long authorId);

    void delete(Long Id);
}
