package com.raven43.cinemaproject.controllers;

import com.raven43.cinemaproject.model.User;
import com.raven43.cinemaproject.repo.FilmRepo;
import com.raven43.cinemaproject.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserRepo userRepo;
    private final FilmRepo filmRepo;

    @GetMapping("/")
    public String index(
            Model model
    ) {
        model.addAttribute("page", filmRepo.getRandom(PageRequest.of(0, 4)));
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    String register() {
        return "register";
    }

    @PostMapping("/register")
    String addNewUser(
            User user,
            Model model
    ) {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            model.addAttribute("message", "user exist!");
            return "register";
        }
        if (userRepo.count() == 0)
            user.setRoles(Arrays.stream(User.Role.values()).collect(Collectors.toSet()));
        else
            user.setRoles(Collections.singleton(User.Role.USER));
        userRepo.save(user);
        model.addAttribute("message", "Пользователь зарегистрирован");
        return "redirect:/login";
    }


}
