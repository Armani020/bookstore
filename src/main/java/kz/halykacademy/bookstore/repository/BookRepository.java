package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Book repository.
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findBookByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);
}
