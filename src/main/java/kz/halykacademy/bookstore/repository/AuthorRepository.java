package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Author repository.
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Query("SELECT a FROM Author a " +
            "LEFT JOIN a.books b LEFT JOIN b.genres g " +
            "WHERE " +
            "((:fullName IS NULL OR LOWER(concat(a.name, ' ', a.patronymic, ' ', a.surname)) LIKE LOWER(concat('%', :fullName, '%'))) AND ((:genres) IS NULL OR g.name IN :genres)) OR " +
            "((:fullName IS NULL OR LOWER(concat(a.surname, ' ', a.name, ' ', a.patronymic)) LIKE LOWER(concat('%', :fullName, '%'))) AND ((:genres) IS NULL OR g.name IN :genres)) OR " +
            "((:fullName IS NULL OR LOWER(concat(a.name, ' ', a.surname)) LIKE LOWER(concat('%', :fullName, '%'))) AND ((:genres) IS NULL OR g.name IN :genres)) OR " +
            "((:fullName IS NULL OR LOWER(concat(a.surname, ' ', a.name)) LIKE LOWER(concat('%', :fullName, '%'))) AND ((:genres) IS NULL OR g.name IN :genres)) " +
            "GROUP BY a ORDER BY COUNT(DISTINCT g.name) DESC")
    Page<Author> findByFullName(@Param("fullName") String fullName, @Param("genres") List<String> genres, Pageable pageable);

    Optional<List<Author>> findAuthorsByIdIn(List<Long> idList);

    boolean existsBySurnameAndNameAndPatronymicAndDateOfBirth(
            String surname,
            String name,
            String patronymic,
            LocalDate dateOfBirth
    );
}
