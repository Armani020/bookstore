package kz.halykacademy.bookstore.entity.providers.impl;

import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.providers.AuthorProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AuthorProviderImpl implements AuthorProvider {

    private static final List<Author> authors = new ArrayList<>(Arrays.asList(
            new Author(
                    1L,
                    "Robert",
                    "Downey",
                    "Junior",
                    LocalDate.of(1965, 4, 4),
                    Collections.emptyList()
            ),
            new Author(
                    2L,
                    "Robert2",
                    "Downey2",
                    "Junior2",
                    LocalDate.of(1966, 5, 5),
                    Collections.emptyList()
            ),
            new Author(
                    3L,
                    "Robert3",
                    "Downey3",
                    "Junior3",
                    LocalDate.of(1967, 6, 6),
                    Collections.emptyList()
            )
    ));


    @Override
    public List<Author> getAll() {
        return authors;
    }
}
