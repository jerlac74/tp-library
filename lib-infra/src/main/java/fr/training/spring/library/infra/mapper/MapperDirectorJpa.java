package fr.training.spring.library.infra.mapper;

import fr.training.spring.library.domain.library.Director;
import fr.training.spring.library.infra.jpa.DirectorJpa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperDirectorJpa implements IMapperJpa<DirectorJpa, Director> {
    @Override
    public Director mapToEntity(DirectorJpa directorJpa) {
        if(directorJpa!=null){
            return new Director(directorJpa.getSurname(), directorJpa.getName());
        }
        return null;
    }

    @Override
    public DirectorJpa mapToJpa(Director director) {
        if(director!=null){
            return new DirectorJpa(director.getSurname(), director.getName());
        }
        return null;
    }

    @Override
    public List<Director> mapToEntityList(List<DirectorJpa> directorJpas) {
        if(directorJpas!=null){
            List<Director> directors = new ArrayList<>();
            for (DirectorJpa directorJpa : directorJpas) {
                directors.add(mapToEntity(directorJpa));
            }
            return directors;
        }
        return null;
    }

    @Override
    public List<DirectorJpa> mapToJpaList(List<Director> directors) {
        if(directors!=null){
            List<DirectorJpa> directorJpas = new ArrayList<>();
            for (Director director : directors) {
                directorJpas.add(mapToJpa(director));
            }
            return directorJpas;
        }
        return null;
    }
}
