package kz.halykacademy.bookstore;

import kz.halykacademy.bookstore.entity.Role;
import kz.halykacademy.bookstore.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository) {
        return args -> {
            roleRepository.save(new Role(null, "ROLE_USER"));
        };
    }
}
