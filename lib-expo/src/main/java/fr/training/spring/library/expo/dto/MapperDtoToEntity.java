package fr.training.spring.library.expo.dto;

import fr.training.spring.library.domain.book.Book;
import fr.training.spring.library.domain.book.GenreLitteraire;
import fr.training.spring.library.domain.library.*;

import java.util.ArrayList;
import java.util.List;

public class MapperDtoToEntity {
    public static Library mapToLibrary(LibraryDto libraryDto) {
        Address address = new Address(libraryDto.getNumber(), libraryDto.getStreet(), libraryDto.getPostalCode(), libraryDto.getCity());
        Director director = new Director(libraryDto.getSurname(), libraryDto.getName());

        //return new Library(libraryDto.getId(), convertToEnumTypeLib(libraryDto.getTypeLib()), address, director, mapToListBook(libraryDto.getBookDtos()));
        return Library.builder().id(libraryDto.getId())
                .typeLib(convertToEnumTypeLib(libraryDto.getTypeLib()))
                .address(address).director(director)
                .books(mapToListBook(libraryDto.getBookDtos()))
                .build();
    }

    public static Book mapToBook(BookDto bookDto) {
        /*return new Book(bookDto.getId(), bookDto.getTitle(), bookDto.getAuthor(), bookDto.getPagesNumbers(),
                convertToEnumGenre(bookDto.getGenre()), bookDto.getIsbn());

         */
        return Book.builder().id(bookDto.getId()).title(bookDto.getTitle()).author(bookDto.getAuthor()).pagesNumbers(bookDto.getPagesNumbers())
                .genreLitteraire(convertToEnumGenre(bookDto.getGenre())).isbn(bookDto.getIsbn())
                .build();
    }

    public static List<Library> mapToListLibrary(List<LibraryDto> libraryDtos) {
        List<Library> libraries = new ArrayList<>();
        if (libraryDtos != null) {
            for (LibraryDto libraryDto : libraryDtos) {
                libraries.add(mapToLibrary(libraryDto));
            }
        }

        return libraries;
    }

    public static List<Book> mapToListBook(List<BookDto> bookDtos) {
        List<Book> books = new ArrayList<>();

        if (bookDtos != null) {
            for (BookDto bookDto : bookDtos) {
                books.add(mapToBook(bookDto));
            }
        }

        return books;
    }

    public static GenreLitteraire convertToEnumGenre(String value) {
        if (value != null) {
            if (value.equals(GenreLitteraire.COMICS.name())) {
                return GenreLitteraire.COMICS;
            } else if (value.equals(GenreLitteraire.HISTORIQUE.name())) {
                return GenreLitteraire.HISTORIQUE;
            } else if (value.equals(GenreLitteraire.SCIENCE_FICTION.name())) {
                return GenreLitteraire.SCIENCE_FICTION;
            }
        }
        return null;
    }

    private static TypeLib convertToEnumTypeLib(String value) {
        if (value != null) {
            if (value.equals(TypeLib.Associative.name())) {
                return TypeLib.Associative;
            } else if (value.equals(TypeLib.Nationale.name())) {
                return TypeLib.Nationale;
            } else if (value.equals(TypeLib.Publique.name())) {
                return TypeLib.Publique;
            } else if (value.equals(TypeLib.Scolaire.name())) {
                return TypeLib.Scolaire;
            }
            if (value.equals(TypeLib.Universitaire.name())) {
                return TypeLib.Universitaire;
            }
        }
        return null;
    }
}
