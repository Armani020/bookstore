package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.BookDto.*;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.mapper.MapStructMapper;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final MapStructMapper mapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, MapStructMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    @Override
    public Response.Created save(final Request.Create request) {
        if (bookRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Book with this data already exists: " + request);
        }
        return mapper.bookToBookDtoResponseCreated(
                bookRepository.save(mapper.bookDtoRequestCreateToBook(request))
        );
    }

    @Override
    public Response.Slim update(final Long id, final Request.Update request) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Book not found. id: " + id);
        }
        Book bookToDb = mapper.bookDtoRequestUpdateToBook(request);
        bookToDb.setId(id);
        return mapper.bookToBookDtoResponseSlim(bookRepository.save(bookToDb));
    }

    @Override
    public Response.All find(final Long id) {
        return mapper.bookToBookDtoResponseAll(
                bookRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("Book not found. id: " + id)
                )
        );
    }

    @Override
    public List<Response.All> findAll(final String name) {
        return mapper.booksToBookDtoResponseAll(
                bookRepository.findBookByNameContainingIgnoreCase(name)
        );
    }

    @Override
    public void delete(final Long id) {
        bookRepository.deleteById(id);
    }
}
