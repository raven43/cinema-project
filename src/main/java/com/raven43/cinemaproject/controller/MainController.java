package com.raven43.cinemaproject.controller;

import com.raven43.cinemaproject.repo.FilmRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final FilmRepo filmRepo;

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.addObject("page", filmRepo.getRandom(PageRequest.of(0, 4)));
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


}
