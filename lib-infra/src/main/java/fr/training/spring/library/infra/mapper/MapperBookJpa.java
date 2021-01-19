package fr.training.spring.library.infra.mapper;

import fr.training.spring.library.domain.book.Book;
import fr.training.spring.library.infra.jpa.BookJpa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperBookJpa implements IMapperJpa<BookJpa, Book> {
    @Override
    public Book mapToEntity(BookJpa bookJpa) {
        if(bookJpa!=null){
            return Book.builder().id(bookJpa.getId())
                    .title(bookJpa.getTitle())
                    .author(bookJpa.getAuthor())
                    .pagesNumbers(bookJpa.getPagesNumbers())
                    .genreLitteraire(bookJpa.getGenreLitteraire())
                    .isbn(bookJpa.getIsbn())
                    .build();
        }
        return null;
    }

    @Override
    public BookJpa mapToJpa(Book book) {
        if(book!=null){
            return new BookJpa(book.getId(), book.getTitle(), book.getAuthor(), book.getPagesNumbers(),
                    book.getGenreLitteraire(), book.getIsbn());
        }
        return null;
    }

    @Override
    public List<Book> mapToEntityList(List<BookJpa> bookJpas) {
        if(bookJpas!=null){
            List<Book> books = new ArrayList<>();
            for (BookJpa bookJpa : bookJpas) {
                books.add(mapToEntity(bookJpa));
            }
            return books;
        }
        return null;
    }

    @Override
    public List<BookJpa> mapToJpaList(List<Book> books) {
        if(books!=null){
            List<BookJpa> bookJpas = new ArrayList<>();
            for (Book book : books) {
                bookJpas.add(mapToJpa(book));
            }
            return bookJpas;
        }
        return null;
    }
}
