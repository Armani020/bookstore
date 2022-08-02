package kz.halykacademy.bookstore.dto;

import lombok.Value;

import javax.validation.constraints.Positive;
import java.util.List;

public enum PublisherDto {
    ;

    public enum Request {
        ;

        public static class Create implements Name {
            String name;

            public Create(String name) {
                this.name = name;
            }

            public Create() {
            }

            @Override
            public String getName() {
                return this.name;
            }
        }

        public static class Update implements Name {
            String name;

            public Update(String name) {
                this.name = name;
            }

            public Update() {
            }

            @Override
            public String getName() {
                return this.name;
            }
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
            List<BookDto.Response.Slim> books;
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
        List<BookDto.Response.Slim> getBooks();
    }
}
