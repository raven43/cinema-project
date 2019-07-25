package com.raven43.cinemaproject.model.mapper;

import com.raven43.cinemaproject.model.domain.Person;
import com.raven43.cinemaproject.model.responese.PersonResponse;
import com.raven43.cinemaproject.model.responese.RoleResponse;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public abstract class PersonMapper {

    @Autowired
    protected RoleMapper roleMapper;

    @Mappings(@Mapping(target = "topicId", source = "topic.id"))
    public abstract PersonResponse map(Person person);

    @AfterMapping
    protected void setRoles(Person person, @MappingTarget PersonResponse personResponse) {
        Set<RoleResponse> roleResponseSet = person.getRoles()
                .stream()
                .map(roleMapper::map)
                .collect(Collectors.toSet());
        personResponse.setRoles(roleResponseSet);
    }
}
