package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Author repository.
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Query("SELECT u FROM Author u WHERE " +
            "(LOWER(concat(u.surname, ' ', u.name, ' ', u.patronymic)) LIKE LOWER(concat('%', :fullName, '%'))) OR " +
            "(LOWER(concat(u.name, ' ', u.surname)) LIKE LOWER(concat('%', :fullName, '%'))) OR " +
            "(LOWER(concat(u.surname, ' ', u.name)) LIKE LOWER(concat('%', :fullName, '%')))")
    List<Author> findByFullName(@Param("fullName") String fullName);

    boolean existsBySurnameAndNameAndPatronymicAndDateOfBirth(
            String surname,
            String name,
            String patronymic,
            Date dateOfBirth
    );
}
