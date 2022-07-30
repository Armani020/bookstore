package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.GenreDto.*;
import kz.halykacademy.bookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/")
    public ResponseEntity<Response.Created> createGenre(@Valid @RequestBody Request.Create request) {
        return new ResponseEntity<>(
                genreService.save(request),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response.All> getGenre(@PathVariable Long id) {
        return new ResponseEntity<>(
                genreService.find(id),
                HttpStatus.OK
        );
    }

    @GetMapping("/")
    public ResponseEntity<List<Response.All>> getGenres(@RequestParam(defaultValue = "") String name) {
        return new ResponseEntity<>(
                genreService.findAll(name),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response.Slim> updateGenre(@PathVariable Long id, @Valid @RequestBody Request.Update request) {
        return new ResponseEntity<>(
                genreService.update(id, request),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable Long id) {
        genreService.delete(id);
        return new ResponseEntity<>(
                "Genre deleted. id: " + id,
                HttpStatus.OK
        );
    }
}
