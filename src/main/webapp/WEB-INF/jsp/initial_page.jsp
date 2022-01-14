<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.volodko.controller.constant.CommandName" %>
<%@ page import="com.epam.volodko.controller.constant.ParameterName" %>
<%@ page import="com.epam.volodko.controller.constant.Message" %>
<%@ page import="com.epam.volodko.entity.user.Role" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
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
    <fmt:message bundle="${loc}" key="default.title" var="default_title"/>
    <fmt:message bundle="${loc}" key="locale.button.ru" var="locale_button_ru"/>
    <fmt:message bundle="${loc}" key="locale.button.en" var="locale_button_en"/>
    <fmt:message bundle="${loc}" key="locale.note" var="locale_note"/>
    <fmt:message bundle="${loc}" key="initial.button.login" var="button_login"/>
    <fmt:message bundle="${loc}" key="initial.button.Registration" var="button_registration"/>

    <title>${default_title}</title>
</head>

<body>
<c:set var="errorMessage" value="${requestScope.get(ParameterName.ERROR_MESSAGE)}"/>

<nav class="navbar navbar-default">
    <div class="container-fluid bg-light">
        <div class="navbar-header row">
            <div class="col-4">
                <p>${locale_note}</p>
            </div>
            <div class="col-2">
                <form action="Controller" method="post">
                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.LOCALIZATION}">
                    <input type="hidden" name="${ParameterName.LOCALE}" value="ru">
                    <input type="submit" class="btn btn-outline-info" value="${locale_button_ru}">
                </form>
            </div>
            <div class="col-2">
                <form action="Controller" method="post">
                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.LOCALIZATION}">
                    <input type="hidden" name="${ParameterName.LOCALE}" value="en">
                    <input type="submit" class="btn btn-outline-info" value="${locale_button_en}">
                </form>
            </div>
        </div>
    </div>
</nav>

<br/>
<h3 class="text-warning text-center">
    <c:if test="${errorMessage eq Message.NOT_LOGGED_ID}">
        <fmt:message bundle="${loc}" key="initial.error_message.not_logged_in"/>
    </c:if>
</h3>
<br/>

<div class="container">
    <div class="text-center">
        <form action="Controller" method="get">
            <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_LOGINATION}">
            <input type="submit" class="btn btn-success" value="${button_login}">
        </form><br/>
        <form action="Controller" method="get">
            <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_REGISTRATION}">
            <input type="hidden" name="${ParameterName.REGISTER_ROLE}" value="${Role.CLIENT}"/>
            <input type="submit" class="btn btn-info" value="${button_registration}">
        </form>
    </div>
</div>

</body>
</html>