package kz.halykacademy.bookstore.dto;

import lombok.Value;

import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

public enum AuthorDto {
    ;

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
        Date getDateOfBirth();
    }

    private interface Books {
        List<BookDto.Response.Slim> getBooks();
    }

    public enum Request {
        ;

        @Value
        public static class Create implements Surname, Name, Patronymic, DateOfBirth {
            String surname;
            String name;
            String patronymic;
            Date dateOfBirth;
        }

        @Value
        public static class Update implements Surname, Name, Patronymic, DateOfBirth {
            String surname;
            String name;
            String patronymic;
            Date dateOfBirth;
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
            Date dateOfBirth;
        }

        @Value
        public static class All implements Id, Surname, Name, Patronymic, DateOfBirth, Books {
            Long id;
            String surname;
            String name;
            String patronymic;
            Date dateOfBirth;
            List<BookDto.Response.Slim> books;
        }

        @Value
        public static class Slim implements Id, Surname, Name, Patronymic, DateOfBirth {
            Long id;
            String surname;
            String name;
            String patronymic;
            Date dateOfBirth;
        }
    }
}
