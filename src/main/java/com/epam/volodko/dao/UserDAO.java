package com.epam.volodko.dao;

import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;

import java.util.List;

public interface UserDAO extends AbstractDAO<User>{

    User retrieveUserById(int userId) throws DAOException;
    User retrieveUserByLogin(String userLogin);
    List<? extends User> retrieveUsersByRole(Role role);
    //todo add retrieving by more parameters

    void updateUser(User user) throws DAOException;
    void saveNewUser(User user) throws DAOException;

    void deleteUserByLogin(String userLogin)throws DAOException;

}
