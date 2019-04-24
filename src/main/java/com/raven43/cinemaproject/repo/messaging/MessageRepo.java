package com.raven43.cinemaproject.repo.messaging;

import com.raven43.cinemaproject.model.messaging.Chat;
import com.raven43.cinemaproject.model.messaging.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MessageRepo extends PagingAndSortingRepository<Message, Long> {

    Page<Message> getByChat(Chat chat, Pageable pageable);
}
