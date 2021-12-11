<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11.12.2021
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car Depot</title>
</head>
<body>
<h2>Registration failed. You input has invalid data. Try again.</h2>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="go_to_registration">
    <input type="submit" value="Go to registration">
</form>
</body>
</html>
