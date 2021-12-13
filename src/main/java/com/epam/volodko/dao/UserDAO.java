package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;

import java.util.List;

public interface UserDAO {

    String findPasswordByLogin(String login) throws DAOException;

    User findById(int userId) throws DAOException;

    User findByLogin(String login) throws DAOException;

    List<User> findAll() throws DAOException;

    List<User> findByRole(Role role) throws DAOException;

    int saveNew(User user) throws DAOException;

    int deleteUser(User user) throws DAOException;

    int updateNameAndPhone(User user) throws DAOException;

    int updateLogin(String login) throws DAOException;

    int updateUserPassword(String password) throws DAOException;


}
