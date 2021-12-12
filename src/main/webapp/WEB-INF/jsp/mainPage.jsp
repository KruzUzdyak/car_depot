<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 12.12.2021
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car Depot</title>
</head>
<body>

<h2 style="color: limegreen">
    <%
        String registerMessage = request.getParameter("registration_message");
        if (registerMessage != null && registerMessage.equals("done")){
            out.println("Registration done. Congrats!");
        }
    %>
</h2>

<h2 style="color: forestgreen">
    <%
        String loginMessage = request.getParameter("logination_message");
        if (loginMessage != null && loginMessage.equals("done")){
            out.println("Now you are logged in.");
        }
    %>
</h2>

<br/>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="go_to_initial_page">
    <input type="submit" value="Go to main page">
</form>

</body>
</html>
