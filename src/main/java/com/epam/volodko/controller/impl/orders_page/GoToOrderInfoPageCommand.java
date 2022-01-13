package com.epam.volodko.controller.impl.orders_page;

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

public class GoToOrderInfoPageCommand implements Command {

    private final Logger log = LogManager.getLogger(GoToOrderInfoPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            setOrderInfo(request);
        } catch (ServiceException e) {
            log.error("Catching: ", e);
            request.setAttribute(ParameterName.ERROR_MESSAGE, Message.ORDER_LOAD_FAILED);
        }

        forward(request, response, PagePath.ORDER_INFO_PAGE);
    }

    private void setOrderInfo(HttpServletRequest request) throws ServiceException {
        String orderId = request.getParameter(ParameterName.ORDER_ID);
        int id = 0;
        if (orderId != null){
            id = Integer.parseInt(orderId);
        }

        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        request.setAttribute(ParameterName.ORDER, orderService.getOrderById(id));
    }
}
