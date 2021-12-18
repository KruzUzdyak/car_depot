package com.epam.volodko.service;

import com.epam.volodko.entity.user.User;
import com.epam.volodko.service.exception.ServiceException;

public interface UserService {

    boolean saveUser(User user);

    boolean validateLogin(String login);

    boolean validateLogination(String login, String password) throws ServiceException;

    boolean validatePasswordRepeat(String password, String passwordRepeat);


}
