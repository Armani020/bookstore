package kz.halykacademy.bookstore.entity.providers.impl;

import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.entity.providers.PublisherProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PublisherProviderImpl implements PublisherProvider {

    private static final List<Publisher> publishers = new ArrayList<>(Arrays.asList(
            new Publisher(),
            new Publisher(),
            new Publisher()
    ));

    @Override
    public List<Publisher> getAll() {
        return null;
    }
}
