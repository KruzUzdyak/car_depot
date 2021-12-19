<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.epam.volodko.controller.constant.CommandName" %>
<%@ page import="com.epam.volodko.controller.constant.ParameterName" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.locale" var="loc"/>
    <fmt:message bundle="${loc}"  key="default.title" var="title"/>
    <fmt:message bundle="${loc}" key="index.button.locale.ru" var="button_locale_ru"/>
    <fmt:message bundle="${loc}" key="index.button.locale.en" var="button_locale_en"/>
    <fmt:message bundle="${loc}" key="index.message.locale" var="set_languate_message"/>
    <fmt:message bundle="${loc}" key="index.button.login" var="button_login"/>
    <fmt:message bundle="${loc}" key="index.button.Registration" var="button_registration"/>

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

<form action="Controller" method="get">
    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_LOGINATION}">
    <input type="submit" value="${button_login}">
</form>

<br/>
<form action="Controller" method="get">
    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_REGISTRATION}">
    <input type="submit" value="${button_registration}">
</form>

</body>
</html>