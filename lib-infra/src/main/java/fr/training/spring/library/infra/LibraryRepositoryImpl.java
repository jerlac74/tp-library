package fr.training.spring.library.infra;

import fr.training.spring.library.domain.library.ILibraryRepository;
import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.domain.library.TypeLib;
import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.domain.exception.NotFoundException;
import fr.training.spring.library.infra.jpa.LibraryJpa;
import fr.training.spring.library.infra.mapper.MapperLibraryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LibraryRepositoryImpl implements ILibraryRepository {
    @Autowired
    private ILibraryDAO libraryDAO;
    @Autowired
    private MapperLibraryJpa mapperLibraryJpa;

    @Override
    public Library save(Library library) {
        if(library!=null) {
            return mapperLibraryJpa.mapToEntity(libraryDAO.save(mapperLibraryJpa.mapToJpa(library)));
        }
        return  null;
    }

    @Override
    public void deleteById(Long id) {
        if(id>0){
            libraryDAO.deleteById(id);
        }
    }

    @Override
    public List<Library> findAll() {
        return mapperLibraryJpa.mapToEntityList((List<LibraryJpa>)libraryDAO.findAll());
    }

    @Override
    public Library findById(Long id) {
        return mapperLibraryJpa.mapToEntity(libraryDAO.findById(id).orElseThrow(()->new NotFoundException("La librairie n'existe pas", ErrorCodes.LIBRARY_NOT_FOUND)));
    }

    @Override
    public List<Library> findByTypeLib(TypeLib typelib) {
        return mapperLibraryJpa.mapToEntityList(libraryDAO.findByTypeLib(typelib));
    }

    @Override
    public List<Library> findByDirector_SurnameIgnoreCase(String surname) {
        if(surname!=null && !surname.isEmpty()){
            return mapperLibraryJpa.mapToEntityList(libraryDAO.findByDirector_SurnameIgnoreCase(surname));
        }
        return null;
    }

    @Override
    public List<Library> searchByDirectorNameQuery(String surname) {
        if(surname!=null && !surname.isEmpty()){
            return  mapperLibraryJpa.mapToEntityList(libraryDAO.searchByDirectorNameQuery(surname));
        }
        return null;
    }

    @Override
    public List<Library> searchByDirectorNameNativeQuery(String surname) {
        if(surname!=null && !surname.isEmpty()){
            return mapperLibraryJpa.mapToEntityList(libraryDAO.searchByDirectorNameNativeQuery(surname));
        }
        return null;
    }
}
