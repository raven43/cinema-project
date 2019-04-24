package com.raven43.cinemaproject.services;

import com.raven43.cinemaproject.model.User;
import com.raven43.cinemaproject.model.messaging.Chat;
import com.raven43.cinemaproject.model.messaging.Message;
import com.raven43.cinemaproject.repo.messaging.ChatRepo;
import com.raven43.cinemaproject.repo.messaging.MessageRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MessageService {

    public static final Logger log = LoggerFactory.getLogger(MessageService.class);

    private final MessageRepo messageRepo;
    private final ChatRepo chatRepo;


    @Autowired
    public MessageService(
            MessageRepo messageRepo,
            ChatRepo chatRepo
    ) {
        this.messageRepo = messageRepo;
        this.chatRepo = chatRepo;
    }

    public boolean existsChat(User... users) {
        log.info("Exists chat start...");
        for (Chat chat : users[0].getChats()) {
            log.warn("chat : "+chat);
            log.info("chat.getUsers().containsAll(Arrays.asList(users)) : "+chat.getUsers().containsAll(Arrays.asList(users)));
            log.info("chat.getUsers().size() : "+chat.getUsers().size());
            log.info("users.length : "+users.length);
            if (chat.getUsers().containsAll(Arrays.asList(users))
                    && chat.getUsers().size() == users.length) {
                return true;
            }
        }
        return false;
    }


    public boolean openChat(User sender, User receiver) {
        if (!existsChat(sender, receiver)) {
            Chat chat = new Chat(sender, receiver);
            log.info("Existing "+chat.toString());
            chatRepo.save(chat);
            return true;
        } else {
            log.info("New "+chatRepo.getByUsers(Arrays.asList(sender, receiver)).toString());
            return false;
        }
    }

    public void handleMessage(User user, Chat chat, String text) {
        Message message = new Message(user, chat, text);
        messageRepo.save(message);
    }

    public void handleMessage(User user, Long chatId, String text) {
        Chat chat = chatRepo.findById(chatId).orElseThrow();
        Message message = new Message(user, chat, text);
        messageRepo.save(message);
    }

    public Page<Message> getChatPage(Chat chat, Pageable pageable) {
        return messageRepo.getByChat(chat, pageable);
    }

    public Page<Message> getChatPage(Long chatId, Pageable pageable) {
        return messageRepo.getByChat(chatRepo.findById(chatId).orElseThrow(), pageable);
    }

    public Page<Chat> getChats(User user, Pageable pageable) {
        return chatRepo.getByUsersContaining(user, pageable);
    }

}
