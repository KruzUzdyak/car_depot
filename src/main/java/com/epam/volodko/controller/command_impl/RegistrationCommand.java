package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {

    private static final String REGISTRATION_ERROR_MESSAGE_TEXT = "Registration failed! Try again.";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = prepareUserForSaving(request);
        String repeatPassword = request.getParameter(ParameterName.USER_REPEAT_PASSWORD);
        // TODO: 12.12.2021 add full validation for user
        if (validateLoginAndPassword(user, repeatPassword)){
            try {
                // TODO: 12.12.2021 save user in DB
            } catch (Exception e) {
                //logger.log();
                forwardOnFailedRegistration(request, response);
            }
            response.sendRedirect("Controller?command=" + CommandName.GO_TO_MAIN_PAGE
                    + "&registration_message=" + "done");
        } else {
            forwardOnFailedRegistration(request, response);
        }
    }

    private void forwardOnFailedRegistration(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(ParameterName.ERROR_MESSAGE, REGISTRATION_ERROR_MESSAGE_TEXT);
        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.REGISTRATION_PAGE);
        dispatcher.forward(request, response);
    }

    private boolean validateLoginAndPassword(User user, String repeatPassword) {
        return user.getLogin() != null && !user.getLogin().isEmpty() &&
                !user.getPassword().equals("") && user.getPassword().equals(repeatPassword);
    }

    private User prepareUserForSaving(HttpServletRequest request){
        String login = request.getParameter(ParameterName.USER_LOGIN);
        // TODO: 12.12.2021 add password hashing
        String password = request.getParameter(ParameterName.USER_PASSWORD);
        String name = request.getParameter(ParameterName.USER_NAME);
        String phone = request.getParameter(ParameterName.USER_PHONE);
        Role role = Role.valueOf(request.getParameter(ParameterName.USER_ROLE).toUpperCase());
        return new User(0, login, password, name, phone, role);
    }


}
