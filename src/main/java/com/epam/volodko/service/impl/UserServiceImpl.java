package com.epam.volodko.service.impl;

import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.User;
import com.epam.volodko.service.UserService;
import com.epam.volodko.service.exception.ServiceException;
import com.epam.volodko.service.validator.UserValidator;

import javax.servlet.ServletException;

public class UserServiceImpl implements UserService {

    private final UserValidator validator = new UserValidator();

    @Override
    public boolean saveUser(User user) {
        boolean saved = false;
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            userDAO.saveNew(user);
            saved = true;
        } catch (DAOException e) {
            // TODO: 13.12.2021 logging
        }
        return saved;
    }

    @Override
    public boolean validateLogin(String login) {
        return login != null && !login.isEmpty();
    }

    @Override
    public boolean validateLogination(String login, String password) throws ServiceException {
        if (!validator.validateLoginAndPassword(login, password)) {
            return false;
        }
        String passwordFromDB;
        try {
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            passwordFromDB = userDAO.findPasswordByLogin(login);
        } catch (DAOException e) {
            // TODO: 13.12.2021 logging
            throw new ServiceException(e);
        }
        return password.equals(passwordFromDB);
    }

    @Override
    public boolean validatePasswordRepeat(String password, String passwordRepeat) {
        return password != null && !password.isEmpty() &&
        validatePasswordRestrictions(password) && password.equals(passwordRepeat);
    }

    private boolean validatePasswordRestrictions(String password){
        return password.length() >= 5 && hasLetters(password.toCharArray())
                && hasDigits(password.toCharArray());
    }

    private boolean hasLetters(char[] password){
        for (char c : password){
            if (Character.isLetter(c)){
                return true;
            }
        }
        return false;
    }

    private boolean hasDigits(char[] password) {
        for (char c : password) {
            if (Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }
}
