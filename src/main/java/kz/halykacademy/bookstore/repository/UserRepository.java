package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * User repository.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
