package com.epam.volodko.service;

import com.epam.volodko.entity.user.User;
import com.epam.volodko.service.exception.ServiceException;

public interface UserService {

    boolean processRegistration(User user) throws ServiceException;

    boolean processPasswordValidation(String password, String passwordRepeat) throws ServiceException;

    User processLogination(String login, String password) throws ServiceException;

    String encodePassword(String password);

    User getUser(int id) throws ServiceException;
}
