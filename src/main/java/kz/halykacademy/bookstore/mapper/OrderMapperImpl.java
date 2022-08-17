package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.BookDto;
import kz.halykacademy.bookstore.dto.OrderDto.Request;
import kz.halykacademy.bookstore.dto.OrderDto.Response;
import kz.halykacademy.bookstore.entity.Order;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.entity.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class OrderMapperImpl {

    private final BookMapperImpl bookMapper;

    @Autowired
    public OrderMapperImpl(BookMapperImpl bookMapper) {
        this.bookMapper = bookMapper;
    }

    public Order toEntity(Request.Create orderDto) {
        if (orderDto == null) return null;
        Order.OrderBuilder order = Order.builder();
        order.status(OrderStatus.CREATED);
        order.createdAt(LocalDateTime.now());
        order.books(new HashSet<>());
        order.user(new User());
        return order.build();
    }

    public Response.Slim toDtoResponseSlim(Order order) {
        Response.Slim slim = new Response.Slim();
        if (order == null) return null;
        if (order.getId() != null) slim.setId(order.getId());
        if (order.getStatus() != null) slim.setStatus(order.getStatus().name());
        return slim;
    }

    public Response.Created toDtoResponseCreated(Order order) {
        Response.Created created = new Response.Created();
        if (order == null) return null;
        if (order.getId() != null) created.setId(order.getId());
        if (order.getStatus() != null) created.setStatus(order.getStatus().name());
        if (order.getCreatedAt() != null) created.setCreatedAt(order.getCreatedAt());
        return created;
    }

    public Response.All toDtoResponseAll(Order order) {
        Response.All created = new Response.All();
        if (order == null) return null;
        if (order.getId() != null) created.setId(order.getId());
        if (order.getStatus() != null) created.setStatus(order.getStatus().name());
        if (order.getCreatedAt() != null) created.setCreatedAt(order.getCreatedAt());

        List<BookDto.Response.Slim> books = new ArrayList<>(order.getBooks().size());
        order.getBooks().forEach(book -> books.add(bookMapper.toDtoResponseSlim(book)));
        created.setBooks(books);

        return created;
    }

    public List<Response.All> toDtoResponseAll(List<Order> orders) {
        if (orders == null) return null;
        List<Response.All> list = new ArrayList<>(orders.size());
        orders.forEach(order -> list.add(toDtoResponseAll(order)));
        return list;
    }
}
