package fr.training.spring.library.domain.library;

import java.util.StringJoiner;


public class Director {
    private String surname;
    private String name;

    Director() {
    }

    public Director(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Director.class.getSimpleName() + "[", "]")
                .add("surname='" + surname + "'")
                .add("name='" + name + "'")
                .toString();
    }
}
