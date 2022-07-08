package kz.halykacademy.bookstore.entity;

import java.time.LocalDate;
import java.util.List;

public class Book {

    private Long id;

    private Integer price;

    private String name;

    private Integer numberOfPages;

    private LocalDate releaseYear;

    private Publisher publisher;

    private List<Author> authors;
}
