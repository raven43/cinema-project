package com.raven43.cinemaproject.controller.rest;

import com.raven43.cinemaproject.model.domain.User;
import com.raven43.cinemaproject.model.mapper.FilmMapper;
import com.raven43.cinemaproject.model.mapper.PersonMapper;
import com.raven43.cinemaproject.model.request.*;
import com.raven43.cinemaproject.model.responese.FilmResponse;
import com.raven43.cinemaproject.model.responese.PersonResponse;
import com.raven43.cinemaproject.service.admin.ContentAdministrationService;
import com.raven43.cinemaproject.service.admin.UserAdministrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("rest/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminRestController {

    private final ContentAdministrationService contentAdministrationService;
    private final UserAdministrationService userAdministrationService;
    private final FilmMapper filmMapper;
    private final PersonMapper personMapper;

    @GetMapping("/film")
    public FilmResponse getEditableFilm(@RequestParam Long id) {
        return filmMapper.map(contentAdministrationService.getFilm(id));
    }

    @PostMapping(value = "/film")
    public FilmResponse createFilm(
            @Valid FilmCreateRequest request,
            @RequestParam(required = false) MultipartFile file
    ) {
        request.setImage(file);
        return filmMapper.map(contentAdministrationService.createFilm(request));
    }

    @PutMapping("/film")
    public FilmResponse updateFilm(
            @Valid FilmUpdateRequest request,
            @RequestParam(required = false) MultipartFile file
    ) {
        request.setImage(file);
        return filmMapper.map(contentAdministrationService.updateFilm(request));
    }

    @GetMapping("/person")
    public PersonResponse getEditablePerson(@RequestParam Long id) {
        return personMapper.map(contentAdministrationService.getPerson(id));
    }

    @PostMapping("/person")
    public PersonResponse createPerson(
            @Valid PersonCreateRequest request,
            @RequestParam(required = false) MultipartFile file
    ) {
        request.setImage(file);
        return personMapper.map(contentAdministrationService.createPerson(request));
    }

    @PutMapping("/person")
    public PersonResponse updatePerson(
            @Valid PersonUpdateRequest request,
            @RequestParam(required = false) MultipartFile file
    ) {
        request.setImage(file);
        return personMapper.map(contentAdministrationService.updatePerson(request));
    }

    @PutMapping("/user")
    public User editUser(@Valid @RequestBody UserUpdateRequest request) {
        return userAdministrationService.updateUser(request);
    }
}
