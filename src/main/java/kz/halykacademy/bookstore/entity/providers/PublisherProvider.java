package kz.halykacademy.bookstore.entity.providers;

import kz.halykacademy.bookstore.entity.Publisher;

import java.util.List;

public interface PublisherProvider {

    /**
     * Get all publishers
     *
     * @return list of publishers
     */
    List<Publisher> getAll();
}
