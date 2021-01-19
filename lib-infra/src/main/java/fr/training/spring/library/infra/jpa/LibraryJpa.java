package fr.training.spring.library.infra.jpa;

import fr.training.spring.library.domain.library.TypeLib;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "LIBRARY")
public class LibraryJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeLib typeLib;

    @Embedded
    @NotNull
    private AddressJpa address;

    @Embedded
    @NotNull
    private DirectorJpa director;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BookJpa> books;

    LibraryJpa() {
    }

    public LibraryJpa(Long id, TypeLib typeLib, AddressJpa address, DirectorJpa director) {
        this.id = id;
        this.typeLib = typeLib;
        this.address = address;
        this.director = director;
    }

    public LibraryJpa(Long id, TypeLib typeLib, AddressJpa address, DirectorJpa director, List<BookJpa> books) {
        this.id = id;
        this.typeLib = typeLib;
        this.address = address;
        this.director = director;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public TypeLib getTypeLib() {
        return typeLib;
    }

    public AddressJpa getAddress() {
        return address;
    }

    public DirectorJpa getDirector() {
        return director;
    }

    public List<BookJpa> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LibraryJpa.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("typeLib=" + typeLib)
                .add("address=" + address)
                .add("director=" + director)
                .add("books=" + books)
                .toString();
    }
}
