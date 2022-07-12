package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {


}
