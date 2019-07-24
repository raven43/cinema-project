package com.raven43.cinemaproject.controllers;

import com.raven43.cinemaproject.model.Film;
import com.raven43.cinemaproject.model.Person;
import com.raven43.cinemaproject.model.User;
import com.raven43.cinemaproject.model.dto.RoleMapper;
import com.raven43.cinemaproject.repo.FilmRepo;
import com.raven43.cinemaproject.repo.PersonRepo;
import com.raven43.cinemaproject.repo.UserRepo;
import com.raven43.cinemaproject.repo.comment.TopicRepo;
import com.raven43.cinemaproject.services.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final FilmRepo filmRepo;
    private final PersonRepo personRepo;
    private final FileService fileService;
    private final UserRepo userRepo;
    private final RoleMapper roleMapper;
    private final TopicRepo topicRepo;

    @GetMapping
    public String main() {
        return "admin/main";
    }

    @GetMapping("/film")
    public ModelAndView getEditFilmPage(
            @RequestParam(required = false) Long id,
            ModelAndView modelAndView
    ) {
        if (id != null) modelAndView.addObject("item", filmRepo.findById(id).orElse(null));
        modelAndView.setViewName("admin/film");
        return modelAndView;
    }

    @PostMapping("/film")
    public String postEditFilmPage(
            @Valid Film film,
            @RequestParam(name = "sroles", required = false) String[] roles,
            @RequestParam(required = false) MultipartFile file,
            Model model
    ) throws IOException {

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

        model.addAttribute("message",
                "Film <a class=\"alert-link\" href=\"/films/" + film.getId() +
                        "\">" + film.getName() + "</a> successfully updated");
        model.addAttribute("item", film);

        return "admin/film";
    }

    @GetMapping("/person")
    public String editPerson(
            @RequestParam(required = false) Long id,
            Model model
    ) {
        if (id != null) model.addAttribute("item", personRepo.findById(id).orElse(null));
        return "admin/person";
    }

    @PostMapping("/person")
    public String editPerson(
            @Valid Person person,
            @RequestParam(name = "sroles", required = false) String[] roles,
            @RequestParam(required = false) MultipartFile file,
            Model model
    ) throws IOException {

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

        model.addAttribute("item", person);
        model.addAttribute("message", "Person <a class=\"alert-link\" href=\"/persons/" + person.getId() + "\">" + person.getName() + "</a> successfully updated");
        return "admin/person";
    }

    @GetMapping("/users")
    public String getUsers(
            @RequestParam(required = false, defaultValue = "") String str,
            Pageable pageable,
            Model model
    ) {
        model.addAttribute("page", userRepo.getByUsernameContains(str, pageable));
        model.addAttribute("str", str);
        return "admin/users";
    }

    @GetMapping("/users/{id}")
    public String getUser(
            @PathVariable Long id,
            Pageable pageable,
            Model model
    ) {
        model.addAttribute("item", userRepo.findById(id).orElseThrow());
        return "admin/user";
    }

    @PostMapping("/users/{id}")
    public String editUser(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "false") boolean mod,
            @RequestParam(required = false, defaultValue = "false") boolean adm,
            Model model
    ) {

        User user = userRepo.findById(id).orElseThrow();

        if (mod) user.getRoles().add(User.Role.MODER);
        else user.getRoles().remove(User.Role.MODER);
        if (adm) user.getRoles().add(User.Role.ADMIN);
        else user.getRoles().remove(User.Role.ADMIN);

        userRepo.save(user);
        model.addAttribute("item", user);
        model.addAttribute("message", "User " + user.getUsername() + " updated");
        return "admin/user";
    }


}
