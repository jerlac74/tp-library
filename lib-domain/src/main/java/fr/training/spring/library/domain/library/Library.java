package fr.training.spring.library.domain.library;

import fr.training.spring.library.domain.book.Book;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Library {
    private Long id;

//    @Enumerated(EnumType.STRING)
    private TypeLib typeLib;

  //  @Embedded
    private Address address;

    //@Embedded
    //@NotNull
    private Director director;

    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Book> books;

    Library() {
    }

    /*public Library(Long id, TypeLib typeLib, Address address, Director director) {
        this.id = id;
        this.typeLib = typeLib;
        this.address = address;
        this.director = director;
    }

    public Library(Long id, TypeLib typeLib, Address address, Director director, List<Book> books) {
        this.id = id;
        this.typeLib = typeLib;
        this.address = address;
        this.director = director;
        this.books = books;
    }*/

    public Long getId() {
        return id;
    }

    public TypeLib getTypeLib() {
        return typeLib;
    }

    public Address getAddress() {
        return address;
    }

    public Director getDirector() {
        return director;
    }

    public List<Book> getBooks() {
        if (books == null) {
            books = new ArrayList<>();
        }
        return books;
    }

    public void addBook(Book book){
        this.getBooks().add(book);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Library.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("typeLib=" + typeLib)
                .add("address=" + address)
                .add("director=" + director)
                .add("books=" + books)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(id, library.id) && typeLib == library.typeLib && address.equals(library.address) && director.equals(library.director) && Objects.equals(books, library.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeLib, address, director, books);
    }

    /**
     * private constructor to enforce Builder usage
     */
    private Library(final Builder builder) {
        if (builder.id != null) {
            id = builder.id;
        }
        typeLib = builder.typeLib;
        address = builder.address;
        director = builder.director;
        books = builder.books;
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
        private TypeLib typeLib;
        private Address address;
        private Director director;
        private List<Book> books;

        public Builder id(final Long id) {
            this.id = id;
            return this;
        }

        public Builder typeLib(final TypeLib typeLib) {
            this.typeLib = typeLib;
            return this;
        }

        public Builder address(final Address address) {
            this.address = address;
            return this;
        }

        public Builder director(final Director director) {
            this.director = director;
            return this;
        }

        public Builder books(final List<Book> books) {
            this.books = books;
            return this;
        }

        public Library build() {
            Validate.notNull(typeLib, "library's type is required");
            Validate.notNull(address, "library's address is required");
            Validate.notNull(director, "library's director is required");
            return new Library(this);
        }

    }
}
