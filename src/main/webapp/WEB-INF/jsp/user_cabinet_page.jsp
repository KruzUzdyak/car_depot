<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.volodko.controller.constant.CommandName" %>
<%@ page import="com.epam.volodko.controller.constant.ParameterName" %>
<%@ page import="com.epam.volodko.controller.constant.Message" %>
<%@ page import="com.epam.volodko.entity.user.Role" %>
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
    <fmt:message bundle="${loc}" key="default.title" var="default_title"/>
    <fmt:message bundle="${loc}" key="locale.button.ru" var="locale_button_ru"/>
    <fmt:message bundle="${loc}" key="locale.button.en" var="locale_button_en"/>
    <fmt:message bundle="${loc}" key="locale.note" var="locale_note"/>
    <fmt:message bundle="${loc}" key="default.sign_out_button" var="sign_out_button"/>

    <title>${default_title}</title>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid bg-light">
        <div class="navbar-header row">
            <div class="col-4">
                <c:out value="${locale_note}"/>
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
        <div class="col-10">
            <div class="row">
                <div class="col-8">

                </div>
                <div class="col-2">

                </div>
                <div class="col-2 text-center">
                    <form action="Controller" method="get">
                        <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_INITIAL_PAGE}">
                        <input type="submit" class="btn btn-outline-danger" value="${sign_out_button}">
                    </form>
                </div>
            </div>
        </div>
    </div>
</nav>

<c:set var="user" value="${requestScope.get(ParameterName.USER)}"/>
<c:set var="updateInfo" value="${requestScope.get(ParameterName.USER_UPDATE_INFO)}"/>
<c:set var="updateLogin" value="${requestScope.get(ParameterName.USER_UPDATE_LOGIN)}"/>
<c:set var="updatePass" value="${requestScope.get(ParameterName.USER_UPDATE_PASS)}"/>
<c:set var="userRole" value="${requestScope.get(ParameterName.USER_ROLE)}"/>

<br/><br/><br/>

<div class="container-fluid row">
    <div class="col-4">

    </div>
    <div class="col-4">
        <form action="Controller" method="post">
            <table class="table table-borderless table-striped">
                <tr>
                    <th>Login: </th>
                    <td colspan="3">${user.login}</td>
                </tr>
                <tr>
                    <th>Name: </th>
                    <td colspan="3">${user.name}</td>
                </tr>
                <tr>
                    <th>Phone: </th>
                    <td colspan="3">${user.phone}</td>
                </tr>
                <tr>
                    <th>You are: </th>
                    <td colspan="3">${user.role}</td>
                </tr>
                <c:if test="${user.role eq Role.ADMIN}">
                    <tr>
                        <th>Work since</th>
                        <td colspan="3">${user.worksSince}</td>
                    </tr>
                    <tr>
                        <th>Note</th>
                        <td colspan="3">${user.note}</td>
                    </tr>
                </c:if>
                <c:if test="${user.role eq Role.CLIENT}">
                    <tr>
                        <th>Company</th>
                        <td colspan="3">${user.company}</td>
                    </tr>
                    <tr>
                        <th>Note</th>
                        <td colspan="3">${user.note}</td>
                    </tr>
                </c:if>

            </table>
        </form>
    </div>
</div>

</body>
</html>
