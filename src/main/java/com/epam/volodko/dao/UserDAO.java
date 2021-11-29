package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.users.*;

import java.util.List;

public interface UserDAO {

    User retrieveUserById(int userId) throws DAOException;

    User retrieveUserByName(String name) throws DAOException;

    User retrieveUserByLogin(String login) throws DAOException;

    User retrieveUserByPhone(String phone) throws DAOException;

    List<User> retrieveAllUsers() throws DAOException;

    List<User> retrieveAllUsers(Role role) throws DAOException;

    void saveUser(User user) throws DAOException;

    void saveUser(List<User> users) throws DAOException;

    void deleteUser(User user) throws  DAOException;

    void deleteAllUsers() throws DAOException;

    void deleteUserById(int userId) throws DAOException;

    void deleteUserByLogin(String login) throws DAOException;

}
