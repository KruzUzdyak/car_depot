package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;

import java.util.List;

public interface UserDAO<T extends User> {

    String findPasswordByLogin(String login) throws DAOException;

    T findById(int userId) throws DAOException;

    T findByLogin(String login) throws DAOException;

    List<T> findAll() throws DAOException;

    int saveNew(T user) throws DAOException;

    int deleteById(int id) throws DAOException;

    int update(T user) throws DAOException;

    int updateLogin(int userId, String newLogin) throws DAOException;

    int updatePassword(int userId, String newPassword) throws DAOException;

    int saveInfo(T user) throws DAOException;

    int updateInfo(T user) throws DAOException;

}
