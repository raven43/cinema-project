package com.raven43.cinemaproject.repo;

import com.raven43.cinemaproject.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends CustomRepo<Person, Long> {
    Page<Person> findByNameContains(String name, Pageable pageable);
}
