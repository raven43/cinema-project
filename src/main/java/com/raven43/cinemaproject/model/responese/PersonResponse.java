package com.raven43.cinemaproject.model.responese;

import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Data
public class PersonResponse {
    private Long id;
    private String name;
    private String imgName;
    private String description;
    private Date birth;
    private Set<RoleResponse> roles;
    private Long topicId;

}
