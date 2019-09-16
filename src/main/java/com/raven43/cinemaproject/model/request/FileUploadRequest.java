package com.raven43.cinemaproject.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class FileUploadRequest {

    @NotNull
    private final MultipartFile file;
}
