package kz.halykacademy.bookstore.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer price;

    private String name;

    private Integer numberOfPages;

    private LocalDate releaseYear;

    @ManyToOne(fetch = FetchType.LAZY)
    private Publisher publisher;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(mappedBy = "books")
    private Set<Genre> genres = new HashSet<>();

    public void addAuthor(Author author) {
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(Long authorId) {
        Author author = this.authors.stream().filter(b -> b.getId().equals(authorId)).findFirst().orElse(null);
        if (author != null) {
            this.authors.remove(author);
            author.getBooks().remove(this);
        }
    }

    public void addGenre(Genre genre) {
        this.genres.add(genre);
        genre.getBooks().add(this);
    }

    public void removeGenre(Long genreId) {
        Genre genre = this.genres.stream().filter(b -> b.getId().equals(genreId)).findFirst().orElse(null);
        if (genre != null) {
            this.genres.remove(genre);
            genre.getBooks().remove(this);
        }
    }

    public void addPublisher(Publisher publisher) {
        this.setPublisher(publisher);
        publisher.getBooks().add(this);
    }

    public void removePublisher(Publisher publisher) {
        this.setPublisher(null);
        publisher.getBooks().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
