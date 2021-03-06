package com.raven43.cinemaproject.model.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raven43.cinemaproject.model.domain.Film;
import com.raven43.cinemaproject.model.domain.Person;
import com.raven43.cinemaproject.model.domain.Role;
import com.raven43.cinemaproject.repo.FilmRepo;
import com.raven43.cinemaproject.repo.PersonRepo;
import com.raven43.cinemaproject.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoleMapper {

    private final ObjectMapper mapper = new ObjectMapper();

    private final RoleRepo roleRepo;
    private final FilmRepo filmRepo;
    private final PersonRepo personRepo;

    public Role getRole(Film film, RoleDTO dto) {

        Person person = personRepo.findById(dto.getItemId()).orElseThrow();
        return new Role(film, person, dto.getDescription());
    }

    public Role getRole(Person person, RoleDTO dto) {

        Film film = filmRepo.findById(dto.getItemId()).orElseThrow();
        return new Role(film, person, dto.getDescription());
    }

    public Film updateFilmRoles(Film film, String[] roles) throws IOException {

        Set<Role> newRoles = new HashSet<>();
        Set<Role> oldRoles = roleRepo.getByFilm(film);
        if (roles != null) {
            for (String s : roles) {
                String str = URLDecoder.decode(s, StandardCharsets.UTF_8);
                RoleDTO dto = mapper.readValue(str, RoleDTO.class);
                Role role = getRole(film, dto);
                newRoles.add(role);
            }
        }

        for (Role role : newRoles) {
            if (!oldRoles.contains(role)) {
                film.getRoles().add(role);
            }
        }

        for (Role role : oldRoles) {
            if (!newRoles.contains(role)) {
                film.getRoles().remove(role);
                roleRepo.delete(role);
            }
        }
        return filmRepo.save(film);
    }

    public Person updatePersonRoles(Person person, String[] roles) throws IOException {

        Set<Role> newRoles = new HashSet<>();
        Set<Role> oldRoles = roleRepo.getByPerson(person);
        if (roles != null) {
            for (String s : roles) {
                RoleDTO dto = mapper.readValue(URLDecoder.decode(s, StandardCharsets.UTF_8), RoleDTO.class);
                Role role = getRole(person, dto);
                newRoles.add(role);
            }
        }

        for (Role role : newRoles) {
            if (!oldRoles.contains(role)) {
                person.getRoles().add(role);
            }
        }

        for (Role role : oldRoles) {
            if (!newRoles.contains(role)) {
                person.getRoles().remove(role);
                roleRepo.delete(role);
            }
        }
        return personRepo.save(person);
    }

}
