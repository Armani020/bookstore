package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findBookByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);
}
