package com.raven43.cinemaproject.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRegisterRequest {
    @Length(max = 32)
    private String username;

    @Length(max = 32)
    private String password;
}
