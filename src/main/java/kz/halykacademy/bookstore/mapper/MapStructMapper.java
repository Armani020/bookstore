package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.AuthorDto;
import kz.halykacademy.bookstore.dto.BookDto;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for converting entities to dto and vise-versa.
 */
@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {

    AuthorDto.Response.Created authorToAuthorDtoResponseCreated(Author author);

    AuthorDto.Response.Slim authorToAuthorDtoResponseSlim(Author author);

    List<AuthorDto.Response.Slim> authorsToAuthorDtoResponseSlim(List<Author> authors);

    List<AuthorDto.Response.All> authorsToAuthorDtoResponseAll(List<Author> authors);

    Author authorDtoRequestCreateToAuthor(AuthorDto.Request.Create authorDto);

    Author authorDtoRequestUpdateToAuthor(AuthorDto.Request.Update authorDto);

    BookDto.Response.Created bookToBookDtoResponseCreated(Book book);

    BookDto.Response.Slim bookToBookDtoResponseSlim(Book book);

    BookDto.Response.All bookToBookDtoResponseAll(Book book);

    List<BookDto.Response.Slim> booksToBookDtoResponseSlim(List<Book> books);

    List<BookDto.Response.All> booksToBookDtoResponseAll(List<Book> books);

    Book bookDtoRequestCreateToBook(BookDto.Request.Create bookDto);

    Book bookDtoRequestUpdateToBook(BookDto.Request.Update bookDto);

}
