package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.PagePath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToInitialPageCommand extends RequestSaver implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        saveRequest(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.INITIAL_PAGE);
        dispatcher.forward(request, response);
    }
}
