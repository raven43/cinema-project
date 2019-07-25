package com.raven43.cinemaproject.repo;

import com.raven43.cinemaproject.model.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CustomRepo<User, Long> {

    User findByUsername(String username);

    Page<User> getByUsernameContains(String str, Pageable pageable);

    boolean existsByUsername(String username);

//    @Query("select u from User where u in (select m.receiver from Message where m.sender = ?1)")
//    Page<User> getInterlocutors(User user, Pageable pageable);
}