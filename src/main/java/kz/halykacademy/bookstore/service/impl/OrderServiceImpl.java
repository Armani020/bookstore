package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.OrderDto.Request;
import kz.halykacademy.bookstore.dto.OrderDto.Response;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Order;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.entity.enums.OrderStatus;
import kz.halykacademy.bookstore.mapper.OrderMapperImpl;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.OrderRepository;
import kz.halykacademy.bookstore.repository.RoleRepository;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Order service.
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final OrderMapperImpl orderMapper;

    @Override
    public Response.Created save(final Request.Create request) {
        User user = userRepository.findByLogin(authService.getAuthInfo().getLogin()).orElseThrow(
                () -> new IllegalArgumentException("User not found!"));
        Order orderToDb = orderMapper.toEntity(request);
        orderToDb.setUser(user);
        request.getBookIdList().forEach(id -> orderToDb.addBook(bookRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Book not found. ID: " + id))));
        if (orderToDb.getBooks().stream().mapToInt(Book::getPrice).sum() > 10000) {
            throw new IllegalArgumentException("The order price is more than 10k!");
        }
        return orderMapper.toDtoResponseCreated(orderRepository.save(orderToDb));
    }

    @Override
    public Response.All update(final Long id, final Request.Update request) {
        User user = userRepository.findByLogin(authService.getAuthInfo().getLogin()).orElseThrow(
                () -> new IllegalArgumentException("User not found!"));
        Order orderFromDb = orderRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Order not found!"));
        if (orderFromDb.getUser().equals(user)) {
            orderFromDb.setBooks(new HashSet<>());
            request.getBookIdList().forEach(bookId -> orderFromDb.addBook(bookRepository.findById(bookId).orElseThrow(
                    () -> new IllegalArgumentException("Book not found. ID: " + bookId))));
            return orderMapper.toDtoResponseAll(orderRepository.save(orderFromDb));
        } else {
            throw new IllegalArgumentException("You don't have access");
        }
    }

    @Override
    public Response.All updateStatus(final Long id, final Request.UpdateStatus request) {
        User user = userRepository.findByLogin(authService.getAuthInfo().getLogin()).orElseThrow(
                () -> new IllegalArgumentException("User not found!"));
        Order orderFromDb = orderRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Order not found!"));
        if (user.getRoles().contains(roleRepository.findByName("ROLE_ADMIN"))) {
            orderFromDb.setStatus(OrderStatus.valueOf(request.getStatus()));
            return orderMapper.toDtoResponseAll(orderRepository.save(orderFromDb));
        } else {
            throw new IllegalArgumentException("You don't have access");
        }
    }

    @Override
    public Response.All find(final Long id) {
        User user = userRepository.findByLogin(authService.getAuthInfo().getLogin()).orElseThrow(
                () -> new IllegalArgumentException("User not found!"));
        Order orderFromDb = orderRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Order not found!"));
        if (!orderFromDb.getUser().equals(user) && !user.getRoles().contains(roleRepository.findByName("ROLE_ADMIN"))) {
            throw new IllegalArgumentException("You don't have access");
        }
        return orderMapper.toDtoResponseAll(orderFromDb);
    }

    @Override
    public Page<Response.All> findAll(final String login, Pageable pageable) {
        User user = userRepository.findByLogin(authService.getAuthInfo().getLogin()).orElseThrow(
                () -> new IllegalArgumentException("User not found!"));
        if (!user.getLogin().equals(login) && !user.getRoles().contains(roleRepository.findByName("ROLE_ADMIN"))) {
            throw new IllegalArgumentException("You don't have access");
        }
        Page<Order> ordersPage = orderRepository.findOrdersByUserLogin(user.getLogin(), pageable);
        return new PageImpl<>(
                orderMapper.toDtoResponseAll(ordersPage.getContent()), pageable, ordersPage.getTotalElements());
    }

    @Override
    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Order not found!");
        }
        orderRepository.deleteById(id);
    }
}
