package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.Admin;
import com.epam.volodko.entity.user.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl extends AbstractUserDAO<Admin> implements UserDAO<Admin>{

    private static final String FIND_ADMIN_BY_ID_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s JOIN %s ai ON u.%s = ai.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.ADMIN_INFO,
            Column.USERS_ID, Column.ADMIN_INFO_USER_ID, Column.USERS_ID);
    private static final String FIND_ADMIN_BY_LOGIN_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s JOIN %s ai ON u.%s = ai.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.ADMIN_INFO,
            Column.USERS_ID, Column.ADMIN_INFO_USER_ID, Column.USERS_LOGIN);
    private static final String FIND_ALL_ADMINS_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s JOIN %s ai ON u.%s = ai.%s WHERE r.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Table.ADMIN_INFO,
            Column.USERS_ID, Column.ADMIN_INFO_USER_ID, Column.ROLES_ID);
    private static final String SAVE_NEW_ADMIN_INFO_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?);",
            Table.ADMIN_INFO, Column.ADMIN_INFO_USER_ID, Column.ADMIN_INFO_WORKS_SINCE, Column.ADMIN_INFO_NOTE);
    private static final String UPDATE_ADMIN_INFO_QUERY = String.format(
            "UPDATE %s SET %s = ?, %s = ? WHERE %s = ?;",
            Table.ADMIN_INFO, Column.ADMIN_INFO_WORKS_SINCE, Column.ADMIN_INFO_NOTE, Column.ADMIN_INFO_USER_ID);



    @Override
    public Admin findById(int id) throws DAOException {
        Admin admin = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_ADMIN_BY_ID_QUERY);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                admin = BuilderFactory.getAdminBuilder().build(resultSet);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return admin;
    }

    @Override
    public Admin findByLogin(String userLogin) throws DAOException {
        Admin admin = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_ADMIN_BY_LOGIN_QUERY);
            statement.setString(1, userLogin);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                admin = BuilderFactory.getAdminBuilder().build(resultSet);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return admin;
    }

    @Override
    public List<Admin> findAll() throws DAOException {
        List<Admin> admins = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
            statement.setInt(1, Role.ADMIN.getRoleId());
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                admins.add(BuilderFactory.getAdminBuilder().build(resultSet));
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return admins;
    }

    @Override
    public int saveInfo(Admin admin) throws DAOException {
        return processUpdateInfo(admin, SAVE_NEW_ADMIN_INFO_QUERY);
    }

    @Override
    public int updateInfo(Admin admin) throws DAOException {
        return processUpdateInfo(admin, UPDATE_ADMIN_INFO_QUERY);
    }

    private int processUpdateInfo(Admin admin, String query) throws DAOException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1,  admin.getId());
            statement.setLong(2,  admin.getWorksSince().getTime());
            statement.setString(3, admin.getNote());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }
}
