package com.raven43.cinemaproject.controller;

import com.raven43.cinemaproject.model.domain.Film;
import com.raven43.cinemaproject.model.domain.Person;
import com.raven43.cinemaproject.model.domain.User;
import com.raven43.cinemaproject.service.admin.ContentAdministrationService;
import com.raven43.cinemaproject.service.admin.UserAdministrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static java.util.Objects.nonNull;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final ContentAdministrationService contentAdministrationService;
    private final UserAdministrationService userAdministrationService;

    @GetMapping
    public String main() {
        return "admin/main";
    }

    @GetMapping("/film")
    public ModelAndView getEditFilmPage(
            @RequestParam(required = false) Long id,
            ModelAndView modelAndView
    ) {
        if (nonNull(id)) {
            modelAndView.addObject("item", contentAdministrationService.getFilm(id));
        }
        modelAndView.setViewName("admin/film");
        return modelAndView;
    }

    @GetMapping("/v2/film")
    public String getEditFilmPage() {
        return "admin_v2/film";
    }

    @PostMapping("/film")
    public String postEditFilmPage(
            @Valid Film film,
            @RequestParam(name = "sroles", required = false) String[] roles,
            @RequestParam(required = false) MultipartFile file,
            Model model
    ) {

        contentAdministrationService.updateOrCreateFilm(film, roles, file);
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
        if (nonNull(id)) model.addAttribute("item", contentAdministrationService.getPerson(id));
        return "admin/person";
    }

    @PostMapping("/person")
    public String editPerson(
            @Valid Person person,
            @RequestParam(name = "sroles", required = false) String[] roles,
            @RequestParam(required = false) MultipartFile file,
            Model model
    ) {

        contentAdministrationService.updateOrCreatePerson(person, roles, file);
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
        model.addAttribute("page", userAdministrationService.getUsers(str, pageable));
        model.addAttribute("str", str);
        return "admin/users";
    }

    @GetMapping("/users/{id}")
    public String getUser(
            @PathVariable Long id,
            Model model
    ) {
        model.addAttribute("item", userAdministrationService.getUser(id));
        return "admin/user";
    }

    @PostMapping("/users/{id}")
    public String editUser(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "false") boolean mod,
            @RequestParam(required = false, defaultValue = "false") boolean adm,
            Model model
    ) {
        User user = userAdministrationService.editUser(id, mod, adm);
        model.addAttribute("item", user);
        model.addAttribute("message", "User " + user.getUsername() + " updated");
        return "admin/user";
    }
}
