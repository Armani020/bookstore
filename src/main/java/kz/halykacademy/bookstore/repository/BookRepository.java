package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Book;
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
            "WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%')) AND g.name in :genres")
    List<Book> findByNameAndGenres(@Param("name") String name, @Param("genres") List<String> genres);

    boolean existsByName(String name);
}
