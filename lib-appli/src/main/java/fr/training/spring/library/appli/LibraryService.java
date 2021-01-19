package fr.training.spring.library.appli;

import fr.training.spring.library.domain.library.TypeLib;
import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.domain.library.ILibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibraryService implements ILibraryService{
    @Autowired
    private ILibraryRepository libraryRepository;

    @Transactional
    public Library save(Library library){
        return libraryRepository.save(library);
    }

    @Transactional
    public Library update(Library library){
        if(library!=null && library.getId()>0) {
            try {
                Library libraryFound = findById(library.getId());
                if(libraryFound!=null) {
                    return libraryRepository.save(library);
                }
            }
            catch (RuntimeException exp){
                return null;
            }
        }
        return null;
    }

    @Transactional
    public void deleteById(Long id){
        libraryRepository.deleteById(id);
    }

    public Library findById(Long id){
        return libraryRepository.findById(id);
    }

    public List<Library> findAll(){
        return (List)libraryRepository.findAll();
    }

    public List<Library> findByType(TypeLib typeLib){
        return libraryRepository.findByTypeLib(typeLib);
    }

    public List<Library> findByDirector_Surname(String surname){
        return  libraryRepository.findByDirector_SurnameIgnoreCase(surname);
    }

    public List<Library> searchByDirector_SurnameJpql(String surname){
        return  libraryRepository.searchByDirectorNameQuery(surname);
    }

    public List<Library> searchByDirector_SurnameNativeSql(String surname){
        return  libraryRepository.searchByDirectorNameNativeQuery(surname);
    }
}
