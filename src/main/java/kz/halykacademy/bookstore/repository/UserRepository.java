package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User repository.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByLogin(String login);

    Optional<User> findByLogin(String login);
}
