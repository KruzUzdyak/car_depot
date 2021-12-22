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

public class LocalizationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession(true)
                .setAttribute(ParameterName.LOCALE, request.getParameter(ParameterName.LOCALE));
        response.sendRedirect(restoreLastRequest(request));
    }

    private String restoreLastRequest(HttpServletRequest request){
        HttpSession session = request.getSession();
        Map<String, String[]> lastRequestParameters =
                (Map<String, String[]>) session.getAttribute(ParameterName.LAST_REQUEST);
        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append(CommandName.CONTROLLER).append('?');
        for (Map.Entry<String, String[]> parameter : lastRequestParameters.entrySet()){
            requestBuilder.append(parameter.getKey()).append('=');
            for (String parameterValue : parameter.getValue()){
                requestBuilder.append(parameterValue).append(',');
            }
            requestBuilder.deleteCharAt(requestBuilder.lastIndexOf(","));
            requestBuilder.append('&');
        }
        requestBuilder.deleteCharAt(requestBuilder.lastIndexOf("&"));
        return requestBuilder.toString();
    }
}
