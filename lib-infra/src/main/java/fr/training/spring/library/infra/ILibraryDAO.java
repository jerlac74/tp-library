package fr.training.spring.library.infra;

import fr.training.spring.library.domain.library.TypeLib;
import fr.training.spring.library.infra.jpa.LibraryJpa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ILibraryDAO extends CrudRepository<LibraryJpa, Long> {
    List<LibraryJpa> findByTypeLib(TypeLib typelib);

    List<LibraryJpa> findByDirector_SurnameIgnoreCase(String surname);

    @Query("SELECT lib FROM LibraryJpa lib WHERE lib.director.surname = ?1")
    List<LibraryJpa> searchByDirectorNameQuery(String surname);

    @Query(value = "SELECT * FROM LIBRARY WHERE SURNAME = :surname", nativeQuery = true)
    List<LibraryJpa> searchByDirectorNameNativeQuery(String surname);

}
