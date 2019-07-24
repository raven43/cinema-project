package com.raven43.cinemaproject.controllers.rest;

import com.raven43.cinemaproject.model.Film;
import com.raven43.cinemaproject.model.Person;
import com.raven43.cinemaproject.repo.FilmRepo;
import com.raven43.cinemaproject.repo.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/search")
@RequiredArgsConstructor
public class SearchRestController {

    private final FilmRepo filmRepo;
    private final PersonRepo personRepo;

    @GetMapping("/films")
    public Page<Film> getFilms(
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam(required = false, defaultValue = "") String str
    ) {
        return filmRepo.findByNameContains(str, pageable);
    }

    @GetMapping("/persons")
    public Page<Person> getPersons(
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam(required = false, defaultValue = "") String str
    ) {
        return personRepo.findByNameContains(str, pageable);
    }
}
