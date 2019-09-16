package com.raven43.cinemaproject.service;

import com.raven43.cinemaproject.exception.NoSuchUserException;
import com.raven43.cinemaproject.exception.UserAlreadyExistException;
import com.raven43.cinemaproject.model.domain.User;
import com.raven43.cinemaproject.model.request.UserRegisterRequest;
import com.raven43.cinemaproject.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
    }

    public User registerNewUser(UserRegisterRequest request) {
        User user = new User();
        if (userRepo.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistException();
        }
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        if (userRepo.count() == 0) {
            user.setRoles(Set.of(User.Role.values()));
        } else {
            user.setRoles(Set.of(User.Role.USER));
        }
        return userRepo.save(user);
    }

    public User getUser(Long id) {
        return userRepo.findById(id).orElseThrow(NoSuchUserException::new);
    }
}
