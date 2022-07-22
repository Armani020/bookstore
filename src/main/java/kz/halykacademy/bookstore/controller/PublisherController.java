package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.PublisherDto;
import kz.halykacademy.bookstore.dto.PublisherDto.*;
import kz.halykacademy.bookstore.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("/")
    public ResponseEntity<PublisherDto.Response.Created> createPublisher(@Valid @RequestBody Request.Create request) {
        return new ResponseEntity<>(
                publisherService.save(request),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response.All> getPublisher(@PathVariable Long id) {
        return new ResponseEntity<>(
                publisherService.find(id),
                HttpStatus.OK
        );
    }

    @GetMapping("/")
    public ResponseEntity<List<Response.All>> getPublishers(@RequestParam(defaultValue = "") String name) {
        return new ResponseEntity<>(
                publisherService.findAll(name),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response.Slim> updatePublisher(@PathVariable Long id, @Valid @RequestBody Request.Update request) {
        return new ResponseEntity<>(
                publisherService.update(id, request),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePublisher(@PathVariable Long id) {
        publisherService.delete(id);
        return new ResponseEntity<>(
                "Publisher deleted. id: " + id,
                HttpStatus.OK
        );
    }
}
