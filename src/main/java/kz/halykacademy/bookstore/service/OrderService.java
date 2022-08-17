package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.OrderDto.Request;
import kz.halykacademy.bookstore.dto.OrderDto.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service for working with Order.
 */
public interface OrderService {

    Response.Created save(Request.Create request);

    Response.All update(Long id, Request.Update request);

    Response.All updateStatus(Long id, Request.UpdateStatus request);

    Response.All find(Long id);

    Page<Response.All> findAll(String login, Pageable pageable);

    void delete(Long id);
}
