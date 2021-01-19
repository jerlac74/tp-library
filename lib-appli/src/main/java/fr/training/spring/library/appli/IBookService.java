package fr.training.spring.library.appli;

import fr.training.spring.library.domain.book.Book;
import fr.training.spring.library.domain.book.GenreLitteraire;

public interface IBookService {

    Book searchBookByISBN(final String isbn);
    Book addBookToLibrary(final Long librayrId, final String isbn, final GenreLitteraire literaryGenre);

}
