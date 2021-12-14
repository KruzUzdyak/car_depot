package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.Admin;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl extends UserDAOImpl {

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
            "INSERT INTO %s (%s, %s, %s) VALUES ((SELECT %s FROM %s WHERE %s = ?), ?, ?);",
            Table.ADMIN_INFO, Column.ADMIN_INFO_USER_ID, Column.ADMIN_INDO_WORKS_SINCE, Column.ADMIN_INFO_NOTE,
            Column.USERS_ID, Table.USERS, Column.USERS_LOGIN);
    private static final String UPDATE_ADMIN_INFO_QUERY = String.format(
            "UPDATE %s SET %s = ?, %s = ? WHERE %s = ?;",
            Table.ADMIN_INFO, Column.ADMIN_INDO_WORKS_SINCE, Column.ADMIN_INFO_NOTE, Column.ADMIN_INFO_USER_ID);



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
    public List<User> findAll() throws DAOException {
        List<User> admins = new ArrayList<>();
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
    public List<User> findByRole(Role role) throws DAOException {
        throw new UnsupportedOperationException("Use UserDAO for this operation.");
    }
    // TODO: 14.12.2021 refactor userDAO hierarchy
/* 
    @Override
    public int saveNew(User admin) throws DAOException {
        Admin adminForSave = (Admin) admin;
        Connection connection = null;
        PreparedStatement statement = null;
        int rowsAffected;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SAVE_NEW_USER_QUERY);
            prepareSaveUserStatement(adminForSave, statement);
            statement.executeUpdate();
            statement = connection.prepareStatement(SAVE_NEW_ADMIN_INFO_QUERY);
            statement.setString(1, adminForSave.getLogin());
            statement.setLong(2, adminForSave.getWorksSince().getTime());
            statement.setString(3, adminForSave.getNote());
            rowsAffected = statement.executeUpdate();
            int userId = getLastAddedUserId(adminForSave, connection, statement);
            adminForSave.setUserId(userId);
            connection.commit();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when saving new admin.", e);
        } finally {
            closeConnection(connection, statement);
        }
        return rowsAffected;
    }

    @Override
    void updateUser(Admin admin) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(UPDATE_USER_QUERY);
            prepareUpdateUserStatement(admin, statement);
            statement.executeUpdate();
            statement = connection.prepareStatement(UPDATE_ADMIN_INFO_QUERY);
            statement.setLong(1, admin.getWorksSince().getTime());
            statement.setString(2, admin.getNote());
            statement.setInt(3, admin.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when updating admin.", e);
        } finally {
            closeConnection(connection, statement);
        }
    }
*/

}
