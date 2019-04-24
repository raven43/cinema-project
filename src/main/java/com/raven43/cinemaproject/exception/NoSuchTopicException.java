package com.raven43.cinemaproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchTopicException extends NoSuchElementException {

    public NoSuchTopicException() {
        super();
    }

    public NoSuchTopicException(String s) {
        super(s);
    }

    public NoSuchTopicException(Long id) {
        super("Can not find topic "+id);
    }
}
