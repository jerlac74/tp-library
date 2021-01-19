package fr.training.spring.library.infra.jpa;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

@Embeddable
public class DirectorJpa {
    @NotNull
    private String surname;
    @NotNull
    private String name;

    DirectorJpa() {
    }

    public DirectorJpa(String surname, String name) {
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
        return new StringJoiner(", ", DirectorJpa.class.getSimpleName() + "[", "]")
                .add("surname='" + surname + "'")
                .add("name='" + name + "'")
                .toString();
    }
}
