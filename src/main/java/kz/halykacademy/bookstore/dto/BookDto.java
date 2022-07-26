package kz.halykacademy.bookstore.dto;

import lombok.Value;

import javax.validation.constraints.Positive;
import java.util.*;

public enum BookDto {
    ;

    public enum Request {
        ;

        @Value
        public static class Create implements Price, Name, NumberOfPages, ReleaseYear, AuthorIdList, PublisherId {
            Integer price;
            String name;
            Integer numberOfPages;
            Date releaseYear;
            List<AuthorIds> authorIds;
            Long publisherId;
        }

        @Value
        public static class Update implements Price, Name, NumberOfPages, ReleaseYear {
            Integer price;
            String name;
            Integer numberOfPages;
            Date releaseYear;
        }

        public static class AuthorIds implements Id {
            Long id;

            public AuthorIds() {
            }

            public AuthorIds(Long authorId) {
                this.id = authorId;
            }

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
            Date releaseYear;
        }

        @Value
        public static class All implements Id, Price, Name, NumberOfPages, ReleaseYear, Authors, Publisher {
            Long id;
            Integer price;
            String name;
            Integer numberOfPages;
            Date releaseYear;
            Set<AuthorDto.Response.Slim> authors;
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

    private interface Publisher {
        PublisherDto.Response.Slim getPublisher();
    }

    private interface AuthorIdList {
        List<Request.AuthorIds> getAuthorIds();
    }

    private interface PublisherId {
        @Positive Long getPublisherId();
    }
}
