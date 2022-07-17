package kz.halykacademy.bookstore.dto;

import javax.validation.constraints.Positive;

public enum PublisherDto {
    ;

    private interface Id {
        @Positive Long getId();
    }

    private interface Name {
        String getName();
    }
}
