package fr.training.spring.library.infra.mapper;

import java.util.List;

public interface IMapperJpa<T,S> {
    S mapToEntity(T t);
    T mapToJpa(S s);

    List<S> mapToEntityList(List<T> t);
    List<T> mapToJpaList(List<S> s);
}
