package com.epam.volodko.dao.database;

import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testNUmberOfConnections() throws ConnectionPoolException, SQLException {
        int numberOfConnections = Integer.parseInt(DBResourceManager.getInstance().getValue(DBParameter.DB_POOL_SIZE));
        List<Connection> connections = new ArrayList<>();
        for (int i=0; i<numberOfConnections; i++){
            connections.add(pool.takeConnection());
        }
        for (Connection con : connections){
            assertTrue(con.isValid(10));
        }
        connections.get(0).close();
        connections.add(pool.takeConnection());
        assertTrue(connections.get(connections.size()-1).isValid(10));
    }

    @Before
    public void init() throws ConnectionPoolException {
        pool = ConnectionPool.getInstance();
    }


}