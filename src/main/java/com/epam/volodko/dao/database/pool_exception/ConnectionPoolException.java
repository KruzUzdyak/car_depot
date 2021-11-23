package com.epam.volodko.dao.database.pool_exception;

public class ConnectionPoolException extends Exception{
    private final static long serialVersionUID = 1L;

    public ConnectionPoolException(String message, Exception e){
        super(message, e);
    }

    public ConnectionPoolException(String message){
        super(message);
    }

    public ConnectionPoolException(Exception e){
        super(e);
    }
}
