package com.raven43.cinemaproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchFilmException extends NoSuchElementException {

    public NoSuchFilmException() {
        super();
    }

    public NoSuchFilmException(String s) {
        super(s);
    }

    public NoSuchFilmException(Long id) {
        super("Can not find film " + id);
    }
}
