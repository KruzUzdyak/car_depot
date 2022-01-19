package com.epam.volodko.controller;

import com.epam.volodko.controller.constant.CommandName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final Logger log = LogManager.getLogger(Controller.class);

    private final CommandProvider provider = new CommandProvider();

    @Override
    public void init(){
        log.info("Receive request from user. Starting new Thread.");
    }

    @Override
    public void destroy() {
        log.info(String.format("Request from user completed. Thread destroy process started - %s.", Thread.currentThread()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter(CommandName.COMMAND);
        Command command = provider.getCommand(commandName);
        command.execute(request, response);
    }
}
