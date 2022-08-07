package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.GenreDto.Response;
import kz.halykacademy.bookstore.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreMapperImpl {

    public Response.Slim toDtoResponseSlim(Genre genre) {
        Response.Slim slim = new Response.Slim();
        if (genre == null) return null;
        if (genre.getId() != null) slim.setId(genre.getId());
        if (genre.getName() != null) slim.setName(genre.getName());
        return slim;
    }
}
