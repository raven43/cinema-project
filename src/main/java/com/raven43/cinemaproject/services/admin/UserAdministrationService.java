package com.raven43.cinemaproject.services.admin;

import com.raven43.cinemaproject.model.User;
import com.raven43.cinemaproject.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAdministrationService {

    private final UserRepo userRepo;

    public Page<User> getUsers(String str, Pageable pageable) {
        return userRepo.getByUsernameContains(str, pageable);
    }

    public User getUser(Long id) {
        return userRepo.findById(id).orElseThrow();
    }

    public User editUser(Long id, boolean mod, boolean adm) {
        User user = userRepo.findById(id).orElseThrow();
        if (mod) user.getRoles().add(User.Role.MODER);
        else user.getRoles().remove(User.Role.MODER);
        if (adm) user.getRoles().add(User.Role.ADMIN);
        else user.getRoles().remove(User.Role.ADMIN);
        return userRepo.save(user);
    }
}
