package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.builder.impl.UserBuilder;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;

import java.sql.*;
import java.util.List;

public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDAO {

    private static final String RETRIEVE_USER_BY_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?;",
            Table.USERS, Column.USERS_ID);
    private static final String UPDATE_USER_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?;",
            Table.USERS, Column.USERS_LOGIN, Column.USERS_PASSWORD, Column.USERS_NAME,
            Column.USERS_PHONE, Column.USERS_ROLE_ID, Column.USERS_ID);

    private final UserBuilder builder = BuilderFactory.getUserBuilder();

    public UserDAOImpl() {
        super(BuilderFactory.getUserBuilder(), Table.USERS, Column.USERS_ID);
    }

    @Override
    public int save(User entity) throws DAOException {
        return 0;
    }

    @Override
    public int update(User entity) throws DAOException {
        return 0;
    }

    @Override
    public User retrieveUserById(int userId) throws DAOException {
        return null;
    }

    @Override
    public User retrieveUserByLogin(String userLogin) {
        return null;
    }

    @Override
    public List<? extends User> retrieveUsersByRole(Role role) {
        return null;
    }

    @Override
    public void updateUser(User user) throws DAOException {

    }

    @Override
    public void saveNewUser(User user) throws DAOException {

    }

    @Override
    public void deleteUserByLogin(String userLogin) throws DAOException {

    }
}
