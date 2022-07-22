package kz.halykacademy.bookstore.dto;

import lombok.Value;

import javax.validation.constraints.Positive;
import java.util.*;

public enum BookDto {
    ;

    public enum Request {
        ;

        @Value
        public static class Create implements Price, Name, NumberOfPages, ReleaseYear, AuthorId {
            Integer price;
            String name;
            Integer numberOfPages;
            Date releaseYear;
            Long[] authorId;
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
            Set<AuthorDto.Response.Slim> authors;
        }

        @Value
        public static class Slim implements Id, Name {
            Long id;
            String name;
        }
    }

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
        Set<AuthorDto.Response.Slim> getAuthors();
    }

//    private interface AuthorsRequest {
//        List<AuthorDto.Request.Create> getAuthors();
//    }
//
//    private interface AuthorsEntity {
//        List<Author> getAuthors();
//    }

    private interface AuthorId {
        @Positive Long[] getAuthorId();
    }


}
