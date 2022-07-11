package kz.halykacademy.bookstore.entity;

import java.time.LocalDate;
import java.util.List;

public class Book {

    private Long id;

    private Integer price;

    private String name;

    private Integer numberOfPages;

    private LocalDate releaseYear;

    private Publisher publisher;

    private List<Author> authors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(LocalDate releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Book(Long id, Integer price, String name, Integer numberOfPages, LocalDate releaseYear, Publisher publisher, List<Author> authors) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.releaseYear = releaseYear;
        this.publisher = publisher;
        this.authors = authors;
    }
}
