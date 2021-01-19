package fr.training.spring.library.expo;

import fr.training.spring.library.appli.ILibraryService;
import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.domain.library.TypeLib;
import fr.training.spring.library.expo.dto.LibraryDto;
import fr.training.spring.library.expo.dto.MapperDtoToEntity;
import fr.training.spring.library.expo.dto.MapperEntityToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.StringJoiner;

@RestController
@RequestMapping(value = "/api")
public class LibraryResources {
    @Autowired
    private ILibraryService libraryService;

    @PostMapping(value = "/library")
    public Long create(@Validated @RequestBody LibraryDto libraryDto){
        Library library = MapperDtoToEntity.mapToLibrary(libraryDto);
        Library libraryAdded =libraryService.save(library);

        return libraryAdded.getId();
    }

    @PutMapping(value = "/update")
    public String update(@Validated @RequestBody LibraryDto libraryToUpd){
        Library library = MapperDtoToEntity.mapToLibrary(libraryToUpd);
        Library libraryUpdated = libraryService.update(library);
        if(libraryUpdated!=null){
            return libraryUpdated.toString();
        }
        else {
            return "la bibliothèque transmise n'existe pas et ne peut pas être mise à jour";
        }
    }

    @GetMapping(value = "/{id}")
    public LibraryDto findById(@PathVariable(value = "id") Long id){
        /*
        on n'a plus besoin de gérer l'exception car on utilise notre classe CustomExceptionHandler
        try {
            Library library = libraryService.findById(id);
            return library.toString();
        }
        catch(LibraryNotFoundException exp){
            return exp.getMessage();
        }*/
        Library library = libraryService.findById(id);
        return MapperEntityToDto.mapToLibraryDto(library);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable(value = "id") Long id){
        libraryService.deleteById(id);

        return "Deletion OK";
    }

    @GetMapping(value = "/findall")
    public List<LibraryDto> findAll(){

        return MapperEntityToDto.mapToListLibraryDto(libraryService.findAll());
    }

    @GetMapping(value = "/findByType/{type}")
    public String findByType(@PathVariable(value = "type") String typeString){
        TypeLib typeLib=null;
        if(typeString!=null) {
            if (typeString.equals(TypeLib.Associative.name())) {
                typeLib = TypeLib.Associative;
            } else if (typeString.equals(TypeLib.Nationale.name())) {
                typeLib = TypeLib.Nationale;
            } else if (typeString.equals(TypeLib.Publique.name())) {
                typeLib = TypeLib.Publique;
            } else if (typeString.equals(TypeLib.Scolaire.name())) {
                typeLib = TypeLib.Scolaire;
            } else if (typeString.equals(TypeLib.Universitaire.name())) {
                typeLib = TypeLib.Universitaire;
            }
        }

        if(typeLib!=null){
            return MapperEntityToDto.mapToListLibraryDto(libraryService.findByType(typeLib)).toString();
        }
        else {
            return new StringJoiner(", ", "Le type transmis n'est pas valide, attendu :", "")
                    .add(TypeLib.Associative.name())
                    .add(TypeLib.Nationale.name())
                    .add(TypeLib.Publique.name())
                    .add(TypeLib.Scolaire.name())
                    .add(TypeLib.Universitaire.name())
                    .toString();
        }
    }

    @GetMapping(value = "/findDirector/{surname}")
    public List<LibraryDto> findByDirectorSurname(@Validated @PathVariable(value = "surname") String surname){
        return MapperEntityToDto.mapToListLibraryDto(libraryService.findByDirector_Surname(surname.toLowerCase()));
    }

    @GetMapping(value = "/searchDirectorJpql/{surname}")
    public List<LibraryDto> searchfindByDirectorSurnameJpql(@Validated @PathVariable(value = "surname") String surname){
        return MapperEntityToDto.mapToListLibraryDto(libraryService.searchByDirector_SurnameJpql(surname));
    }

    @GetMapping(value = "/searchDirectorNativeSql/{surname}")
    public List<LibraryDto> searchByDirectorSurnameNativeSql(@Validated @PathVariable(value = "surname") String surname){
        return MapperEntityToDto.mapToListLibraryDto(libraryService.searchByDirector_SurnameNativeSql(surname));
    }

}
