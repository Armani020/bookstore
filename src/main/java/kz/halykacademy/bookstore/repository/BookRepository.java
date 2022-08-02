package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Book repository.
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findBookByNameContainingIgnoreCase(String name);

    @Query("SELECT b FROM Book b " +
            "JOIN b.genres g " +
            "WHERE (:name IS NULL OR LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND ((:genres) IS NULL OR g.name IN :genres) " +
            "GROUP BY b " +
            "ORDER BY COUNT(DISTINCT g.name) DESC")
    Page<Book> findByNameAndGenres(@Param("name") String name, @Param("genres") List<String> genres, Pageable pageable);

    boolean existsByName(String name);
}
