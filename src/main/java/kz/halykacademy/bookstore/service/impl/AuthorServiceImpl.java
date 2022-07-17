package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.AuthorDto.*;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.mapper.MapStructMapper;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import kz.halykacademy.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author service.
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final MapStructMapper mapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, MapStructMapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @Override
    public Response.Created save(final Request.Create request) {
        if (authorRepository.existsBySurnameAndNameAndPatronymicAndDateOfBirth(
                request.getSurname(),
                request.getName(),
                request.getPatronymic(),
                request.getDateOfBirth())
        ) {
            throw new IllegalArgumentException("Author with this data already exists: " + request);
        }
        return mapper.authorToAuthorDtoResponseCreated(
                authorRepository.save(mapper.authorDtoRequestCreateToAuthor(request))
        );
    }

    @Override
    public Response.Slim update(final Long id, final Request.Update request) {
        if (!authorRepository.existsById(id)) {
            throw new IllegalArgumentException("Author not found. id: " + id);
        }
        Author authorToDb = mapper.authorDtoRequestUpdateToAuthor(request);
        authorToDb.setId(id);
        return mapper.authorToAuthorDtoResponseSlim(authorRepository.save(authorToDb));
    }

    @Override
    public Response.Slim find(final Long id) {
        return mapper.authorToAuthorDtoResponseSlim(
                authorRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("Author not found. id: " + id)
                )
        );
    }

    @Override
    public List<Response.All> findAll(final String surname, final String name, final String patronymic) {
        return mapper.authorsToAuthorDtoResponseAll(
                authorRepository.findByFullName(surname, name, patronymic)
        );
    }

    @Override
    public void delete(final Long id) {
        authorRepository.deleteById(id);
    }
}
