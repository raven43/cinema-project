package com.raven43.cinemaproject.model.mapper;

import com.raven43.cinemaproject.model.domain.Film;
import com.raven43.cinemaproject.model.responese.FilmResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public abstract class FilmMapper {

    @Mapping(target = "topicId", source = "topic.id")
    public abstract FilmResponse map(Film film);
}
