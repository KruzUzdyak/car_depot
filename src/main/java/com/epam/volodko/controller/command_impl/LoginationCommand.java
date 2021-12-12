package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginationCommand implements Command {

    private static final String LOGINATION_ERROR_MESSAGE_TEXT = "Login failed. Try again.";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(ParameterName.USER_LOGIN);
        // TODO: 12.12.2021 password hashing
        String password = request.getParameter(ParameterName.USER_PASSWORD);

        // TODO: 12.12.2021 add validation for login process
        if (validateUserForLogin(login, password)){
            response.sendRedirect("Controller?command=" + CommandName.GO_TO_MAIN_PAGE
                    + "&logination_message=" + "done");
        } else {
            forwardOnFailedLogination(request, response);
        }
    }

    private void forwardOnFailedLogination(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(ParameterName.ERROR_MESSAGE, LOGINATION_ERROR_MESSAGE_TEXT);
        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.LOGINATION_PAGE);
        dispatcher.forward(request, response);
    }

    private boolean validateUserForLogin(String login, String password) {
        return login != null && !login.equals("") && password != null && !password.equals("");
    }


}
