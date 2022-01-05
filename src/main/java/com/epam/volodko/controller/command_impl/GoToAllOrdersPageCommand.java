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
import java.io.IOException;

public class GoToAllOrdersPageCommand extends AbstractCommand implements Command {

    private static final Logger log = LogManager.getLogger(GoToAllOrdersPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        validateRole(request, response);
        saveRequest(request);

        try {
            setOrderList(request);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
            request.setAttribute(ParameterName.ERROR_MESSAGE, Message.ORDER_LOADING_FAILED);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.ALL_ORDERS_PAGE);
        dispatcher.forward(request, response);
    }

    private void setOrderList(HttpServletRequest request) throws ServiceException {
        String orderListType = request.getParameter(ParameterName.ORDER_LIST_TYPE);
        String orderListId = request.getParameter(ParameterName.ORDER_LIST_ID);

        int id = 0;
        if (orderListId != null){
            id = Integer.parseInt(orderListId);
        }

        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        request.setAttribute(ParameterName.ORDERS_LIST, orderService.getOrderList(orderListType, id));
    }

}
