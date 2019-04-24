package com.raven43.cinemaproject.controllers.rest;

import com.raven43.cinemaproject.model.User;
import com.raven43.cinemaproject.model.messaging.Chat;
import com.raven43.cinemaproject.model.messaging.Message;
import com.raven43.cinemaproject.repo.UserRepo;
import com.raven43.cinemaproject.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/im")
public class MessageRestController {

    private final MessageService messageService;
    private final UserRepo userRepo;

    @Autowired
    public MessageRestController(
            MessageService messageService,
            UserRepo userRepo
    ) {
        this.messageService = messageService;
        this.userRepo = userRepo;
    }

    @GetMapping
    public Page<Chat> getChats(
            @AuthenticationPrincipal User user,
            Pageable pageable
    ) {
        return messageService.getChats(user, pageable);
    }

    @PostMapping("/open")
    public void openChat(
            @AuthenticationPrincipal User sender,
            @RequestParam Long id
    ) {
        User user = userRepo.findById(id).orElseThrow();
        messageService.openChat(sender, user);
    }

    @GetMapping("/{chatId}")
    public Page<Message> getMessages(
            @PathVariable Long chatId,
            @AuthenticationPrincipal User user,
            Pageable pageable
    ) {
        return messageService.getChatPage(chatId, pageable);
    }

    @PostMapping("/{chatId}")
    public void postMessage(
            @PathVariable Long chatId,
            @AuthenticationPrincipal User user,
            @RequestParam String text
    ) {
        messageService.handleMessage(user, chatId, text);
    }

}
