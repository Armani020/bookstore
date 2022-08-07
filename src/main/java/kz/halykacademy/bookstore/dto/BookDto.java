package kz.halykacademy.bookstore.dto;

import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

public enum BookDto {
    ;

    public enum Request {
        ;

        @Data
        public static class Create implements Price, Name, NumberOfPages, ReleaseYear, AuthorIdList, GenreIdList, PublisherId {
            Integer price;
            String name;
            Integer numberOfPages;
            LocalDate releaseYear;
            List<Long> authorIdList;
            List<Long> genreIdList;
            Long publisherId;
        }

        @Value
        public static class Update implements Price, Name, NumberOfPages, ReleaseYear {
            Integer price;
            String name;
            Integer numberOfPages;
            LocalDate releaseYear;
        }

        @Data
        public static class AuthorIds implements Id {
            Long id;
        }

        @Data
        public static class GenreIds implements Id {
            Long id;
        }

    }

    public enum Response {
        ;

        @Data
        public static class Created implements Id, Price, Name, NumberOfPages, ReleaseYear {
            Long id;
            Integer price;
            String name;
            Integer numberOfPages;
            LocalDate releaseYear;
        }

        @Data
        public static class All implements Id, Price, Name, NumberOfPages, ReleaseYear, Authors, Genres, Publisher {
            Long id;
            Integer price;
            String name;
            Integer numberOfPages;
            LocalDate releaseYear;
            List<AuthorDto.Response.Slim> authors;
            List<GenreDto.Response.Slim> genres;
            PublisherDto.Response.Slim publisher;
        }

        @Data
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
        @NotBlank String getName();
    }

    private interface NumberOfPages {
        @Positive Integer getNumberOfPages();
    }

    private interface ReleaseYear {
        LocalDate getReleaseYear();
    }

    private interface Authors {
        List<AuthorDto.Response.Slim> getAuthors();
    }

    private interface AuthorIdList {
        @NotNull List<Long> getAuthorIdList();
    }

    private interface Genres {
        List<GenreDto.Response.Slim> getGenres();
    }

    private interface GenreIdList {
        @NotNull List<Long> getGenreIdList();
    }

    private interface Publisher {
        @NotNull PublisherDto.Response.Slim getPublisher();
    }

    private interface PublisherId {
        @NotNull @Positive Long getPublisherId();
    }
}
