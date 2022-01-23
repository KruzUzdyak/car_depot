package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.builder.BuilderFactory;
import com.epam.volodko.dao.database.ConnectionPool;
import com.epam.volodko.dao.database.pool_exception.ConnectionPoolException;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.dao.table_name.Column;
import com.epam.volodko.dao.table_name.Table;
import com.epam.volodko.entity.user.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAOImpl extends AbstractDAO{

    private static final String FIND_ROLE_BY_USER_ID_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Column.USERS_ID);
    private static final String FIND_ROLE_BY_USER_LOGIN_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s AS r ON u.%s = r.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ID, Column.USERS_LOGIN);

    Role findRoleByUserId(int userId) throws DAOException {
        Role role = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_ROLE_BY_USER_ID_QUERY);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            resultSet.next();
            role = BuilderFactory.getRoleBuilder().build(resultSet);
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return role;
    }

    public Role findRoleByUserLogin(String userLogin) throws DAOException {
        Role role = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_ROLE_BY_USER_LOGIN_QUERY);
            statement.setString(1, userLogin);
            resultSet = statement.executeQuery();
            resultSet.next();
            role = BuilderFactory.getRoleBuilder().build(resultSet);
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return role;
    }
}
