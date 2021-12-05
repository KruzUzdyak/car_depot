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

public class RoleDAOImpl {

    private static final String FIND_ROLE_BY_USER_ID_QUERY = String.format(
            "SELECT * FROM %s AS u JOIN %s r ON u.%s = r.%s WHERE u.%s = ?;",
            Table.USERS, Table.ROLES, Column.USERS_ROLE_ID, Column.ROLES_ROLE_ID, Column.USERS_ID);

    Role findRoleByUserId(int userId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Role role;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_ROLE_BY_USER_ID_QUERY);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            resultSet.next();
            role = BuilderFactory.getRoleBuilder().build(resultSet);
        }catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPoolException when try to find role by user id.", e);
        } catch (SQLException e) {
            throw new DAOException("SQLException when try to find role by user id.", e);
        } finally {
            try {
                ConnectionPool.getInstance().closeConnection(connection, statement, resultSet);
            } catch (ConnectionPoolException e) {
                throw new DAOException(e);
            }
        }
        return role;
    }
}
