package com.raven43.cinemaproject.controller.rest;

import com.raven43.cinemaproject.model.request.FileUploadRequest;
import com.raven43.cinemaproject.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@ResponseStatus
@RequestMapping("/rest/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@Valid FileUploadRequest request) {
        return new ResponseEntity<>(fileService.upload(request.getFile()), CREATED);
    }
}
