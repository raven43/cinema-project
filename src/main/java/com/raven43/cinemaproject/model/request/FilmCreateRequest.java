package com.raven43.cinemaproject.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.Set;

@Data
public class FilmCreateRequest {

    @NotBlank
    @Length(max = 128)
    private String name;

    private MultipartFile image;
    @Length(max = 4096)
    private String description;
    @Nullable
    private Date premiere;
    @Valid
    private Set<RoleRequest> roles;
}
