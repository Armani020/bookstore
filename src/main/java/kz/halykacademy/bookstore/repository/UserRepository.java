package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User repository.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByLogin(String login);

    Optional<User> findByLogin(String login);

    @Query("SELECT u FROM User u WHERE " +
            "(:login IS NULL OR LOWER(u.login) LIKE LOWER(CONCAT('%', :login, '%')))")
    Page<User> findUsersByLogin(@Param("login") String login, Pageable pageable);
}
