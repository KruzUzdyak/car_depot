package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToInitialPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        saveRequest(request);
        HttpSession session = request.getSession();
        session.setAttribute(ParameterName.USER_LOGIN, null);
        session.setAttribute(ParameterName.USER_ROLE, null);
        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.INITIAL_PAGE);
        dispatcher.forward(request, response);
    }
}
