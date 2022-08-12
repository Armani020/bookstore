package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.OrderDto;
import kz.halykacademy.bookstore.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Order service.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public OrderDto.Response.Created save(OrderDto.Request.Create request) {
        return null;
    }

    @Override
    public OrderDto.Response.Slim update(Long id, OrderDto.Request.Update request) {
        return null;
    }

    @Override
    public OrderDto.Response.All find(Long id) {
        return null;
    }

    @Override
    public List<OrderDto.Response.All> findAll(String name) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
