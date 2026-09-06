package com.example.demo.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidRequestException extends BackofficeException {

    public InvalidRequestException(BackofficeError error) {
        super(error);
    }
}
