<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.volodko.controller.constant.CommandName" %>
<%@ page import="com.epam.volodko.controller.constant.ParameterName" %>
<%@ page import="com.epam.volodko.controller.constant.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="default.title" var="title"/>
    <fmt:message bundle="${loc}" key="loginanion.login.note" var="login_note"/>
    <fmt:message bundle="${loc}" key="logination.password.note" var="password_note"/>
    <fmt:message bundle="${loc}" key="logination.sign_in.button" var="sign_in_button"/>
    <fmt:message bundle="${loc}" key="logination.to_initial_page.button" var="to_initial_page_button"/>
    <fmt:message bundle="${loc}" key="logination.failed.message" var="logination_failed_mess"/>
    <fmt:message bundle="${loc}" key="logination.exception.message" var="logination_exception_mess"/>

    <title>${title}</title>
</head>
<body>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="logination">
    ${login_note}<br/>
    <input type="text" name="user_login" placeholder="${login_note}">
    <br/><br/>
    ${password_note}<br/>
    <input type="password" name="user_password" placeholder="${password_note}">
    <br/><br/>
    <input type="submit" value="${sign_in_button}">
</form>


<c:set var="errorMessage" scope="page" value="${requestScope.get(ParameterName.ERROR_MESSAGE)}"/>
<c:if test="${not empty errorMessage}">
    <h3 style="color:crimson">
        <c:if test="${errorMessage eq Message.LOGINATION_FAILED}">
            <c:out value="${logination_failed_mess}"/>
        </c:if>
        <c:if test="${errorMessage eq Message.LOGINATION_EXCEPTION}">
            <c:out value="${logination_failed_mess}"/>
        </c:if>
    </h3>
</c:if>

<br/>
<form action="Controller" method="get">
    <input type="hidden" name="command" value="go_to_initial_page">
    <input type="submit" value="${to_initial_page_button}">
</form>
</body>
</html>
