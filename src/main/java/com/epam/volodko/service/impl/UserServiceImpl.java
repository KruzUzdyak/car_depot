package com.epam.volodko.service.impl;

import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.User;
import com.epam.volodko.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public boolean validateUser(User user) {
        return false;
    }

    @Override
    public boolean validateUserPassword(String login, String password) {
        boolean isValidLogin = login != null && !login.isEmpty();
        boolean isValidPassword = password != null && !password.isEmpty();
        if (isValidLogin && isValidPassword){
            String passwordFromDB;
            try {
                UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
                passwordFromDB = userDAO.findUserPasswordByLogin(login);
            } catch (DAOException e) {
                // TODO: 13.12.2021 logging
                return false;
            }
            return password.equals(passwordFromDB);
        }
        return false;
    }

    @Override
    public boolean validatePasswordRepeat(String password, String passwordRepeat) {
        return password != null && !password.isEmpty() && password.equals(passwordRepeat);
    }

    private boolean validatePasswordRestrictions(String password){

        return false;
    }
}
