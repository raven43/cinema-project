package com.raven43.cinemaproject.controller;

import com.raven43.cinemaproject.exception.UserAlreadyExistException;
import com.raven43.cinemaproject.model.domain.User;
import com.raven43.cinemaproject.model.request.UserRegisterRequest;
import com.raven43.cinemaproject.repo.UserRepo;
import com.raven43.cinemaproject.service.FileService;
import com.raven43.cinemaproject.service.MessageService;
import com.raven43.cinemaproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserRepo userRepo;
    private final FileService fileService;
    private final MessageService messageService;
    private final UserService userService;

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(
            @Valid UserRegisterRequest user,
            Model model
    ) {
        try {
            userService.registerNewUser(user);
            model.addAttribute("message", "Пользователь зарегистрирован");
            return "redirect:/login";
        } catch (UserAlreadyExistException e) {
            model.addAttribute("message", "user exist!");
            return "register";
        }
    }

    @GetMapping
    public String profile() {
        return "user/profile";
    }

    @GetMapping("/{id}")
    public String getUserPage(
            @PathVariable Long id,
            @AuthenticationPrincipal User user,
            Model model
    ) {
        User requiredUser = userService.getUser(id);
        if (requiredUser.getId().equals(user.getId())) {
            return "redirect:/profile";
        }
        model.addAttribute("item", requiredUser);
        return "user/page";
    }


    @GetMapping("/edit")
    public String getProfileEditPage(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/edit")
    public String profileEditPost(
            @AuthenticationPrincipal User user,
            @RequestParam String login,
            @RequestParam(required = false) String oldPassword,
            @RequestParam(required = false) String newPassword,
            @RequestParam(required = false) MultipartFile file,
            Model model
    ) throws IOException {
        try {
            user.setUsername(login);
            if (file != null && !file.isEmpty()) {
                String imgName = fileService.upload(file);
                user.setImgName(imgName);
            }
            if (!Objects.equals(oldPassword, "") && !Objects.equals(newPassword, "")) {
                if (user.getPassword().equals(oldPassword)) {
                    user.setPassword(newPassword);
                } else {
                    model.addAttribute("message", "wrong password");
                    model.addAttribute("user", user);
                    return "user/edit";
                }
            }
            userRepo.save(user);
            model.addAttribute("user", user);
            return "redirect:/profile";
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/im")
    public String getIm(
            @AuthenticationPrincipal User user,
            Pageable pageable,
            Model model
    ) {
        model.addAttribute("page", messageService.getChats(user, pageable));
        return "user/im";
    }

    @GetMapping("/im/{id}")
    public String getDialog(
            @PathVariable Long id,
            @AuthenticationPrincipal User user,
            Pageable pageable,
            Model model
    ) {
        model.addAttribute("page", messageService.getChatPage(id, pageable));
        return "user/dialog";
    }

    @PostMapping("/im/{id}")
    public String postMessage(
            @PathVariable Long id,
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            Pageable pageable,
            Model model
    ) {
        messageService.handleMessage(user, id, text);
        model.addAttribute("page", messageService.getChatPage(id, pageable));
        return "user/dialog";
    }

}
