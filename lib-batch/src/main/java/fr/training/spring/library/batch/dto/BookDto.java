package fr.training.spring.library.batch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

public class BookDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;
    @JsonProperty("author")
    private String author;
    @JsonProperty("pagesNumbers")
    private int pagesNumbers;
    @JsonProperty("genre")
    private String genre;
    @JsonProperty("isbn")
    private String isbn;

    public BookDto() {
    }

    public BookDto(Long id, String title, String author, int pagesNumbers, String genre, String isbn) {
        this.id =id;
        this.title = title;
        this.author = author;
        this.pagesNumbers = pagesNumbers;
        this.genre = genre;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPagesNumbers() {
        return pagesNumbers;
    }

    public void setPagesNumbers(int pagesNumbers) {
        this.pagesNumbers = pagesNumbers;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BookDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("author='" + author + "'")
                .add("pagesNumbers=" + pagesNumbers)
                .add("genre='" + genre + "'")
                .add("isbn='" + isbn + "'")
                .toString();
    }
}
