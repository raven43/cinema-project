package com.raven43.cinemaproject.repo.messaging;

import com.raven43.cinemaproject.model.User;
import com.raven43.cinemaproject.model.messaging.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ChatRepo extends PagingAndSortingRepository<Chat, Long> {

    boolean existsByUsersContaining(Iterable users);

    Page<Chat> getByUsersContaining(User user, Pageable pageable);

    Optional<Chat> getByUsers(Iterable users);

    Optional<Chat> getByUsersContaining(Iterable users);

}
