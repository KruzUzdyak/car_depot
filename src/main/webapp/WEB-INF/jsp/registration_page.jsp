<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.volodko.controller.constant.CommandName" %>
<%@ page import="com.epam.volodko.controller.constant.ParameterName" %>
<%@ page import="com.epam.volodko.controller.constant.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.locale" var="loc"/>
    <fmt:message bundle="${loc}"  key="default.title" var="title"/>

    <meta charset="ISO-8859-1">
    <title>${title}</title>
</head>
<body>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="registration">
    Login<br/>
    <input type="text" name="user_login" value="">
    <br/><br/>
    Password<br/>
    <input type="password" name="user_password" value="">
    <c:out value="${Message.PASS_RESTRICT_MESSAGE_TEXT}"/>
    <br/><br/>
    Repeat password<br/>
    <input type="password" name="user_repeat_password" value="">
    <br/><br/>
    Name<br/>
    <input type="text" name="user_name" value="">
    <br/><br/>
    Phone (pattern +XXX XX XXXXXXX)<br/>
    <input type="tel" name="user_phone" value="">
    <br/><br/>
    Role<br/>
    <select name="user_role" size="1" >
        <option disabled>Choose your role</option>
        <option value="admin">admin</option>
        <option value="client">client</option>
        <option value="driver">driver</option>
    </select>
    <br/><br/>
    <input type="submit" value="Register">
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
    <input type="submit" value="Go to initial page">
</form>

</body>
</html>
