package com.raven43.cinemaproject.repo;

import com.raven43.cinemaproject.model.domain.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepo extends CustomRepo<Film, Long> {

    Page<Film> findByNameContains(String name, Pageable pageable);

}
