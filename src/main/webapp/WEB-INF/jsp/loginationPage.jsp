<%@ page import="com.epam.volodko.controller.constant.ParameterName" %><%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.12.2021
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
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

<h3 style="color:crimson">
    <%
        String errorMessage = (String) request.getAttribute(ParameterName.ERROR_MESSAGE);
        if (errorMessage != null){
            out.println(errorMessage);
        }
    %>
</h3>

<br/>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="go_to_initial_page">
    <input type="submit" value="Go to main page">
</form>
</body>
</html>
