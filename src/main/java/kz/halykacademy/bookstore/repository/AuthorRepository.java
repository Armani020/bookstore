package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Author repository.
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Query("SELECT u FROM Author u WHERE " +
            "(:fullName IS NULL OR LOWER(concat(u.surname, ' ', u.name, ' ', u.patronymic)) LIKE LOWER(concat('%', :fullName, '%'))) OR " +
            "(:fullName IS NULL OR LOWER(concat(u.name, ' ', u.surname)) LIKE LOWER(concat('%', :fullName, '%'))) OR " +
            "(:fullName IS NULL OR LOWER(concat(u.surname, ' ', u.name)) LIKE LOWER(concat('%', :fullName, '%')))")
    List<Author> findByFullName(@Param("fullName") String fullName);

    @Query("SELECT author FROM Author author " +
            "JOIN author.books author_books " +
            "JOIN author_books.genres g " +
            "WHERE g.name in :genres")
    List<Author> findByGenre(@Param("genres") List<String> genres); // where g.name in :genres (List<String>)

    boolean existsBySurnameAndNameAndPatronymicAndDateOfBirth(
            String surname,
            String name,
            String patronymic,
            LocalDate dateOfBirth
    );
}
