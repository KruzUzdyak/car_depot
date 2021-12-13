package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToRegistrationCommand implements Command {

    private static final String PASS_RESTRICT_MESSAGE_TEXT =
            "*Password length should be 5 symbols min and include characters and numbers.";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(ParameterName.PASS_RESTRICT_MESSAGE, PASS_RESTRICT_MESSAGE_TEXT);
        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.REGISTRATION_PAGE);
        dispatcher.forward(request, response);
    }
}
