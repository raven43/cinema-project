package com.raven43.cinemaproject.service;

import com.raven43.cinemaproject.model.domain.User;
import com.raven43.cinemaproject.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
    }

    public boolean addUser(User user) {
        if (user == null || userRepo.existsByUsername(user.getUsername())) {
            return false;
        }
        user.setRoles(Collections.singleton(User.Role.USER));
        userRepo.save(user);
        return true;
    }
}
