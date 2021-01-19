package fr.training.spring.library.expo.dto;

import fr.training.spring.library.domain.library.Address;
import fr.training.spring.library.domain.book.Book;
import fr.training.spring.library.domain.library.Director;
import fr.training.spring.library.domain.library.Library;

import java.util.ArrayList;
import java.util.List;

public class MapperEntityToDto {
    public static LibraryDto mapToLibraryDto(Library library){
        LibraryDto libraryDto =new LibraryDto();
        libraryDto.setId(library.getId());
        Address address = library.getAddress();
        if(address!=null) {
            libraryDto.setCity(address.getCity());
            libraryDto.setNumber(address.getNumber());
            libraryDto.setPostalCode(address.getPostalCode());
            libraryDto.setStreet(address.getStreet());
        }

        Director director = library.getDirector();
        if(director!=null) {
            libraryDto.setName(director.getName());
            libraryDto.setSurname(director.getSurname());
        }

        libraryDto.setTypeLib(library.getTypeLib()!=null ? library.getTypeLib().name() :"");

        libraryDto.setBookDtos(mapToListBookDto(library.getBooks()));

        return libraryDto;
    }

    public static BookDto mapToBookDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setPagesNumbers(book.getPagesNumbers());
        bookDto.setGenre(book.getGenreLitteraire()!=null ? book.getGenreLitteraire().name() :"");
        bookDto.setIsbn(book.getIsbn());

        return bookDto;
    }

    public static List<LibraryDto> mapToListLibraryDto(List<Library> libraries){
        List<LibraryDto> libraryDtos =new ArrayList<>();
        if(libraries!=null) {
            for (Library library : libraries) {
                libraryDtos.add(mapToLibraryDto(library));
            }
        }

        return libraryDtos;
    }

    public static List<BookDto> mapToListBookDto(List<Book> books){
        List<BookDto> bookDtos = new ArrayList<>();

        if(books!=null) {
            for (Book book : books) {
                bookDtos.add(mapToBookDto(book));
            }
        }

        return bookDtos;
    }
}
