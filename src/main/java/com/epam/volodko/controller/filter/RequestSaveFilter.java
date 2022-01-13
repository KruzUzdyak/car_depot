package com.epam.volodko.controller.filter;

import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.constant.ParameterName;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestSaveFilter implements Filter {

    private final List<String> notSavedCommands = new ArrayList<>();

    {
        notSavedCommands.add(CommandName.LOCALIZATION);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String command = request.getParameter(CommandName.COMMAND);
        if (!notSavedCommands.contains(command)){
            saveRequest((HttpServletRequest) request);
        }
        chain.doFilter(request, response);
    }

    private void saveRequest(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute(ParameterName.LAST_REQUEST, new HashMap<>(request.getParameterMap()));
    }


}
