package kz.halykacademy.bookstore.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
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

    private Date releaseYear;

//    @ManyToOne
//    @JoinColumn(name = "publisher_id")
//    private Publisher publisher;

    @ManyToMany(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            }
    )
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT)
    )
    private Set<Author> authors = new HashSet<>();

//    public void addAuthor(Author author) {
//        this.authors.add(author);
//        author.getBooks().add(this);
//    }
//
//    public void removeAuthor(Long authorId) {
//        Author author = this.authors.stream().filter(a -> a.getId() == authorId).findFirst().orElse(null);
//        if (author != null) {
//            this.authors.remove(author);
//            author.getBooks().remove(this);
//        }
//    }
}
