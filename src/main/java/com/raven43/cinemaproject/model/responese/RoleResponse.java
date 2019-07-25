package com.raven43.cinemaproject.model.responese;

import lombok.Data;

@Data
public class RoleResponse {

    private Long filmId;
    private String filmName;
    private Long personId;
    private String personName;
    private String description;
}
