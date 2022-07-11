package kz.halykacademy.bookstore.entity.providers;

import kz.halykacademy.bookstore.entity.Author;

import java.util.List;

public interface AuthorProvider {

    /**
     * Get all authors
     *
     * @return list of authors
     */
    List<Author> getAll();
}
