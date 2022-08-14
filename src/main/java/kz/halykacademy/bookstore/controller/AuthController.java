package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.TokenDto.*;
import kz.halykacademy.bookstore.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Response.TokenResponse> login(@RequestBody Request.LoginRequest authRequest) {
        return new ResponseEntity<>(
                authService.login(authRequest),
                HttpStatus.OK
        );
    }

    @PostMapping("/token")
    public ResponseEntity<Response.TokenResponse> getNewAccessToken(@RequestBody Request.RefreshJwtRequest request) {
        return new ResponseEntity<>(
                authService.getAccessToken(request.getRefreshToken()),
                HttpStatus.OK
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<Response.TokenResponse> getNewRefreshToken(@RequestBody Request.RefreshJwtRequest request) {
        return new ResponseEntity<>(
                authService.refresh(request.getRefreshToken()),
                HttpStatus.OK
        );
    }
}
