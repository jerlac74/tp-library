package fr.training.spring.library.domain.library;


import java.util.List;

public interface ILibraryRepository {
    Library save(Library library);
    void deleteById(Long id);

    List<Library> findAll();

    Library findById(Long id);
    List<Library> findByTypeLib(TypeLib typelib);

    List<Library> findByDirector_SurnameIgnoreCase(String surname);

    List<Library> searchByDirectorNameQuery(String surname);

    List<Library> searchByDirectorNameNativeQuery(String surname);
}
