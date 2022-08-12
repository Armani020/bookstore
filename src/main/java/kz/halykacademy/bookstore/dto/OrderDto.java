package kz.halykacademy.bookstore.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

public enum OrderDto {
    ;

    public enum Request {
        ;

        @Data
        public static class Create implements BookIdList {
            List<Long> bookIdList;
        }

        @Data
        public static class Update implements Status {
            String status;
        }
    }

    public enum Response {
        ;

        @Data
        public static class Created implements Id, Status, CreatedAt {
            Long id;
            String status;
            LocalDateTime createdAt;
        }

        @Data
        public static class All implements Id, Status, CreatedAt, Books {
            Long id;
            String status;
            LocalDateTime createdAt;
            List<BookDto.Response.Slim> books;
        }

        @Data
        public static class Slim implements Id, Status {
            Long id;
            String status;
        }
    }

    private interface Id {
        @Positive Long getId();
    }

    private interface Status {
        @NotBlank String getStatus();
    }

    private interface CreatedAt {
        LocalDateTime getCreatedAt();
    }

    private interface Books {
        List<BookDto.Response.Slim> getBooks();
    }

//    private interface User {
//        @NotNull PublisherDto.Response.Slim getUser();
//    }

    private interface BookIdList {
        @NotNull List<Long> getBookIdList();
    }
}
