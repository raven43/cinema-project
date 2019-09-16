package com.raven43.cinemaproject.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;

import static java.util.UUID.randomUUID;

@Service
public class FileService {

    @Value("${app.upload.path}")
    private String path;

    @SneakyThrows
    public String upload(@NotNull MultipartFile file) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String resultFileName = randomUUID() + "." + file.getOriginalFilename();
        resultFileName = (resultFileName.length() > 128)
                ? resultFileName.substring(0, 127)
                : resultFileName;
        file.transferTo(new File(dir.getAbsolutePath() + "/" + resultFileName));
        return resultFileName;
    }
}
