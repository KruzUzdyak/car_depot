package com.epam.volodko.service.exception;

public class UserValidationException extends Exception{
    private final static long serialVersionUID = 1L;

    public UserValidationException(String message, Exception e){
        super(message, e);
    }

    public UserValidationException(String message){
        super(message);
    }

    public UserValidationException(Exception e){
        super(e);
    }
}
