package com.epam.volodko.service.impl;

import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.DriverLicenseDAO;
import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.*;
import com.epam.volodko.service.UserService;
import com.epam.volodko.service.exception.ServiceException;
import com.epam.volodko.service.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImpl implements UserService {

    private final Logger log = LogManager.getLogger(UserServiceImpl.class);

    private final UserValidator validator = new UserValidator();
    private final UserDAO<User> userDAO = DAOFactory.getInstance().getUserDAO();
    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final DriverLicenseDAO licenseDAO = DAOFactory.getInstance().getLicenseDAO();

    // TODO: 07.01.2022 add more validation

    @Override
    public boolean processRegistration(User user) throws ServiceException {
        if (!validator.validateRegistration(user)){
            return false;
        }
        return saveNewUser(user);
    }

    @Override
    public boolean processPasswordValidation(String password, String passwordRepeat){
        return validator.validatePasswordRepeat(password, passwordRepeat);
    }

    private boolean saveNewUser(User user) throws ServiceException {
        int saveResponse;
        try {
            saveResponse = userDAO.saveNew(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return saveResponse == 1;
    }

    @Override
    public User processLogination(String login, String password) throws ServiceException {
        if (!validator.validateLoginAndPassword(login, password)) {
            return null;
        }
        String storedPasswordHash = getStoredPasswordHash(login);
        if (verifyPassword(password, storedPasswordHash)){
            return getUserFromDB(login);
        }
        return null;
    }

    @Override
    public String encodePassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public User getUser(int id, Role role) throws ServiceException {
        User user;
        try {
            user = daoFactory.getUserDAO(role).findById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        int rowsAffectedUser;
        int rowsAffectedInfo;
        try{
            rowsAffectedUser = userDAO.update(user);
            rowsAffectedInfo = daoFactory.getUserDAO(user.getRole()).updateInfo(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return rowsAffectedUser > 0 || rowsAffectedInfo > 0;
    }

    @Override
    public boolean updatePassword(int userId, String newPassword, String newPasswordRepeat) throws ServiceException {
        if (!validator.validatePasswordRepeat(newPassword, newPasswordRepeat)){
            return false;
        }
        String passwordHash = encodePassword(newPassword);
        int rowsAffected;
        try {
            rowsAffected = userDAO.updatePassword(userId, passwordHash);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean updateLogin(User user) throws ServiceException {
        if (!validator.notEmptyString(user.getLogin())){
            return false;
        }
        int rowsAffected;
        try {
            rowsAffected = userDAO.updateLogin(user.getId(), user.getLogin());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return rowsAffected > 0;
    }

    @Override
    public boolean saveNewDriverLicense(int driverId, DriverLicense license) throws ServiceException {
        int rowsAffected;
        try{
            rowsAffected = licenseDAO.saveNew(driverId, license);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteDriverLicense(int driverId, int licenseTypeId) throws ServiceException {
        int rowsAffected;
        try{
            rowsAffected = licenseDAO.deleteById(driverId, licenseTypeId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean saveNewUserInfo(User user) throws ServiceException {
        int rowsAffected;
        try{
            rowsAffected = daoFactory.getUserDAO(user.getRole()).saveInfo(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return rowsAffected > 0;
    }

    private boolean verifyPassword(String password, String storedPasswordHash){
        return storedPasswordHash != null && BCrypt.checkpw(password, storedPasswordHash);
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

    private String getStoredPasswordHash(String login) throws ServiceException {
        String storedPasswordHash;
        try {
            storedPasswordHash = userDAO.findPasswordHashByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return storedPasswordHash;
    }

}
