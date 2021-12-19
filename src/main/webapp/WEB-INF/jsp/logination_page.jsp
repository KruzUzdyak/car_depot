<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.volodko.controller.constant.ParameterName" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car Depot</title>
</head>
<body>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="logination">
    Login
    <input type="text" name="user_login" value="">
    <br/><br/>
    Password
    <input type="password" name="user_password" value="">
    <br/><br/>
    <input type="submit" value="Login">
</form>

<c:set var="errorMessage" scope="page" value="${requestScope.get(ParameterName.ERROR_MESSAGE)}"/>
<c:if test="${not empty errorMessage}">
    <h3 style="color:crimson">
        <c:out value="${errorMessage}"/>
    </h3>
</c:if>

<br/>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="go_to_initial_page">
    <input type="submit" value="Go to main page">
</form>
</body>
</html>
