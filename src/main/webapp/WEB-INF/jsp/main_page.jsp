<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.volodko.controller.constant.CommandName" %>
<%@ page import="com.epam.volodko.controller.constant.ParameterName" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.locale" var="loc"/>
    <fmt:message bundle="${loc}"  key="default.title" var="title"/>
    <fmt:message bundle="${loc}" key="index.button.locale.ru" var="button_locale_ru"/>
    <fmt:message bundle="${loc}" key="index.button.locale.en" var="button_locale_en"/>
    <fmt:message bundle="${loc}" key="index.message.locale" var="set_languate_message"/>

    <meta charset="ISO-8859-1">
    <title>${title}</title>
</head>
<body>

<c:out value="${set_languate_message}"/>
<form action="Controller" method="post">
    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.LOCALIZATION}">
    <input type="hidden" name="${ParameterName.LOCALE}" value="ru">
    <input type="submit" value="${button_locale_ru}">
</form>
<form action="Controller" method="post">
    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.LOCALIZATION}">
    <input type="hidden" name="${ParameterName.LOCALE}" value="en">
    <input type="submit" value="${button_locale_en}">
</form>

<br/>

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
