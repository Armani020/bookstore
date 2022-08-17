package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Order repository.
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    Page<Order> findOrdersByUserLogin(String login, Pageable pageable);
}
