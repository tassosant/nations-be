package com.example.demo.api.error;

public class ApplicationException extends RuntimeException{
    public ApplicationException(String message){
        super(message);
    }
}

