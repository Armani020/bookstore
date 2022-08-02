package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.AuthorDto.Request;
import kz.halykacademy.bookstore.dto.AuthorDto.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service for working with Author.
 */
public interface AuthorService {

    Response.Created save(Request.Create request);

    Response.Slim update(Long id, Request.Update request);

    Response.All find(Long id);

    Page<Response.All> findAll(String fullName, String genres, Pageable pageable);

    void delete(Long id);
}
