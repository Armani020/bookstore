package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.UserDto.*;
import kz.halykacademy.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<Response.Created> save(@Validated @RequestBody Request.Create request) {
        return new ResponseEntity<>(
                userService.save(request),
                HttpStatus.CREATED
        );
    }

//    @GetMapping("/")
//
}
