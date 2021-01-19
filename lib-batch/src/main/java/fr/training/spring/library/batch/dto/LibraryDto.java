package fr.training.spring.library.batch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.training.spring.library.domain.book.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class LibraryDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("typeLib")
    private String typeLib;

    //Adresse
    @JsonProperty("number")
    private int number;
    @JsonProperty("street")
    private String street;
    @JsonProperty("postalCode")
    private int postalCode;
    @JsonProperty("city")
    private String city;

    //director
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("name")
    private String name;

    @JsonProperty("bookDtos")
    private List<BookDto> bookDtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeLib() {
        return typeLib;
    }

    public void setTypeLib(String typeLib) {
        this.typeLib = typeLib;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookDto> getBookDtos() {
        if(bookDtos==null){
            bookDtos = new ArrayList<>();
        }
        return bookDtos;
    }

    public void setBookDtos(List<BookDto> bookDtos) {
        this.bookDtos = bookDtos;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LibraryDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("typeLib='" + typeLib + "'")
                .add("number=" + number)
                .add("street='" + street + "'")
                .add("postalCode=" + postalCode)
                .add("city='" + city + "'")
                .add("surname='" + surname + "'")
                .add("name='" + name + "'")
                .add("bookDtos=" + bookDtos)
                .toString();
    }

    public void addBookDto(BookDto bookDto){
        this.getBookDtos().add(bookDto);
    }
}
