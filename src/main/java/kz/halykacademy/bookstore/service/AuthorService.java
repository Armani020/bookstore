package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.AuthorDto.*;

import java.util.List;

/**
 * Service for working with Author.
 */
public interface AuthorService {

    Response.Created save(Request.Create request);

    Response.Slim update(Long id, Request.Update request);

    Response.Slim find(Long id);

    List<Response.All> findAll(String surname, String name, String patronymic);

    void delete(Long id);
}
