package com.raven43.cinemaproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchPersonException extends NoSuchElementException {
    public NoSuchPersonException() {
        super();
    }

    public NoSuchPersonException(String s) {
        super(s);
    }
    public NoSuchPersonException(Long id) {
        super("Can not find person "+id);
    }
}
