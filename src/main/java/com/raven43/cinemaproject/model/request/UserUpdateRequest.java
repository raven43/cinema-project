package com.raven43.cinemaproject.model.request;

import com.raven43.cinemaproject.model.domain.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class UserUpdateRequest {

    @NotNull
    private Long id;
    private Set<User.Role> roles;
}
