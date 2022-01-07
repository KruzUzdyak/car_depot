package com.epam.volodko.controller.listener;

import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PoolInitializingListener implements ServletContextListener {

    private final Logger log = LogManager.getLogger(PoolInitializingListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.init();
        } catch (ConnectionPoolException e) {
            log.error("Catching: ", e);
            // TODO: 06.01.2022 Action on failed initialize of ConnectionPool
        }
    }
}
