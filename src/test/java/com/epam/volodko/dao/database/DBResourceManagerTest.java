package com.epam.volodko.dao.database;

import org.junit.Test;

import java.util.ResourceBundle;

import static org.junit.Assert.*;

public class DBResourceManagerTest {

    @Test
    public void testRequestParameters(){
        ResourceBundle expectedBundle = ResourceBundle.getBundle("db");
        DBResourceManager manager = DBResourceManager.getInstance();

        String expectedDriver = expectedBundle.getString(DBParameter.DB_DRIVER);
        String actualDriver = manager.getValue(DBParameter.DB_DRIVER);

        assertEquals(expectedDriver, actualDriver);
    }
}