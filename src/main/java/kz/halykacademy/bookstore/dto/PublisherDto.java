package kz.halykacademy.bookstore.dto;

import lombok.Data;

import javax.validation.constraints.Positive;
import java.util.List;

public enum PublisherDto {
    ;

    public enum Request {
        ;

        @Data
        public static class Create implements Name {
            String name;
        }

        @Data
        public static class Update implements Name {
            String name;
        }
    }

    public enum Response {
        ;

        @Data
        public static class Created implements Id, Name {
            Long id;
            String name;
        }

        @Data
        public static class All implements Id, Name, Books {
            Long id;
            String name;
            List<BookDto.Response.Slim> books;
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

    private interface Name {
        String getName();
    }

    private interface Books {
        List<BookDto.Response.Slim> getBooks();
    }
}
