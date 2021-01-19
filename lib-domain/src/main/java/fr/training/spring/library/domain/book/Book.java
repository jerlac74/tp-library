package fr.training.spring.library.domain.book;

import org.apache.commons.lang3.Validate;

import java.util.StringJoiner;

public class Book {
    private Long id;

    private String title;
    private String author;
    private int pagesNumbers;
    //@Enumerated(EnumType.STRING)
    private GenreLitteraire genreLitteraire;
    private String isbn;

    Book() {
    }

    /*public Book(Long id, String title, String author, int pagesNumbers, GenreLitteraire genreLitteraire, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pagesNumbers = pagesNumbers;
        this.genreLitteraire = genreLitteraire;
        this.isbn = isbn;
    }*/

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
        return new StringJoiner(", ", Book.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("author='" + author + "'")
                .add("pagesNumbers=" + pagesNumbers)
                .add("genreLitteraire=" + genreLitteraire)
                .add("isbn='" + isbn + "'")
                .toString();
    }

    /**
     * private constructor to enforce Builder usage
     */
    private Book(final Builder builder) {
        if (builder.id != null) {
            id = builder.id;
        }
        title = builder.title;
        author = builder.author;
        pagesNumbers = builder.pagesNumbers;
        genreLitteraire = builder.genreLitteraire;
        isbn = builder.isbn;
    }

    /**
     * Builder static assessor
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder pattern to ensure Customer is immutable.
     */
    public static class Builder {
        private Long id;
        private String title;
        private String author;
        private int pagesNumbers;
        private GenreLitteraire genreLitteraire;
        private String isbn;

        public Builder id(final Long id) {
            this.id = id;
            return this;
        }

        public Builder title(final String title) {
            this.title = title;
            return this;
        }

        public Builder author(final String author) {
            this.author = author;
            return this;
        }

        public Builder pagesNumbers(final int pagesNumbers) {
            this.pagesNumbers = pagesNumbers;
            return this;
        }

        public Builder genreLitteraire(final GenreLitteraire genreLitteraire) {
            this.genreLitteraire = genreLitteraire;
            return this;
        }

        public Builder isbn(final String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Book build() {
            Validate.notNull(title, "book's title is required");
            Validate.notNull(author, "book's author is required");
            Validate.notNull(pagesNumbers, "book's pages numbers is required");
            Validate.notNull(isbn, "book's isbn is required");

            if(genreLitteraire==null){
                genreLitteraire = GenreLitteraire.UNDEFINED;
            }
            return new Book(this);
        }

    }

}
