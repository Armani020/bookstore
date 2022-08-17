package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.AuthorDto.Response;
import kz.halykacademy.bookstore.entity.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorMapperImpl {

    public Response.Slim toDtoResponseSlim(Author author) {
        Response.Slim slim = new Response.Slim();
        if (author == null) return null;
        if (author.getId() != null) slim.setId(author.getId());
        if (author.getSurname() != null) slim.setSurname(author.getSurname());
        if (author.getName() != null) slim.setName(author.getName());
        if (author.getPatronymic() != null) slim.setPatronymic(author.getPatronymic());
        if (author.getDateOfBirth() != null) slim.setDateOfBirth(author.getDateOfBirth());
        return slim;
    }
}
