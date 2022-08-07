package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.PublisherDto.Response;
import kz.halykacademy.bookstore.entity.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PublisherMapperImpl {

    public Response.Slim toDtoResponseSlim(Publisher publisher) {
        Response.Slim slim = new Response.Slim();
        if (publisher == null) return null;
        if (publisher.getId() != null) slim.setId(publisher.getId());
        if (publisher.getName() != null) slim.setName(publisher.getName());
        return slim;
    }
}
