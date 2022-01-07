package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.Message;
import com.epam.volodko.controller.constant.PagePath;
import com.epam.volodko.controller.constant.ParameterName;
import com.epam.volodko.service.OrderService;
import com.epam.volodko.service.ServiceFactory;
import com.epam.volodko.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToAllOrdersPageCommand implements Command {

    private final Logger log = LogManager.getLogger(GoToAllOrdersPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        checkUserLoggedIn(request, response);
        saveRequest(request);

        try {
            setOrderList(request);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
            request.setAttribute(ParameterName.ERROR_MESSAGE, Message.ORDER_LOAD_FAILED);
        }

        saveLastOrderListType(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ALL_ORDERS_PAGE);
        dispatcher.forward(request, response);
    }

    private void setOrderList(HttpServletRequest request) throws ServiceException {
        String orderListType = request.getParameter(ParameterName.ORDER_LIST_TYPE);
        String idForOrdersList = request.getParameter(ParameterName.ORDER_LIST_ENTITY_ID);

        int id = 0;
        if (idForOrdersList != null){
            id = Integer.parseInt(idForOrdersList);
        }

        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        request.setAttribute(ParameterName.ORDER_LIST, orderService.getOrderList(orderListType, id));
    }

    private void saveLastOrderListType(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(ParameterName.ORDER_LIST_TYPE, request.getParameter(ParameterName.ORDER_LIST_TYPE));
    }

}
