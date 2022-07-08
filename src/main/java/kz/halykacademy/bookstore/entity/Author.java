package kz.halykacademy.bookstore.entity;

import java.time.LocalDate;
import java.util.List;

public class Author {

    private Long id;

    private String surname;

    private String name;

    private String patronymic;

    private LocalDate dateOfBirth;

    private List<Book> writtenBooks;
}
