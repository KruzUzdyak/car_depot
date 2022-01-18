package com.epam.volodko.controller.impl;

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
        HttpSession session = request.getSession(true);
        session.setAttribute(ParameterName.USER_LOGIN, null);
        session.setAttribute(ParameterName.USER_ROLE, null);

        setLocale(session);

        forward(request, response, PagePath.INITIAL_PAGE);
    }

    private void setLocale(HttpSession session) {
        String locale = (String) session.getAttribute(ParameterName.LOCALE);
        if (locale == null){
            session.setAttribute(ParameterName.LOCALE, ParameterName.LOCALE_EN);
        }
    }
}
