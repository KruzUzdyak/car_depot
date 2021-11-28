package com.epam.volodko.dao.database;

import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;

public class ConnectionPoolFactory {

    private static ConnectionPoolFactory instance;

    private ConnectionPool pool;

    private ConnectionPoolFactory(){
    }

    public static ConnectionPool getConnectionPool() throws ConnectionPoolException {
        if (instance == null){
            instance = new ConnectionPoolFactory();
        }
        if (instance.pool == null){
            instance.pool = new ConnectionPool();
            instance.pool.initPoolData();
        }
        return instance.pool;
    }
}
