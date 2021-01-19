package fr.training.spring.library.appli;

import fr.training.spring.library.domain.book.Book;
import fr.training.spring.library.domain.book.IBookRepository;
import fr.training.spring.library.domain.book.GenreLitteraire;
import fr.training.spring.library.domain.library.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BookService implements IBookService {
    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private ILibraryService libraryService;

    @Override
    public Book searchBookByISBN(String isbn) {
        if(!StringUtils.isEmpty(isbn)) {
            return bookRepository.searchBook(isbn);
        }
        return null;
    }

    @Override
    public Book addBookToLibrary(Long libraryId, String isbn, GenreLitteraire literaryGenre) {
        if(libraryId>0 && !StringUtils.isEmpty(isbn)) {
            Book book = bookRepository.searchBook(isbn);
            Library library = libraryService.findById(libraryId);
            if(library!=null){
                Book bookNew = Book.builder().id(book.getId()).author(book.getAuthor()).isbn(book.getIsbn()).pagesNumbers(book.getPagesNumbers())
                        .title(book.getTitle()).genreLitteraire(literaryGenre).build();
                library.addBook(bookNew);
                Library libraryUpdated = libraryService.save(library);

                for (Book bookUpd : libraryUpdated.getBooks()) {
                    if(bookUpd.getTitle().equals(bookNew.getTitle())){
                        return Book.builder().id(bookUpd.getId()).author(bookUpd.getAuthor()).isbn(bookUpd.getIsbn()).pagesNumbers(bookUpd.getPagesNumbers())
                                .title(bookUpd.getTitle()).genreLitteraire(bookUpd.getGenreLitteraire()).build();
                    }
                }
                return bookNew;
            }
        }
        return null;
    }


}
