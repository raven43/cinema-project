package com.raven43.cinemaproject.model.mapper;

import com.raven43.cinemaproject.model.domain.Role;
import com.raven43.cinemaproject.model.responese.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "filmId", source = "film.id")
    @Mapping(target = "filmName", source = "film.name")
    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "personName", source = "person.name")
    RoleResponse map(Role role);
}
