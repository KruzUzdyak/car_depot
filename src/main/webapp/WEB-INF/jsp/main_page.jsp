<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.volodko.controller.constant.ParameterName" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car Depot</title>
</head>
<body>

<c:set var="message" value="${param.get(ParameterName.LOGINATION_MESSAGE)}"/>
<c:if test="${empty message}">
    <c:set var="message" value="${param.get(ParameterName.REGISTRATION_MESSAGE)}"/>
</c:if>

<h2 style="color: limegreen">
    <c:out value="${message}"/>
</h2>



<br/>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="go_to_initial_page">
    <input type="submit" value="Go to main page">
</form>

</body>
</html>
