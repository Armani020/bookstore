package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.AuthorDto;
import kz.halykacademy.bookstore.dto.AuthorDto.Request;
import kz.halykacademy.bookstore.dto.AuthorDto.Response;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.mapper.MapStructMapper;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Author service.
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final MapStructMapper mapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, GenreRepository genreRepository, MapStructMapper mapper) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.mapper = mapper;
    }

    @Override
    public Response.Created save(final Request.Create request) {
        Author author = mapper.toAuthor(request);
        author.setBooks(new HashSet<>());
        if (authorRepository.existsBySurnameAndNameAndPatronymicAndDateOfBirth(
                author.getSurname(),
                author.getName(),
                author.getPatronymic(),
                author.getDateOfBirth())
        ) {
            throw new IllegalArgumentException("Author with this data already exists: " + request);
        }
        return mapper.toAuthorDtoResponseCreated(
                authorRepository.save(author)
        );
    }

    @Override
    public Response.Slim update(final Long id, final Request.Update request) {
        Author authorFromDb = authorRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Author not found. id: " + id)
        );
        authorFromDb.setName(request.getName());
        authorFromDb.setSurname(request.getSurname());
        authorFromDb.setPatronymic(request.getPatronymic());
        authorFromDb.setDateOfBirth(request.getDateOfBirth());
        return mapper.toAuthorDtoResponseSlim(authorRepository.save(authorFromDb));
    }

    @Override
    public Response.All find(final Long id) {
        Author authorFromDb = authorRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Author not found. id: " + id));
        AuthorDto.Response.All response = mapper.toAuthorDtoResponseAll(authorFromDb);
        response.setGenres(mapper.toGenreDtoResponseSlim(genreRepository.findGenresByAuthor(id)));
        return response;
    }

    @Override
    public List<Response.All> findAll(final String fullName) {
        List<AuthorDto.Response.All> authorsFromDb = mapper.toAuthorDtoResponseAll(
                authorRepository.findByFullName(fullName));
        for (AuthorDto.Response.All a : authorsFromDb) {
            a.setGenres(mapper.toGenreDtoResponseSlim(genreRepository.findGenresByAuthor(a.getId())));
        }
        return authorsFromDb;
    }

    @Override
    public void delete(final Long id) {
        if (!authorRepository.existsById(id)) {
            throw new IllegalArgumentException("Author doesn't exists. ID: " + id);
        }
        authorRepository.deleteById(id);
    }
}
