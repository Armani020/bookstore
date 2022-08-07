package kz.halykacademy.bookstore.dto;

import kz.halykacademy.bookstore.entity.Book;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public enum AuthorDto {
    ;

    public enum Request {
        ;

        @Value
        public static class Create implements Surname, Name, Patronymic, DateOfBirth {
            String surname;
            String name;
            String patronymic;
            LocalDate dateOfBirth;
        }

        @Value
        public static class Update implements Surname, Name, Patronymic, DateOfBirth {
            String surname;
            String name;
            String patronymic;
            LocalDate dateOfBirth;
        }
    }

    public enum Response {
        ;

        @Value
        public static class Created implements Id, Surname, Name, Patronymic, DateOfBirth {
            Long id;
            String surname;
            String name;
            String patronymic;
            LocalDate dateOfBirth;
        }

        @Data
        public static class All implements Id, Surname, Name, Patronymic, DateOfBirth, Books {
            Long id;
            String surname;
            String name;
            String patronymic;
            LocalDate dateOfBirth;
            List<BookDto.Response.Slim> books;
            List<GenreDto.Response.Slim> genres;
        }

        @Data
        public static class Slim implements Id, Surname, Name, Patronymic, DateOfBirth {
            Long id;
            String surname;
            String name;
            String patronymic;
            LocalDate dateOfBirth;
        }
    }

    private interface Id {
        @Positive Long getId();
    }

    private interface Surname {
        String getSurname();
    }

    private interface Name {
        String getName();
    }

    private interface Patronymic {
        String getPatronymic();
    }

    private interface DateOfBirth {
        LocalDate getDateOfBirth();
    }

    private interface Books {
        List<BookDto.Response.Slim> getBooks();
    }

    private interface Genres {
        Set<GenreDto.Response.Slim> getGenres();
    }

    private interface BooksEntity {
        List<Book> getBooks();
    }
}
