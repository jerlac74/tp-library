package fr.training.spring.library.infra.mapper;

import fr.training.spring.library.domain.library.Library;
import fr.training.spring.library.infra.jpa.LibraryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperLibraryJpa implements IMapperJpa<LibraryJpa, Library> {
    @Autowired
    private MapperAddressJpa mapperAddressJpa;
    @Autowired
    private MapperDirectorJpa mapperDirectorJpa;
    @Autowired
    private MapperBookJpa mapperBookJpa;

    @Override
    public Library mapToEntity(LibraryJpa libraryJpa) {
        if(libraryJpa!=null){
            return Library.builder().id(libraryJpa.getId())
                    .address(mapperAddressJpa.mapToEntity(libraryJpa.getAddress()))
                    .typeLib(libraryJpa.getTypeLib())
                    .books(mapperBookJpa.mapToEntityList(libraryJpa.getBooks()))
                    .director(mapperDirectorJpa.mapToEntity(libraryJpa.getDirector()))
                    .build();
        }
        return null;
    }

    @Override
    public LibraryJpa mapToJpa(Library library) {
        if(library!=null){
            return new LibraryJpa(library.getId(), library.getTypeLib(),
                    mapperAddressJpa.mapToJpa(library.getAddress()),
                    mapperDirectorJpa.mapToJpa(library.getDirector()),
                    mapperBookJpa.mapToJpaList(library.getBooks()));
        }
        return null;
    }

    @Override
    public List<Library> mapToEntityList(List<LibraryJpa> libraryJpas) {
        if(libraryJpas!=null){
            List<Library> libraries = new ArrayList<>();
            for (LibraryJpa libraryJpa : libraryJpas) {
                libraries.add(mapToEntity(libraryJpa));
            }
            return libraries;
        }
        return null;
    }

    @Override
    public List<LibraryJpa> mapToJpaList(List<Library> libraries) {
        if(libraries!=null){
            List<LibraryJpa> libraryJpas = new ArrayList<>();
            for (Library library : libraries) {
                libraryJpas.add(mapToJpa(library));
            }
            return libraryJpas;
        }
        return null;
    }
}
