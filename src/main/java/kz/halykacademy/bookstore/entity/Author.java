package kz.halykacademy.bookstore.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String surname;

    private String name;

    private String patronymic;

    private LocalDate dateOfBirth;

    @ManyToMany
    private List<Book> writtenBooks;

    public Author() {

    }

    public Author(Long id, String surname, String name, String patronymic, LocalDate dateOfBirth, List<Book> writtenBooks) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.writtenBooks = writtenBooks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Book> getWrittenBooks() {
        return writtenBooks;
    }

    public void setWrittenBooks(List<Book> writtenBooks) {
        this.writtenBooks = writtenBooks;
    }
}
