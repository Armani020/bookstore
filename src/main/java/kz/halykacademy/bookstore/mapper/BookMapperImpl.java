package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.AuthorDto;
import kz.halykacademy.bookstore.dto.BookDto;
import kz.halykacademy.bookstore.dto.BookDto.Request;
import kz.halykacademy.bookstore.dto.BookDto.Response;
import kz.halykacademy.bookstore.dto.GenreDto;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class BookMapperImpl {

    private final AuthorMapperImpl authorMapper;
    private final GenreMapperImpl genreMapper;
    private final PublisherMapperImpl publisherMapper;

    @Autowired
    public BookMapperImpl(AuthorMapperImpl authorMapper, GenreMapperImpl genreMapper, PublisherMapperImpl publisherMapper) {
        this.authorMapper = authorMapper;
        this.genreMapper = genreMapper;
        this.publisherMapper = publisherMapper;
    }

    public Book toEntity(Request.Create bookDto) {
        Book book = new Book();
        if (bookDto == null) return null;
        if (bookDto.getPrice() != null) book.setPrice(bookDto.getPrice());
        if (bookDto.getName() != null) book.setName(bookDto.getName());
        if (bookDto.getNumberOfPages() != null) book.setNumberOfPages(bookDto.getNumberOfPages());
        if (bookDto.getReleaseYear() != null) book.setReleaseYear(bookDto.getReleaseYear());
        book.setPublisher(new Publisher());
        book.setAuthors(new HashSet<>());
        book.setGenres(new HashSet<>());
        book.setOrders(new HashSet<>());
        return book;
    }

    public Book toEntity(Request.Update bookDto) {
        if (bookDto == null) return null;
        Book.BookBuilder book = Book.builder();
        if (bookDto.getPrice() != null) book.price(bookDto.getPrice());
        if (bookDto.getName() != null) book.name(bookDto.getName());
        if (bookDto.getNumberOfPages() != null) book.numberOfPages(bookDto.getNumberOfPages());
        if (bookDto.getReleaseYear() != null) book.releaseYear(bookDto.getReleaseYear());
        book.publisher(new Publisher());
        book.authors(new HashSet<>());
        book.genres(new HashSet<>());
        book.orders(new HashSet<>());
        return book.build();
    }

    public Response.Created toDtoResponseCreated(Book book) {
        Response.Created created = new Response.Created();
        if (book == null) return null;
        if (book.getId() != null) created.setId(book.getId());
        if (book.getPrice() != null) created.setPrice(book.getPrice());
        if (book.getName() != null) created.setName(book.getName());
        if (book.getNumberOfPages() != null) created.setNumberOfPages(book.getNumberOfPages());
        if (book.getReleaseYear() != null) created.setReleaseYear(book.getReleaseYear());
        return created;
    }

    public Response.Slim toDtoResponseSlim(Book book) {
        Response.Slim slim = new Response.Slim();
        if (book == null) return null;
        if (book.getId() != null) slim.setId(book.getId());
        if (book.getName() != null) slim.setName(book.getName());
        return slim;
    }

    public Response.All toDtoResponseAll(Book book) {
        Response.All all = new Response.All();
        if (book.getId() != null) all.setId(book.getId());
        if (book.getPrice() != null) all.setPrice(book.getPrice());
        if (book.getName() != null) all.setName(book.getName());
        if (book.getNumberOfPages() != null) all.setNumberOfPages(book.getNumberOfPages());
        if (book.getReleaseYear() != null) all.setReleaseYear(book.getReleaseYear());

        List<AuthorDto.Response.Slim> authors = new ArrayList<>(book.getAuthors().size());
        book.getAuthors().forEach(author -> authors.add(authorMapper.toDtoResponseSlim(author)));
        all.setAuthors(authors);

        List<GenreDto.Response.Slim> genres = new ArrayList<>(book.getGenres().size());
        book.getGenres().forEach(genre -> genres.add(genreMapper.toDtoResponseSlim(genre)));
        all.setGenres(genres);

        if (book.getPublisher() != null) all.setPublisher(publisherMapper.toDtoResponseSlim(book.getPublisher()));
        return all;
    }

    public List<BookDto.Response.All> toDtoResponseAll(List<Book> books) {
        if (books == null) return null;
        List<BookDto.Response.All> list = new ArrayList<>(books.size());
        books.forEach(book -> list.add(toDtoResponseAll(book)));
        return list;
    }

    public List<BookDto.Response.Slim> toDtoResponseSlim(List<Book> books) {
        if (books == null) return null;
        List<BookDto.Response.Slim> list = new ArrayList<>(books.size());
        books.forEach(book -> list.add(toDtoResponseSlim(book)));
        return list;
    }
}
