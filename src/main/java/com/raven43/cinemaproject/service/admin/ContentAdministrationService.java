package com.raven43.cinemaproject.service.admin;

import com.raven43.cinemaproject.exception.NoSuchFilmException;
import com.raven43.cinemaproject.exception.NoSuchPersonException;
import com.raven43.cinemaproject.model.domain.Film;
import com.raven43.cinemaproject.model.domain.Person;
import com.raven43.cinemaproject.model.dto.RoleMapper;
import com.raven43.cinemaproject.model.request.FilmCreateRequest;
import com.raven43.cinemaproject.model.request.FilmUpdateRequest;
import com.raven43.cinemaproject.model.request.PersonCreateRequest;
import com.raven43.cinemaproject.model.request.PersonUpdateRequest;
import com.raven43.cinemaproject.repo.FilmRepo;
import com.raven43.cinemaproject.repo.PersonRepo;
import com.raven43.cinemaproject.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentAdministrationService {

    private final FilmRepo filmRepo;
    private final PersonRepo personRepo;
    private final FileService fileService;
    private final RoleMapper roleMapper;

    public Film getFilm(Long id) {
        return filmRepo.findById(id).orElseThrow(() -> new NoSuchFilmException(id));
    }

    @SneakyThrows
    public Film updateOrCreateFilm(Film film, String[] roles, MultipartFile file) {
        if (film.getId() != null) film.setTopic(
                filmRepo
                        .findById(film.getId())
                        .orElseThrow()
                        .getTopic()
        );

        if (file != null && !file.isEmpty()) {
            log.info("new file");
            String fileName = fileService.upload(file);
            film.setImgName(fileName);
        }
        film = filmRepo.save(film);

        roleMapper.updateFilmRoles(film, roles);

        return filmRepo.save(film);
    }

    public Film createFilm(FilmCreateRequest filmCreateRequest) {
        return new Film();
    }

    public Film updateFilm(FilmUpdateRequest filmCreateRequest) {
        return new Film();
    }

    public Person getPerson(Long id) {
        return personRepo.findById(id).orElseThrow(() -> new NoSuchPersonException(id));
    }

    @SneakyThrows
    public Person updateOrCreatePerson(Person person, String[] roles, MultipartFile file) {
        if (person.getId() != null) person.setTopic(
                personRepo
                        .findById(person.getId())
                        .orElseThrow()
                        .getTopic()
        );

        if (file != null && !file.isEmpty()) {
            String fileName = fileService.upload(file);
            person.setImgName(fileName);
        }
        person = personRepo.save(person);

        roleMapper.updatePersonRoles(person, roles);
        return person;
    }

    public Person createPerson(PersonCreateRequest personCreateRequest) {
        return new Person();
    }

    public Person updatePerson(PersonUpdateRequest filmUpdateRequest) {
        return null;
    }
}
