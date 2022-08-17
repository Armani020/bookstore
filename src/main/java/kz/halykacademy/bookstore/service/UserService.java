package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.UserDto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service for working with User.
 */
public interface UserService {

    Response.Created save(Request.Create request);

    Response.Slim update(Long id, Request.Update request);

    Response.Slim updateStatus(Long id, Request.UpdateStatus request);

    Response.All find(Long id);

    Page<Response.Slim> findAll(String name, Pageable pageable);

    void delete(Long id);
}
