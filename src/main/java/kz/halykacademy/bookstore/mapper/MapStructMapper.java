package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.AuthorDto;
import kz.halykacademy.bookstore.dto.BookDto;
import kz.halykacademy.bookstore.dto.GenreDto;
import kz.halykacademy.bookstore.dto.PublisherDto;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

/**
 * Mapper for converting entities to dto and vise-versa.
 */
@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface MapStructMapper {

    AuthorDto.Response.Created toAuthorDtoResponseCreated(Author author);

    AuthorDto.Response.Slim toAuthorDtoResponseSlim(Author author);

    AuthorDto.Response.All toAuthorDtoResponseAll(Author author);

    List<AuthorDto.Response.Slim> toAuthorDtoResponseSlim(List<Author> authors);

    List<AuthorDto.Response.All> toAuthorDtoResponseAll(List<Author> authors);

    Author toAuthor(AuthorDto.Request.Create authorDto);

    Author toAuthor(AuthorDto.Request.Update authorDto);

    BookDto.Response.Created toBookDtoResponseCreated(Book book);

    BookDto.Response.Slim toBookDtoResponseSlim(Book book);

    BookDto.Response.All toBookDtoResponseAll(Book book);

    List<BookDto.Response.Slim> toBookDtoResponseSlim(List<Book> books);

    List<BookDto.Response.All> toBookDtoResponseAll(List<Book> books);

    Book toBook(BookDto.Request.Create bookDto);

    Book toBook(BookDto.Request.Update bookDto);

    PublisherDto.Response.Created toPublisherDtoResponseCreated(Publisher publisher);

    PublisherDto.Response.Slim toPublisherDtoResponseSlim(Publisher publisher);

    PublisherDto.Response.All toPublisherDtoResponseAll(Publisher publisher);

    List<PublisherDto.Response.Slim> toPublisherDtoResponseSlim(List<Publisher> publishers);

    List<PublisherDto.Response.All> toPublisherDtoResponseAll(List<Publisher> publishers);

    Publisher toPublisher(PublisherDto.Request.Create publisherDto);

    Publisher toPublisher(PublisherDto.Request.Update publisherDto);

    GenreDto.Response.Created toGenreDtoResponseCreated(Genre genre);

    GenreDto.Response.Slim toGenreDtoResponseSlim(Genre genre);

    GenreDto.Response.All toGenreDtoResponseAll(Genre genre);

    List<GenreDto.Response.Slim> toGenreDtoResponseSlim(List<Genre> genres);

    List<GenreDto.Response.All> toGenreDtoResponseAll(List<Genre> genres);

    Genre toGenre(GenreDto.Request.Create genreDto);

    Genre toGenre(GenreDto.Request.Update genreDto);
}
