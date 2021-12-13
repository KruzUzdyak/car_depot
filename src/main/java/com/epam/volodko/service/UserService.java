package com.epam.volodko.service;

import com.epam.volodko.entity.user.User;

public interface UserService {

    boolean saveUser(User user);

    boolean validateLogin(String login);

    boolean validateUserPassword(String login, String password);

    boolean validatePasswordRepeat(String password, String passwordRepeat);


}
