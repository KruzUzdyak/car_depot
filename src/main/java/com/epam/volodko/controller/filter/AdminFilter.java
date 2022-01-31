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

public class AdminFilter implements Filter {

    private final List<String> adminCommands = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        adminCommands.add(CommandName.ADD_NEW_CAR);
        adminCommands.add(CommandName.GO_TO_ADD_NEW_CAR);
        adminCommands.add(CommandName.ORDER_SET_ADMIN);
        adminCommands.add(CommandName.ORDER_SET_CAR);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String command = request.getParameter(CommandName.COMMAND);
        if (adminCommands.contains(command)){
            checkIsAdmin((HttpServletRequest) request, (HttpServletResponse) response, chain);
        } else{
            chain.doFilter(request, response);
        }
    }

    private void checkIsAdmin(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        Role role = (Role) request.getSession().getAttribute(ParameterName.USER_ROLE);
        if (role == Role.ADMIN){
            chain.doFilter(request, response);
        } else {
            request.setAttribute(ParameterName.ERROR_MESSAGE, Message.NOT_ENOUGH_RIGHTS);
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ERROR_PAGE);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
