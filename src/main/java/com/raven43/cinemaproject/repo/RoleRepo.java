package com.raven43.cinemaproject.repo;

import com.raven43.cinemaproject.model.domain.Film;
import com.raven43.cinemaproject.model.domain.Person;
import com.raven43.cinemaproject.model.domain.Role;

import java.util.Set;


public interface RoleRepo extends CustomRepo<Role, Long> {

    Set<Role> getByFilm(Film film);

    Set<Role> getByPerson(Person person);
}
