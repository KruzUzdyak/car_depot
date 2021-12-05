package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private AdminDAOImpl adminDAO;
    private ClientDAOImpl clientDAO;
    private DriverDAOImpl driverDAO;
    private RoleDAOImpl roleDAO;

    @Override
    public User findById(int userId) throws DAOException {
        initUsersDAO();
        User user;
        try {
            Role role = roleDAO.findRoleByUserId(userId);
            switch (role) {
                case ADMIN -> user = adminDAO.findAdminById(userId);
                case CLIENT -> user = clientDAO.findClientById(userId);
                case DRIVER -> user = driverDAO.findDriverById(userId);
                default -> user = new User();
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException when try to find user by id.", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to find user by id.", e);
        }
        return user;
    }

    @Override
    public List<User> findUsersByRole(Role role) throws DAOException {
        initUsersDAO();
        List<User> users = new ArrayList<>();
        try {
            switch (role) {
                case ADMIN -> users.addAll(adminDAO.findAllAdmins());
                case CLIENT -> users.addAll(clientDAO.findAllClients());
                case DRIVER -> users.addAll(driverDAO.findAllDrivers());
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException when try to find users by role.", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to find users by role.", e);
        }
        return users;
    }

    @Override
    public List<User> findAll() throws DAOException {
        initUsersDAO();
        List<User> users = new ArrayList<>();
        try {
            users.addAll(adminDAO.findAllAdmins());
            users.addAll(clientDAO.findAllClients());
            users.addAll(driverDAO.findAllDrivers());
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException when try to find users by role.", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to find users by role.", e);
        }
        return users;
    }

    void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws DAOException {
        try {
            ConnectionPool.getInstance().closeConnection(connection, statement, resultSet);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    private void initUsersDAO(){
        if (adminDAO == null){
            adminDAO = new AdminDAOImpl();
        }
        if (clientDAO == null){
            clientDAO = new ClientDAOImpl();
        }
        if (driverDAO == null){
            driverDAO = new DriverDAOImpl();
        }
        if (roleDAO == null){
            roleDAO = new RoleDAOImpl();
        }
    }

}
