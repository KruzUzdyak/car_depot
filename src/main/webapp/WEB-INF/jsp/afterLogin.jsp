<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.12.2021
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car Depot - Login successful</title>
</head>
<body>
<h2>You logged in.</h2>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="go_to_main">
    <input type="submit" value="Go to main page">
</form>
</body>
</html>
