package com.raven43.cinemaproject.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonUpdateRequest extends PersonCreateRequest {

    @NotNull
    private Long id;
}
