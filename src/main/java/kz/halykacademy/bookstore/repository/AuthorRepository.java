package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Author repository.
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findAuthorsBySurnameContainingIgnoreCaseAndNameContainingIgnoreCaseAndPatronymicContainingIgnoreCase(
            String surname, String name, String patronymic
    );

    default List<Author> findByFullName(String surname, String name, String patronymic) {
        return findAuthorsBySurnameContainingIgnoreCaseAndNameContainingIgnoreCaseAndPatronymicContainingIgnoreCase(
                surname, name, patronymic
        );
    }

    boolean existsBySurnameAndNameAndPatronymicAndDateOfBirth(
            String surname,
            String name,
            String patronymic,
            Date dateOfBirth
    );
}
