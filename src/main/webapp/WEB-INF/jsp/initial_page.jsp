<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.epam.volodko.controller.constant.CommandName" %>
<%@ page import="com.epam.volodko.controller.constant.ParameterName" %>
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
    <fmt:message bundle="${loc}"  key="default.title" var="title"/>
    <fmt:message bundle="${loc}" key="locale.button.ru" var="button_locale_ru"/>
    <fmt:message bundle="${loc}" key="locale.button.en" var="button_locale_en"/>
    <fmt:message bundle="${loc}" key="locale.note" var="set_language_note"/>
    <fmt:message bundle="${loc}" key="initial.button.login" var="button_login"/>
    <fmt:message bundle="${loc}" key="initial.button.Registration" var="button_registration"/>

    <title>${title}</title>
</head>

<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="#" class="navbar-brand">
                <c:out value="${title}"/>
            </a>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
         <div class="col-3">
             <div class="row">
                <div class="col-4">
                    <c:out value="${set_language_note}"/>
                </div>
                <div class="col-1">
                    <form action="Controller" method="post">
                        <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.LOCALIZATION}">
                        <input type="hidden" name="${ParameterName.LOCALE}" value="ru">
                        <input type="submit" value="${button_locale_ru}">
                    </form>
                </div>
                <div class="col-1">
                    <form action="Controller" method="post">
                        <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.LOCALIZATION}">
                        <input type="hidden" name="${ParameterName.LOCALE}" value="en">
                        <input type="submit" value="${button_locale_en}">
                    </form>
                </div>
            </div>
        </div>

        <div class="col-3">
            <div class="align-content-md-center">
                <form action="Controller" method="get">
                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_LOGINATION}">
                    <input type="submit" value="${button_login}">
                </form><br/>
                <form action="Controller" method="get">
                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_REGISTRATION}">
                    <input type="submit" value="${button_registration}">
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>