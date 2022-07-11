package kz.halykacademy.bookstore.entity.providers.impl;

import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.entity.providers.BookProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BookProviderImpl implements BookProvider {

    private static final List<Book> books = new ArrayList<>(Arrays.asList(
            new Book(
                    1L,
                    300,
                    "Voina I Mir",
                    10000,
                    LocalDate.of(1867, 1, 1),
                    new Publisher(),
                    Collections.emptyList()
            ),
            new Book(
                    1L,
                    300,
                    "Robinson Crusoe",
                    10000,
                    LocalDate.of(1719, 1, 1),
                    new Publisher(),
                    Collections.emptyList()
            ),
            new Book(
                    1L,
                    300,
                    "Voina I Mir",
                    10000,
                    LocalDate.of(1867, 1, 1),
                    new Publisher(),
                    Collections.emptyList()
            )
    ));

    @Override
    public List<Book> getAll() {
        return books;
    }
}
