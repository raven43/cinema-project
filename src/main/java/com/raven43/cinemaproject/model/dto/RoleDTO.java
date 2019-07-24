package com.raven43.cinemaproject.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    private Long itemId;
    private String description;

    @JsonIgnore
    public String toEncodeString() {
        return URLEncoder.encode("{\"itemId\": " + itemId + ", \"description\": \"" + description + "\"}", StandardCharsets.UTF_8);
    }
}