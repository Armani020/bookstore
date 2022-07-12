package kz.halykacademy.bookstore.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "publisher")
    private List<Book> publishedBooks;

    public Publisher() {
    }
}
