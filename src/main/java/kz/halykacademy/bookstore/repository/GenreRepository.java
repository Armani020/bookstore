package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    boolean existsByNameIgnoreCase(String name);

    List<Genre> findGenresByNameContainingIgnoreCase(String name);

    @Query("SELECT DISTINCT g FROM Genre g " +
            "JOIN g.books gb " +
            "JOIN gb.authors gba " +
            "WHERE gba.id = :id")
    List<Genre> findGenresByAuthor(@Param("id") Long id);
}
