package com.epam.volodko.service;

import com.epam.volodko.entity.user.User;

public interface UserService {

    boolean validateUser(User user);

    boolean validateUserPassword(String login, String password);

    boolean validatePasswordRepeat(String password, String passwordRepeat);

}
