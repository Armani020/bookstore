package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Order repository.
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
