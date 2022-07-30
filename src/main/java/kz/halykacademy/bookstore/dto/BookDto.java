package kz.halykacademy.bookstore.dto;

import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public enum BookDto {
    ;

    public enum Request {
        ;

        @Value
        public static class Create implements Price, Name, NumberOfPages, ReleaseYear, AuthorIdList, GenreIdList, PublisherId {
            Integer price;
            String name;
            Integer numberOfPages;
            LocalDate releaseYear;
            List<AuthorIds> authorIds;
            List<GenreIds> genreIds;
            Long publisherId;
        }

        @Value
        public static class Update implements Price, Name, NumberOfPages, ReleaseYear {
            Integer price;
            String name;
            Integer numberOfPages;
            LocalDate releaseYear;
        }

        @NoArgsConstructor
        public static class AuthorIds implements Id {
            Long id;

            @Override
            public Long getId() {
                return this.id;
            }
        }

        @NoArgsConstructor
        public static class GenreIds implements Id {
            Long id;

            @Override
            public Long getId() {
                return this.id;
            }
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
            LocalDate releaseYear;
        }

        @Value
        public static class All implements Id, Price, Name, NumberOfPages, ReleaseYear, Authors, Genres, Publisher {
            Long id;
            Integer price;
            String name;
            Integer numberOfPages;
            LocalDate releaseYear;
            Set<AuthorDto.Response.Slim> authors;
            Set<GenreDto.Response.Slim> genres;
            PublisherDto.Response.Slim publisher;
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
        @NotBlank String getName();
    }

    private interface NumberOfPages {
        @Positive Integer getNumberOfPages();
    }

    private interface ReleaseYear {
        LocalDate getReleaseYear();
    }

    private interface Authors {
        Set<AuthorDto.Response.Slim> getAuthors();
    }

    private interface AuthorIdList {
        @NotNull List<Request.AuthorIds> getAuthorIds();
    }

    private interface Genres {
        Set<GenreDto.Response.Slim> getGenres();
    }

    private interface GenreIdList {
        @NotNull List<Request.GenreIds> getGenreIds();
    }

    private interface Publisher {
        @NotNull PublisherDto.Response.Slim getPublisher();
    }

    private interface PublisherId {
        @NotNull @Positive Long getPublisherId();
    }
}
