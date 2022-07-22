package kz.halykacademy.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Positive;
import java.util.Set;

public enum PublisherDto {
    ;

    public enum Request {
        ;

        @Value
        @AllArgsConstructor
        public static class Create implements Name {
            String name;
        }

        @Value
        public static class Update implements Name {
            String name;
        }
    }

    public enum Response {
        ;

        @Value
        public static class Created implements Id, Name {
            Long id;
            String name;
        }

        @Value
        public static class All implements Id, Name, Books {
            Long id;
            String name;
            Set<BookDto.Response.Slim> books;
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

    private interface Name {
        String getName();
    }

    private interface Books {
        Set<BookDto.Response.Slim> getBooks();
    }
}
