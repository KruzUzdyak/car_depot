<%@ page import="com.epam.volodko.controller.constant.ParameterName" %>
<%@ page import="com.epam.volodko.controller.constant.Message" %><%--
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
    <input type="hidden" name="command" value="registration">
    Login
    <input type="text" name="user_login" value="">
    <br/><br/>
    Password
    <input type="password" name="user_password" value="">
    <%
        out.print(Message.PASS_RESTRICT_MESSAGE_TEXT);
    %>
    <br/><br/>
    Repeat password
    <input type="password" name="user_repeat_password" value="">
    <br/><br/>
    Name
    <input type="text" name="user_name" value="">
    <br/><br/>
    Phone
    <input type="tel" name="user_phone" value="">
    <br/><br/>
    Role
    <select name="user_role" size="1" >
        <option disabled>Choose your role</option>
        <option value="admin">admin</option>
        <option value="client">client</option>
        <option value="driver">driver</option>
    </select>
    <br/><br/>
    <input type="submit" value="Register">
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
