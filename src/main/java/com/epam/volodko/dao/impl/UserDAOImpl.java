package com.epam.volodko.dao.impl;

import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final RoleDAOImpl roleDAO = new RoleDAOImpl();

    @Override
    public User findById(int userId) throws DAOException {
        Role role = roleDAO.findRoleByUserId(userId);
        return UserDAOProvider.getAbstractUserDAO(role).findById(userId);
    }

    @Override
    public User findByLogin(String userLogin) throws DAOException {
        Role role = roleDAO.findRoleByUserLogin(userLogin);
        return UserDAOProvider.getAbstractUserDAO(role).findByLogin(userLogin.trim());
    }

    @Override
    public List<User> findUsersByRole(Role role) throws DAOException {
        return UserDAOProvider.getAbstractUserDAO(role).findAll();
    }

    @Override
    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<>();
        Role[] roles = Role.values();
        for (Role role : roles) {
            users.addAll(UserDAOProvider.getAbstractUserDAO(role).findAll());
        }
        return users;
    }

    @Override
    public void saveNewUser(User user) throws DAOException {
        UserDAOProvider.getAbstractUserDAO(user.getRole()).saveNewUser(user);
    }

    @Override
    public void deleteUser(User user) throws DAOException {
        UserDAOProvider.getAbstractUserDAO(user.getRole()).deleteUser(user.getUserId());
    }
}
