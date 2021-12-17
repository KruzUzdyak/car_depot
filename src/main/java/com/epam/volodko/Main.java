package com.epam.volodko;

import com.epam.volodko.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) throws DAOException {
        log.error("error message");
        log.debug("debug message");
        log.info("info message");
        log.warn("warn message");

        try{
            Inner.exceptionMethod();;
        } catch (DAOException e){
            log.error(e);
        }
    }

    static class Inner {

        public static void exceptionMethod() throws DAOException {
            method2();
        }

        public static void method2() throws DAOException {
            throw new DAOException("exception in Inner class.");
        }
    }
}
