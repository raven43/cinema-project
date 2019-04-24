package com.raven43.cinemaproject.controllers;

import com.raven43.cinemaproject.model.User;
import com.raven43.cinemaproject.repo.UserRepo;
import com.raven43.cinemaproject.services.FileService;
import com.raven43.cinemaproject.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/profile")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserRepo userRepo;
    private final FileService fileService;
    private final MessageService messageService;


    @Autowired
    public UserController(
            UserRepo userRepo,
            FileService fileService,
            MessageService messageService
    ) {
        this.userRepo = userRepo;
        this.fileService = fileService;
        this.messageService = messageService;
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
        User requaredUser = userRepo.findById(id).orElseThrow();
        if (requaredUser.equals(user)) return "redirect:/profile";
        model.addAttribute("item", requaredUser);
        return "user/page";
    }


    @GetMapping("/edit")
    public String profileEdit(
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
        messageService.handleMessage(user,id,text);
        model.addAttribute("page", messageService.getChatPage(id, pageable));
        return "user/dialog";
    }

}
