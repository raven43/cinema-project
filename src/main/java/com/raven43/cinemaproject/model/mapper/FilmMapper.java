package com.raven43.cinemaproject.model.mapper;

import com.raven43.cinemaproject.model.domain.Film;
import com.raven43.cinemaproject.model.responese.FilmResponse;
import com.raven43.cinemaproject.model.responese.RoleResponse;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public abstract class FilmMapper {

    @Autowired
    protected RoleMapper roleMapper;

    @Mappings(@Mapping(target = "topicId", source = "topic.id"))
    public abstract FilmResponse map(Film film);

    @AfterMapping
    protected void setRoles(Film film, @MappingTarget FilmResponse filmResponse) {
        Set<RoleResponse> roleResponseSet = film.getRoles()
                .stream()
                .map(roleMapper::map)
                .collect(Collectors.toSet());
        filmResponse.setRoles(roleResponseSet);
    }
}
