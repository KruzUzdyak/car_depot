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
    <fmt:message bundle="${loc}"  key="default.title" var="title"/>
    <fmt:message bundle="${loc}" key="locale.button.ru" var="button_locale_ru"/>
    <fmt:message bundle="${loc}" key="locale.button.en" var="button_locale_en"/>
    <fmt:message bundle="${loc}" key="locale.note" var="set_language_note"/>
    <fmt:message bundle="${loc}" key="main.registration_successful.message" var="registration_successful_mess"/>
    <fmt:message bundle="${loc}" key="main.logination_successful.message" var="logination_successful_mess"/>
    <fmt:message bundle="${loc}" key="default.sign_out_button" var="sign_out_button"/>

    <title>${title}</title>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid bg-light">
        <div class="navbar-header row">
            <div class="col-4">
                <c:out value="${set_language_note}"/>
            </div>
            <div class="col-2">
                <form action="Controller" method="post">
                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.LOCALIZATION}">
                    <input type="hidden" name="${ParameterName.LOCALE}" value="ru">
                    <input type="submit" value="${button_locale_ru}">
                </form>
            </div>
            <div class="col-2">
                <form action="Controller" method="post">
                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.LOCALIZATION}">
                    <input type="hidden" name="${ParameterName.LOCALE}" value="en">
                    <input type="submit" value="${button_locale_en}">
                </form>
            </div>
        </div>
        <div class="text-end">
            <form action="Controller" method="get">
                <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_INITIAL_PAGE}">
                <input type="submit" class="btn btn-outline-danger" value="${sign_out_button}">
            </form>
        </div>
    </div>
</nav>

<c:set var="greeting_message" value="${param.get(ParameterName.GREETING_MESSAGE)}"/>
<c:if test="${not empty greeting_message}">
    <h3 style="color: limegreen">
        <c:if test="${greeting_message eq Message.REGISTRATION_SUCCESSFUL}">
            <c:out value="${registration_successful_mess}"/>
        </c:if>
        <c:if test="${greeting_message eq Message.LOGINATION_SUCCESSFUL}">
            <c:out value="${logination_successful_mess}"/>
        </c:if>
    </h3>
</c:if>



<br/>


</body>
</html>
