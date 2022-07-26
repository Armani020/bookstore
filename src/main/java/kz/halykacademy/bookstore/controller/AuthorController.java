package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.AuthorDto;
import kz.halykacademy.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/")
    public ResponseEntity<AuthorDto.Response.Created> createAuthor(@Valid @RequestBody AuthorDto.Request.Create request) {
        return new ResponseEntity<>(
                authorService.save(request),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto.Response.All> getAuthor(@PathVariable Long id) {
        return new ResponseEntity<>(
                authorService.find(id),
                HttpStatus.OK
        );
    }

    @GetMapping("/")
    public ResponseEntity<List<AuthorDto.Response.All>> getAuthors(@RequestParam(defaultValue = "") String fullName) {
        return new ResponseEntity<>(
                authorService.findAll(fullName),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto.Response.Slim> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorDto.Request.Update request) {
        return new ResponseEntity<>(
                authorService.update(id, request),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return new ResponseEntity<>(
                "Author deleted. id: " + id,
                HttpStatus.OK
        );
    }

}
