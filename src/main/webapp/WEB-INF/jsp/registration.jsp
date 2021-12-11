<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.12.2021
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car Depot - Registration</title>
</head>
<body>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="registration">
    Login
    <input type="text" name="login" value="">
    <br/><br/>
    Password
    <input type="password" name="password" value="">
    <br/><br/>
    Repeat password
    <input type="password" name="repeat_password" value="">
    <br/><br/>
    Name
    <input type="text" name="user_name" value="">
    <br/><br/>
    Phone
    <input type="tel" name="phone" value="">
    <br/><br/>
    <select name="role" size="1" >
        <option disabled>Choose your role</option>
        <option value="admin">admin</option>
        <option value="client">client</option>
        <option value="driver">driver</option>
    </select>
    <br/><br/>
    <input type="submit" value="Register">
</form>
<br/>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="go_to_main">
    <input type="submit" value="Go to main page">
</form>
</body>
</html>
