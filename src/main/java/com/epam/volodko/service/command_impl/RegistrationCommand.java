package com.epam.volodko.service.command_impl;

import com.epam.volodko.dao.DAOFactory;
import com.epam.volodko.dao.UserDAO;
import com.epam.volodko.dao.exception.DAOException;
import com.epam.volodko.entity.user.*;
import com.epam.volodko.service.Command;
import com.epam.volodko.service.ParameterName;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = createUserForRegistration(request);
        if (checkValidUser(user)){
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            try {
                userDAO.saveNewUser(user);
            } catch (DAOException e) {
                e.printStackTrace();
                //todo info for user about failing registration
                //todo logger.log
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/afterRegistration.jsp");
            dispatcher.forward(request, response);
        }
    }

    private User createUserForRegistration(HttpServletRequest request) {
        User user = null;
        Role role = Role.valueOf(request.getParameter(ParameterName.ROLE).toUpperCase());
        switch (role) {
            case ADMIN -> {
                user = new Admin();
                user.setRole(Role.ADMIN);
            }
            case CLIENT -> {
                user = new Client();
                user.setRole(Role.CLIENT);
            }
            case DRIVER -> {
                user = new Driver();
                user.setRole(Role.DRIVER);
            }
        }
        user.setLogin(request.getParameter(ParameterName.LOGIN));
        //todo add password encrypting
        String passwordHash = request.getParameter(ParameterName.PASSWORD);
        user.setPassword(passwordHash);
        user.setName(request.getParameter(ParameterName.USER_NAME));
        user.setPhone(request.getParameter(ParameterName.PHONE));
        return user;
    }

    private boolean checkValidUser(User user){
        return user != null && user.getLogin() != null & user.getPassword() != null &&
                user.getName() != null && user.getRole() != null;
    }


}
