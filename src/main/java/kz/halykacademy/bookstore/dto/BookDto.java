package kz.halykacademy.bookstore.dto;

import kz.halykacademy.bookstore.entity.Author;
import lombok.Value;

import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

public enum BookDto {
    ;

    private interface Id {
        @Positive Long getId();
    }

    private interface Price {
        @Positive Integer getPrice();
    }

    private interface Name {
        String getName();
    }

    private interface NumberOfPages {
        @Positive Integer getNumberOfPages();
    }

    private interface ReleaseYear {
        Date getReleaseYear();
    }

    private interface Authors {
        List<AuthorDto.Response.Slim> getAuthors();
    }


    public enum Request {
        ;

        @Value
        public static class Create implements Price, Name, NumberOfPages, ReleaseYear {
            Integer price;
            String name;
            Integer numberOfPages;
            Date releaseYear;
        }

        @Value
        public static class Update implements Price, Name, NumberOfPages, ReleaseYear {
            Integer price;
            String name;
            Integer numberOfPages;
            Date releaseYear;
        }
    }

    public enum Response {
        ;

        @Value
        public static class Created implements Id, Price, Name, NumberOfPages, ReleaseYear {
            Long id;
            Integer price;
            String name;
            Integer numberOfPages;
            Date releaseYear;
        }

        @Value
        public static class All implements Id, Price, Name, NumberOfPages, ReleaseYear, Authors {
            Long id;
            Integer price;
            String name;
            Integer numberOfPages;
            Date releaseYear;
            List<AuthorDto.Response.Slim> authors;
        }

        @Value
        public static class Slim implements Id, Name {
            Long id;
            String name;
        }
    }

}
