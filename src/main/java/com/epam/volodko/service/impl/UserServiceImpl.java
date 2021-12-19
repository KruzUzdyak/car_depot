package com.epam.volodko.service.impl;

import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.User;
import com.epam.volodko.service.UserService;
import com.epam.volodko.service.exception.ServiceException;
import com.epam.volodko.service.validator.UserValidator;

public class UserServiceImpl implements UserService {

    private final UserValidator validator = new UserValidator();
    private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    @Override
    public boolean processRegistration(User user) throws ServiceException {
        if (!validator.validateRegistration(user)){
            return false;
        }
        return saveUserInDB(user);
    }

    @Override
    public boolean processPasswordValidation(String password, String passwordRepeat){
        return validator.validatePasswordRepeat(password, passwordRepeat);
    }

    private boolean saveUserInDB(User user) throws ServiceException {
        boolean isSaved;
        try {
            isSaved = userDAO.saveNew(user) == 1;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isSaved;
    }

    @Override
    public User processLogination(String login, String password) throws ServiceException {
        if (!validator.validateLoginAndPassword(login, password)) {
            return null;
        }
        String passwordFromDB = getPasswordFromDB(login);
        if (password.equals(passwordFromDB)){
            return getUserFromDB(login);
        }
        return null;
    }

    private User getUserFromDB(String login) throws ServiceException {
        User user;
        try{
            user = userDAO.findByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    private String getPasswordFromDB(String login) throws ServiceException {
        String passwordFromDB;
        try {
            passwordFromDB = userDAO.findPasswordByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return passwordFromDB;
    }

}
