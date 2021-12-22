package com.epam.volodko.controller.command_impl;

import com.epam.volodko.controller.constant.ParameterName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class RequestSaver {

    void saveRequest(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute(ParameterName.LAST_REQUEST, new HashMap<>(request.getParameterMap()));
    }
}
