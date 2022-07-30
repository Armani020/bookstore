package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.PublisherDto;
import kz.halykacademy.bookstore.dto.PublisherDto.*;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.mapper.MapStructMapper;
import kz.halykacademy.bookstore.repository.PublisherRepository;
import kz.halykacademy.bookstore.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    private final MapStructMapper mapper;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository, MapStructMapper mapper) {
        this.publisherRepository = publisherRepository;
        this.mapper = mapper;
    }

    @Override
    public Response.Created save(final Request.Create request) {
        if (publisherRepository.existsByNameIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Publisher already exists. name: " + request.getName());
        }
        Publisher publisher = mapper.toPublisher(request);
        publisher.setBooks(new ArrayList<>());
        return mapper.toPublisherDtoResponseCreated(
                publisherRepository.save(publisher)
        );
    }

    @Override
    public Response.Slim update(final Long id, final Request.Update request) {
        Publisher publisherFromDb = publisherRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Publisher not found. ID: " + id)
        );
        publisherFromDb.setName(request.getName());
        return mapper.toPublisherDtoResponseSlim(publisherRepository.save(publisherFromDb));
    }

    @Override
    public Response.All find(final Long id) {
        return mapper.toPublisherDtoResponseAll(
                publisherRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("Publisher not found. id: " + id)
                )
        );
    }

    @Override
    public List<Response.All> findAll(final String name) {
        return mapper.toPublisherDtoResponseAll(
                publisherRepository.findPublishersByNameContainingIgnoreCase(name)
        );
    }

    @Override
    public void delete(final Long id) {
        publisherRepository.deleteById(id);
    }
}
