package com.raven43.cinemaproject.repo;

import com.raven43.cinemaproject.model.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepo extends JpaRepository<FileInfo, Long> {
}
