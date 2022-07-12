package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.repository.BookRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book payload) {
        return bookRepository.save(payload);
    }

    @Override
    public List<Book> findAll() {
        return IterableUtils.toList(bookRepository.findAll());
    }
}
