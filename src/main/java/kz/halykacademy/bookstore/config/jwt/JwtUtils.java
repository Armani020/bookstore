package kz.halykacademy.bookstore.config.jwt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import kz.halykacademy.bookstore.entity.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtAuthentication = new JwtAuthentication();
        jwtAuthentication.setRoles(getRoles(claims));
        jwtAuthentication.setLogin(claims.get("login", String.class));
        jwtAuthentication.setUsername(claims.getSubject());
        return jwtAuthentication;
    }

    private static Collection<SimpleGrantedAuthority> getRoles(Claims claims) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final List<Role> roles = objectMapper.convertValue(claims.get("roles", List.class), new TypeReference<>() {
        });
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }
}
