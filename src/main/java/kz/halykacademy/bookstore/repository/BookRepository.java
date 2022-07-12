package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {


}
