package com.epam.volodko.controller.impl.cars_page;

import com.epam.volodko.controller.Command;
import com.epam.volodko.controller.constant.PagePath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToAddNewCarCommand implements Command {

    private final Logger log = LogManager.getLogger(GoToAddNewCarCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forward(request, response, PagePath.ADD_NEW_CAR_PAGE);
    }
}
