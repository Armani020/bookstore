package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.AuthorDto;
import kz.halykacademy.bookstore.dto.BookDto;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/")
    public ResponseEntity<BookDto.Response.All> save(@Valid @RequestBody BookDto.Request.Create request) {
        return new ResponseEntity<>(
                bookService.save(request),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto.Response.All> getBook(@PathVariable Long id) {
        return new ResponseEntity<>(
                bookService.find(id),
                HttpStatus.OK
        );
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDto.Response.All>> getBooks(@RequestParam(defaultValue = "") String name) {
        return new ResponseEntity<>(
                bookService.findAll(name),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto.Response.Slim> updateBook(@PathVariable Long id, @Valid @RequestBody BookDto.Request.Update request) {
        return new ResponseEntity<>(
                bookService.update(id, request),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/book/{bookId}/authors/{authorId}")
    public ResponseEntity<?> deleteAuthorsFromBook(@PathVariable Long bookId, @PathVariable Long authorId) {
        bookService.deleteAuthorFromBook(bookId, authorId);
        return new ResponseEntity<>(
                "Author deleted from this book. authorId: " + authorId,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(
                "Book deleted. id: " + id,
                HttpStatus.OK
        );
    }
}
