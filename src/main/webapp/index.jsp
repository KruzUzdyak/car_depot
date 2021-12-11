<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Car Depot</title>
</head>
<body>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="go_to_logination">
    <input type="submit" value="Login">
</form>
<br/>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="go_to_registration">
    <input type="submit" value="Register">
</form>
</body>
</html>