package com.raven43.cinemaproject.model.mapper;

import com.raven43.cinemaproject.model.domain.Person;
import com.raven43.cinemaproject.model.responese.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public abstract class PersonMapper {

    @Mapping(target = "topicId", source = "topic.id")
    public abstract PersonResponse map(Person person);
}
