package com.raven43.cinemaproject.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RoleDTO {

    private Long itemId;
    private String description;

    public RoleDTO() {
    }

    public RoleDTO(Long itemId, String description) {
        this.itemId = itemId;
        this.description = description;
    }


    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public String toEncodeString(){
        return URLEncoder.encode("{\"itemId\": " + itemId +", \"description\": \"" + description + "\"}", StandardCharsets.UTF_8);
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "itemId=" + itemId +
                ", description='" + description + '\'' +
                '}';
    }
}