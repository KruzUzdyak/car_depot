package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.entity.user.Role;
import com.epam.volodko.entity.user.User;
import com.epam.volodko.service.ServiceFactory;
import com.epam.volodko.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {

    private static final String REGISTRATION_MESSAGE_TEXT = "Now you are registered. Congrats!";
    private static final String REGISTRATION_ERROR_MESSAGE_TEXT = "Registration failed! Try again.";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        if (validate(request) && userService.saveUser(prepareUserForSave(request))){
            response.sendRedirect("Controller?command=" + CommandName.GO_TO_MAIN_PAGE
                        + "&" + ParameterName.REGISTRATION_MESSAGE + "=" + REGISTRATION_MESSAGE_TEXT);
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

    private boolean validate(HttpServletRequest request){
        String login = request.getParameter(ParameterName.USER_LOGIN);
        String password = request.getParameter(ParameterName.USER_PASSWORD);
        String passwordRepeat = request.getParameter(ParameterName.USER_REPEAT_PASSWORD);
        UserService userService = ServiceFactory.getInstance().getUserService();
        return userService.validatePasswordRepeat(password, passwordRepeat) &&
                userService.validateLogin(login);
    }

    private User prepareUserForSave(HttpServletRequest request){
        String login = request.getParameter(ParameterName.USER_LOGIN);
        String password = request.getParameter(ParameterName.USER_PASSWORD);
        String name = request.getParameter(ParameterName.USER_NAME);
        String phone = request.getParameter(ParameterName.USER_PHONE);
        Role role = Role.valueOf(request.getParameter(ParameterName.USER_ROLE).toUpperCase());
        return new User(0, login, password, name, phone, role);
    }
}
