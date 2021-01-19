package fr.training.spring.library.domain.library;

import java.util.StringJoiner;

public class Address {
    private int number;
    private String street;
    private int postalCode;
    private String city;

    Address() {
    }

    public Address(int number, String street, int postalCode, String city) {
        this.number = number;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

    public int getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]")
                .add("number=" + number)
                .add("street='" + street + "'")
                .add("postalCode=" + postalCode)
                .add("city='" + city + "'")
                .toString();
    }

}
