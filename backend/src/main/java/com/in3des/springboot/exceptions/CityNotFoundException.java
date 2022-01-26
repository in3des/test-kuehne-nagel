package com.in3des.springboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a city was not found.
 *
 * @author in3des
 *
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CityNotFoundException extends Exception {
    private static final long serialVersionUID = 1105045175631879877L;

    public CityNotFoundException(String message) {
        super(message);
    }

}
