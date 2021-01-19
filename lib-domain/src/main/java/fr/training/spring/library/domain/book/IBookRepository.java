package fr.training.spring.library.domain.book;

public interface IBookRepository {
    Book searchBook(String isbn);
}
