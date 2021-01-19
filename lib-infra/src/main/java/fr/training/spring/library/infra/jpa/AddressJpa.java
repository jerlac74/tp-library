package fr.training.spring.library.infra.jpa;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

@Embeddable
public class AddressJpa {
    @NotNull
    private int number;
    @NotNull
    private String street;
    @NotNull
    private int postalCode;
    @NotNull
    private String city;

    AddressJpa() {
    }

    public AddressJpa(int number, String street, int postalCode, String city) {
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
        return new StringJoiner(", ", AddressJpa.class.getSimpleName() + "[", "]")
                .add("number=" + number)
                .add("street='" + street + "'")
                .add("postalCode=" + postalCode)
                .add("city='" + city + "'")
                .toString();
    }


}
