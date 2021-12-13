package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;

import java.util.List;

public interface UserDAO {

    String findUserPasswordByLogin(String userLogin) throws DAOException;

    User findById(int userId) throws DAOException;

    User findByLogin(String userLogin) throws DAOException;

    List<User> findAll() throws DAOException;

    List<User> findUsersByRole(Role role) throws DAOException;

    int saveNewUser(User user) throws DAOException;

    void deleteUser(User user) throws DAOException;

    void updateUser(User user) throws DAOException;

    void updateUserPassword(User user) throws DAOException;
}
