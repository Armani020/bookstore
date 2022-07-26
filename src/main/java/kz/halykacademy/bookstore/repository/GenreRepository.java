package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    boolean existsByNameIgnoreCase(String name);

    List<Genre> findGenresByNameContainingIgnoreCase(String name);
}
