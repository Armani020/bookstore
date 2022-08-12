package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.OrderDto.*;

import java.util.List;

/**
 * Service for working with Order.
 */
public interface OrderService {

    Response.Created save(Request.Create request);

    Response.Slim update(Long id, Request.Update request);

    Response.All find(Long id);

    List<Response.All> findAll(String name);

    void delete(Long id);
}
