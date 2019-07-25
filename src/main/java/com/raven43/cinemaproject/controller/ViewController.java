package com.raven43.cinemaproject.controller;

import com.raven43.cinemaproject.exception.NoSuchFilmException;
import com.raven43.cinemaproject.exception.NoSuchPersonException;
import com.raven43.cinemaproject.model.comment.Comment;
import com.raven43.cinemaproject.model.comment.Topic;
import com.raven43.cinemaproject.model.domain.Film;
import com.raven43.cinemaproject.model.domain.Person;
import com.raven43.cinemaproject.model.domain.User;
import com.raven43.cinemaproject.repo.FilmRepo;
import com.raven43.cinemaproject.repo.PersonRepo;
import com.raven43.cinemaproject.repo.comment.CommentRepo;
import com.raven43.cinemaproject.repo.comment.TopicRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ViewController {

    private final FilmRepo filmRepo;
    private final PersonRepo personRepo;
    private final CommentRepo commentRepo;
    private final TopicRepo topicRepo;

    @GetMapping("/films")
    public String getFilms(
            @RequestParam(required = false, defaultValue = "") String str,
            Pageable pageable,
            Model model
    ) {
        Page<Film> page = filmRepo.findByNameContains(str, pageable);
        model.addAttribute("page", page);
        return "view/films";
    }

    @GetMapping("/films/{id}")
    public String getFilm(
            @PathVariable Long id,
            Model model
    ) {
        Film item = filmRepo.findById(id).orElseThrow(() -> new NoSuchFilmException(id));
        model.addAttribute("item", item);
        return "view/film";
    }

    @GetMapping("/films/{id}/comments")
    public String getFilmComments(
            @PathVariable Long id,
            Pageable pageable,
            Model model
    ) {
        Film film = filmRepo.findById(id).orElseThrow(() -> new NoSuchFilmException(id));
        Page<Comment> page = commentRepo.getByTopic(film.getTopic(), pageable);
        model.addAttribute("page", page);
        model.addAttribute("entity", film);
        model.addAttribute("type", "films");
        return "view/topic";
    }

    @PostMapping("/films/{id}/comments")
    public String postFilmComments(
            @PathVariable Long id,
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            Pageable pageable,
            Model model
    ) {
        Film film = filmRepo.findById(id).orElseThrow(() -> new NoSuchFilmException(id));

        if (film.getTopic() == null) film.setTopic(topicRepo.save(new Topic()));

        Comment comment = new Comment(user, film.getTopic(), text);
        commentRepo.save(comment);
        log.info(comment.toString());

        Page<Comment> page = commentRepo.getByTopic(film.getTopic(), pageable);
        model.addAttribute("entity", film);
        model.addAttribute("page", page);
        model.addAttribute("type", "films");
        return "view/topic";
    }


    @GetMapping("/persons")
    public String getPersons(
            @RequestParam(required = false, defaultValue = "") String str,
            Pageable pageable,
            Model model
    ) {
        Page<Person> page = personRepo.findByNameContains(str, pageable);
        model.addAttribute("page", page);
        return "view/persons";
    }

    @GetMapping("/persons/{id}")
    public String getPerson(
            @PathVariable Long id,
            Model model
    ) {
        Person item = personRepo.findById(id).orElseThrow(() -> new NoSuchPersonException(id));
        model.addAttribute("item", item);
        return "view/person";
    }

    @GetMapping("/persons/{id}/comments")
    public String getPersonComments(
            @PathVariable Long id,
            Pageable pageable,
            Model model
    ) {
        Person person = personRepo.findById(id).orElseThrow(() -> new NoSuchPersonException(id));
        Page<Comment> page = commentRepo.getByTopic(person.getTopic(), pageable);
        model.addAttribute("entity", person);
        model.addAttribute("page", page);
        model.addAttribute("type", "persons");
        return "view/topic";
    }

    @PostMapping("/persons/{id}/comments")
    public String postPersonComments(
            @PathVariable Long id,
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            Pageable pageable,
            Model model
    ) {
        Person person = personRepo.findById(id).orElseThrow(() -> new NoSuchPersonException(id));

        Comment comment = new Comment(user, person.getTopic(), text);
        log.info(comment.toString());
        commentRepo.save(comment);

        Page<Comment> page = commentRepo.getByTopic(person.getTopic(), pageable);
        model.addAttribute("entity", person);
        model.addAttribute("page", page);
        model.addAttribute("type", "persons");
        return "view/topic";
    }


}
