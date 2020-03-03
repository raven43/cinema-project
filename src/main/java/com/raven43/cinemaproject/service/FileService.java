package com.raven43.cinemaproject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    @Value("${app.upload.path}")
    private String path;

    public String upload(@NotNull MultipartFile file) throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }

        StringBuilder resultFileName = new StringBuilder();
        resultFileName
                .append(UUID.randomUUID().toString())
                .append(".")
                .append(file.getOriginalFilename());

        String result = (resultFileName.length() > 128)
                ? resultFileName.substring(resultFileName.length() - 128, resultFileName.length() - 1)
                : resultFileName.toString();

        file.transferTo(new File(dir.getAbsolutePath() + "/" + result));
        return result;
    }
}
