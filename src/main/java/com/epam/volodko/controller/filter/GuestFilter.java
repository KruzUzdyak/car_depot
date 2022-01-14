package com.epam.volodko.controller.filter;

import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.constant.Message;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.entity.user.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuestFilter implements Filter {

    private final List<String> guestCommands = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        guestCommands.add(CommandName.GO_TO_LOGINATION);
        guestCommands.add(CommandName.GO_TO_REGISTRATION);
        guestCommands.add(CommandName.GO_TO_INITIAL_PAGE);
        guestCommands.add(CommandName.LOGINATION);
        guestCommands.add(CommandName.REGISTRATION);
        guestCommands.add(CommandName.LOCALIZATION);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String command = request.getParameter(CommandName.COMMAND);
        if (guestCommands.contains(command)){
            chain.doFilter(request, response);
        } else{
            checkUserLoggedIn((HttpServletRequest) request, (HttpServletResponse) response, chain);
        }
    }

    private void checkUserLoggedIn(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        Role role = (Role) request.getSession().getAttribute(ParameterName.USER_ROLE);
        if (role == null){
            request.setAttribute(ParameterName.ERROR_MESSAGE, Message.NOT_LOGGED_ID);
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.INITIAL_PAGE);
            dispatcher.forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
