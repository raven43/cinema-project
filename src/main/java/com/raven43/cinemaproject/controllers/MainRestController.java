package com.raven43.cinemaproject.controllers;

import com.raven43.cinemaproject.model.Film;
import com.raven43.cinemaproject.model.Person;
import com.raven43.cinemaproject.model.Role;
import com.raven43.cinemaproject.repo.FilmRepo;
import com.raven43.cinemaproject.repo.PersonRepo;
import com.raven43.cinemaproject.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class MainRestController {

    private final FilmRepo filmRepo;
    private final PersonRepo personRepo;
    private final RoleRepo roleRepo;

    @GetMapping("/films")
    public Iterable<Film> getFilms(
            @PageableDefault(size = 5) Pageable pageable
    ) {
        return filmRepo.findAll(pageable);
    }

    @GetMapping("/persons")
    public Iterable<Person> getPersons(
            @PageableDefault(size = 5) Pageable pageable
    ) {
        return personRepo.findAll(pageable);
    }

    @PostMapping("/films")
    public HttpStatus postFilm(
            @Valid Film film
    ) {
        filmRepo.save(film);
        return HttpStatus.OK;
    }

    @PostMapping("/persons")
    public HttpStatus postPerson(
            @Valid Person person
    ) {
        personRepo.save(person);
        return HttpStatus.OK;
    }

    @PostMapping("/role")
    public HttpStatus postPerson(
            @RequestParam Long film_id,
            @RequestParam Long person_id,
            @Valid Role role
    ) {
        role.setFilm(filmRepo.findById(film_id).get());
        role.setPerson(personRepo.findById(person_id).get());
        System.out.println(role);
        roleRepo.save(role);
        return HttpStatus.OK;
    }


}
