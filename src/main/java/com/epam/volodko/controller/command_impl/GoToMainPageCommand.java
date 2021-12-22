package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GoToMainPageCommand extends RequestSaver implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        saveRequest(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.MAIN_PAGE);
        dispatcher.forward(request, response);
    }
}
