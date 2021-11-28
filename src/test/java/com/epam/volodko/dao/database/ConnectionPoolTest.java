package com.epam.volodko.dao.database;

import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ConnectionPoolTest {

    private ConnectionPool pool;

    @Test
    public void testConnectionToDatabase()
            throws ConnectionPoolException, SQLException {

        Connection con = pool.takeConnection();
        assertTrue(con.isValid(10));

        pool.dispose();
        assertTrue(con.isClosed());
    }

    @Before
    public void init() throws ConnectionPoolException {
        pool = ConnectionPoolFactory.getConnectionPool();
        pool.initPoolData();
    }


}