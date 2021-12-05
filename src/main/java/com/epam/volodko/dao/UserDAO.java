package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;

import java.util.List;

public interface UserDAO {

    User findById(int userId) throws DAOException;

    List<User> findAll() throws DAOException;

    List<User> findUsersByRole(Role role) throws DAOException;
}
