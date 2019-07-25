package com.raven43.cinemaproject.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.Set;

@Data
public class PersonCreateRequest {

    @Length(max = 128)
    private String name;
    private MultipartFile image;
    @Length(max = 4096)
    private String description;
    private Date birth;
    private Set<RoleRequest> roles;
}
