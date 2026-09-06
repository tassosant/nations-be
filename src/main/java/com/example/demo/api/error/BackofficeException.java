package com.example.demo.api.error;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BackofficeException extends ApplicationException implements BackofficeError{

    protected final int code;
    protected final String message;

    public BackofficeException(BackofficeError error) {
        super(error.message());
        this.code = error.code();
        this.message = error.message();
        log.error("BackofficeException: code {}, message: {}", code, message);
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
