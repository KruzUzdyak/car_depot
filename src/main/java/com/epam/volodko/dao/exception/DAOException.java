package com.epam.volodko.dao.exception;

public class DAOException extends Exception{
    private final static long serialVersionUID = 1L;

    public DAOException(String message, Exception e){
        super(message, e);
    }

    public DAOException(String message){
        super(message);
    }

    public DAOException(Exception e){
        super(e);
    }
}
