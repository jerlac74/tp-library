package fr.training.spring.library.infra.jpa;

import fr.training.spring.library.domain.book.GenreLitteraire;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

@Entity
@Table(name = "BOOK")
public class BookJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private int pagesNumbers;
    @NotNull
    @Enumerated(EnumType.STRING)
    private GenreLitteraire genreLitteraire;
    @NotNull
    private String isbn;

    BookJpa() {
    }

    public BookJpa(Long id, String title, String author, int pagesNumbers, GenreLitteraire genreLitteraire, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pagesNumbers = pagesNumbers;
        this.genreLitteraire = genreLitteraire;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPagesNumbers() {
        return pagesNumbers;
    }

    public GenreLitteraire getGenreLitteraire() {
        return genreLitteraire;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BookJpa.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("author='" + author + "'")
                .add("pagesNumbers=" + pagesNumbers)
                .add("genreLitteraire=" + genreLitteraire)
                .add("isbn='" + isbn + "'")
                .toString();
    }

    }
