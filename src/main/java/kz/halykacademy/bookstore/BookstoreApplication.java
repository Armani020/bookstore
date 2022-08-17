package kz.halykacademy.bookstore;

import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.entity.enums.AccountStatus;
import kz.halykacademy.bookstore.repository.RoleRepository;
import kz.halykacademy.bookstore.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByLogin("admin")) {
                User userToDb = User.builder()
                        .id(null)
                        .login("admin")
                        .password(passwordEncoder.encode("12345"))
                        .status(AccountStatus.ACTIVE)
                        .roles(new HashSet<>())
                        .orders(new HashSet<>()).build();
                userToDb.getRoles().add(roleRepository.findByName("ROLE_ADMIN"));
                userRepository.save(userToDb);
            }
        };
    }
}
