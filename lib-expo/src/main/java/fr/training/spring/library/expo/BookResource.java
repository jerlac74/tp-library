package fr.training.spring.library.expo;

import fr.training.spring.library.appli.IBookService;
import fr.training.spring.library.domain.book.Book;
import fr.training.spring.library.expo.dto.BookDto;
import fr.training.spring.library.expo.dto.MapperDtoToEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookResource {
    @Autowired
    private IBookService bookService;

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public BookDto searchBookByISBN(@RequestParam("isbn") final String isbn) {
        final Book book = bookService.searchBookByISBN(isbn);
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor(), book.getPagesNumbers(), book.getGenreLitteraire().name(), isbn);
    }

    @PostMapping("/books/add/{libraryId}")
    @ResponseStatus(HttpStatus.OK)
    public BookDto addBookToLibrary(@PathVariable("libraryId") long libraryId,  @RequestParam("isbn") final String isbn, @RequestParam("literaryGenre") final String literaryGenre) {
        final Book book = bookService.addBookToLibrary(libraryId, isbn, MapperDtoToEntity.convertToEnumGenre(literaryGenre));
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor(), book.getPagesNumbers(), book.getGenreLitteraire().name(), isbn);
    }
}
