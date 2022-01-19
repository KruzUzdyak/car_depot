package com.epam.volodko.service;

import com.epam.volodko.entity.user.Driver;
import com.epam.volodko.entity.user.DriverLicense;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;
import com.epam.volodko.service.exception.ServiceException;

import java.util.List;

public interface UserService {

    boolean processRegistration(User user) throws ServiceException;

    boolean processPasswordValidation(String password, String passwordRepeat) throws ServiceException;

    User processLogination(String login, String password) throws ServiceException;

    String encodePassword(String password);

    User getUser(int id, Role role) throws ServiceException;

    boolean updateUser(User user) throws ServiceException;

    boolean updateLogin(int id, String newLogin) throws ServiceException;

    boolean updatePassword(int userId, String newPassword, String newPasswordRepeat) throws ServiceException;

    boolean saveNewDriverLicense(int driverId, DriverLicense license) throws ServiceException;

    boolean deleteDriverLicense(int driverId, int licenseTypeId) throws ServiceException;

    List<User> getUserList(Role role) throws ServiceException;
}
