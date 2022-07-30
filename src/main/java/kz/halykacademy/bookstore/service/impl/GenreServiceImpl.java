package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.GenreDto;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.mapper.MapStructMapper;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final MapStructMapper mapper;

    public GenreServiceImpl(GenreRepository genreRepository, MapStructMapper mapper) {
        this.genreRepository = genreRepository;
        this.mapper = mapper;
    }

    @Override
    public GenreDto.Response.Created save(final GenreDto.Request.Create request) {
        Genre genre = mapper.toGenre(request);
        genre.setBooks(new HashSet<>());
        if (genreRepository.existsByNameIgnoreCase(genre.getName())) {
            throw new IllegalArgumentException("Genre with this name already exists: " + genre.getName());
        }
        return mapper.toGenreDtoResponseCreated(
                genreRepository.save(genre)
        );
    }

    @Override
    public GenreDto.Response.Slim update(final Long id, final GenreDto.Request.Update request) {
        Genre genreFromDb = genreRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Genre with this id not found id: " + id)
        );
        genreFromDb.setName(request.getName());
        return mapper.toGenreDtoResponseSlim(genreRepository.save(genreFromDb));
    }

    @Override
    public GenreDto.Response.All find(final Long id) {
        return mapper.toGenreDtoResponseAll(
                genreRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("Genre not found. ID: " + id)
                )
        );
    }

    @Override
    public List<GenreDto.Response.All> findAll(final String name) {
        return mapper.toGenreDtoResponseAll(
                genreRepository.findGenresByNameContainingIgnoreCase(name)
        );
    }

    @Override
    public void delete(final Long id) {
        if (!genreRepository.existsById(id)) {
            throw new IllegalArgumentException("Genre doesn't exists. ID: " + id);
        }
        genreRepository.deleteById(id);
    }
}
