package kz.halykacademy.bookstore.entity.providers;

import kz.halykacademy.bookstore.entity.Book;

import java.util.List;

public interface BookProvider {

    /**
     * Get all books
     *
     * @return list of books
     */
    List<Book> getAll();
}
