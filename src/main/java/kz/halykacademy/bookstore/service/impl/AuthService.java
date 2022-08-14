package kz.halykacademy.bookstore.service.impl;

import io.jsonwebtoken.Claims;
import kz.halykacademy.bookstore.config.jwt.JwtAuthentication;
import kz.halykacademy.bookstore.config.jwt.JwtProvider;
import kz.halykacademy.bookstore.dto.TokenDto;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public TokenDto.Response.TokenResponse login(@NonNull TokenDto.Request.LoginRequest authRequest) {
        final User user = userRepository.findByLogin(authRequest.getLogin())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getLogin(), refreshToken);
            return new TokenDto.Response.TokenResponse(accessToken, refreshToken);
        } else {
            throw new IllegalArgumentException("Wrong auth data!");
        }
    }

    public TokenDto.Response.TokenResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findByLogin(login)
                        .orElseThrow(() -> new IllegalArgumentException("User not found! Login: " + login));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new TokenDto.Response.TokenResponse(accessToken, refreshToken);
            }
        }
        return new TokenDto.Response.TokenResponse(null, null);
    }

    public TokenDto.Response.TokenResponse refresh(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findByLogin(login)
                        .orElseThrow(() -> new IllegalArgumentException("User not found! Login: " + login));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getLogin(), newRefreshToken);
                return new TokenDto.Response.TokenResponse(accessToken, newRefreshToken);
            }
        }
        throw new IllegalArgumentException("Invalid token!");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
