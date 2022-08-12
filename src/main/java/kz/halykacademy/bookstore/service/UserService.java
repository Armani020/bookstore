package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.UserDto.*;

import java.util.List;

/**
 * Service for working with User.
 */
public interface UserService {

    Response.Created save(Request.Create request);

    Response.Slim update(Long id, Request.Update request);

    Response.All find(Long id);

    List<Response.All> findAll(String name);

    void delete(Long id);
}
