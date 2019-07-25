package com.raven43.cinemaproject.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class FilmUpdateRequest extends FilmCreateRequest {

    @NotNull
    private Long id;
}
