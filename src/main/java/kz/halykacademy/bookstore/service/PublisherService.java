package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.PublisherDto.*;

import java.util.List;

public interface PublisherService {

    Response.Created save(Request.Create request);

    Response.Slim update(Long id, Request.Update request);

    Response.All find(Long id);

    List<Response.All> findAll(String name);

    void delete(Long id);
}
