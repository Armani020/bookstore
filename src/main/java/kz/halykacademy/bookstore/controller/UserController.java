package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.UserDto.Request;
import kz.halykacademy.bookstore.dto.UserDto.Response;
import kz.halykacademy.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response.Created> save(@Validated @RequestBody Request.Create request) {
        return new ResponseEntity<>(
                userService.save(request),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response.All> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(
                userService.find(id),
                HttpStatus.OK
        );
    }

    @GetMapping("/")
    public ResponseEntity<Page<Response.Slim>> getAuthors(
            @RequestParam(required = false) String login,
            Pageable pageable) {
        return new ResponseEntity<>(
                userService.findAll(login, pageable),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response.Slim> updateAuthor(
            @PathVariable Long id,
            @Valid @RequestBody Request.Update request) {
        return new ResponseEntity<>(
                userService.update(id, request),
                HttpStatus.OK
        );
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Response.Slim> updateUserStatus(
            @PathVariable Long id,
            @Valid @RequestBody Request.UpdateStatus request) {
        return new ResponseEntity<>(
                userService.updateStatus(id, request),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(
                "User deleted. id: " + id,
                HttpStatus.OK
        );
    }
}
