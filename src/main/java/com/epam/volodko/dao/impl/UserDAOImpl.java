package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractDAO implements UserDAO {

    private static final String FIND_USER_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s=r.%s WHERE %s=?;",
            Table.USERS, Table.ROLES,
            Column.USERS_ROLE_ID, Column.ROLES_ID, Column.USERS_ID);

    private final RoleDAOImpl roleDAO = new RoleDAOImpl();

    @Override
    public User findById(int userId) throws DAOException {
        User user;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_USER_BY_ID_QUERY);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = BuilderFactory.getUserBuilder().build(resultSet);
            } else {
                throw new DAOException("Can't find user by id.");
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return user;
    }

    @Override
    public User findByLogin(String userLogin) throws DAOException {
        Role role = roleDAO.findRoleByUserLogin(userLogin);
        return UserDAOProvider.getAbstractUserDAO(role).findByLogin(userLogin.trim());
    }

    @Override
    public List<User> findUsersByRole(Role role) throws DAOException {
        return UserDAOProvider.getAbstractUserDAO(role).findAll();
    }

    @Override
    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<>();
        Role[] roles = Role.values();
        for (Role role : roles) {
            users.addAll(UserDAOProvider.getAbstractUserDAO(role).findAll());
        }
        return users;
    }

    @Override
    public int saveNewUser(User user) throws DAOException {
        return UserDAOProvider.getAbstractUserDAO(user.getRole()).saveNewUser(user);
    }

    @Override
    public void deleteUser(User user) throws DAOException {
        UserDAOProvider.getAbstractUserDAO(user.getRole()).deleteUser(user.getUserId());
    }

    @Override
    public void updateUser(User user) throws DAOException {
        UserDAOProvider.getAbstractUserDAO(user.getRole()).updateUser(user);
    }

    @Override
    public void updateUserPassword(User user) throws DAOException {
        if (user.getPassword() == null){
            return;
        }
        UserDAOProvider.getAbstractUserDAO(user.getRole()).updateUserPassword(user);
    }
}
