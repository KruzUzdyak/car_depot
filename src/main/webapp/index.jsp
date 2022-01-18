<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.volodko.controller.constant.CommandName" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<c:redirect url="Controller?${CommandName.COMMAND}=${CommandName.GO_TO_INITIAL_PAGE}"/>