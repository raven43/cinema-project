package com.raven43.cinemaproject.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RoleRequest {

    private Long filmId;
    private Long personId;
    @Length(max = 128)
    private String description;
}
