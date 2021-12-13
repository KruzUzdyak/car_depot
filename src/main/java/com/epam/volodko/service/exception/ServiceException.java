package com.epam.volodko.service.exception;

public class ServiceException extends Exception{
    private final static long serialVersionUID = 1L;

    public ServiceException(String message, Exception e){
        super(message, e);
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Exception e){
        super(e);
    }
}
