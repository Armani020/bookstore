package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public @ResponseBody Book save(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping
    public @ResponseBody List<Book> findAllBooks() {
        return bookService.findAll();
    }
}
