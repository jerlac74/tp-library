package fr.training.spring.library.appli;

import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.domain.library.TypeLib;

import java.util.List;

public interface ILibraryService {
    Library save(Library library);

    Library update(Library library);

    void deleteById(Long id);

    Library findById(Long id);

    List<Library> findAll();

    List<Library> findByType(TypeLib typeLib);

    List<Library> findByDirector_Surname(String surname);

    List<Library> searchByDirector_SurnameJpql(String surname);

    List<Library> searchByDirector_SurnameNativeSql(String surname);
}
