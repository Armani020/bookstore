package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.entity.Book;

import java.util.List;

public interface BookService {

    Book save(Book payload);

    List<Book> findAll();
}
