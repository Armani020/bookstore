package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.BookDto.Request;
import kz.halykacademy.bookstore.dto.BookDto.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service for working with Book.
 */
public interface BookService {

    Response.All save(Request.Create request);

    Response.Slim update(Long id, Request.Update request);

    Response.All find(Long id);

    Page<Response.All> findAll(String name, String genres, Pageable pageable);

    void deleteAuthorFromBook(Long bookId, Long authorId);

    void delete(Long Id);
}
