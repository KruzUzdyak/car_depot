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
    <fmt:message bundle="${loc}" key="logination.login.placeholder" var="login_placeholder"/>
    <fmt:message bundle="${loc}" key="logination.password.note" var="password_note"/>
    <fmt:message bundle="${loc}" key="logination.password.placeholder" var="password_placeholder"/>
    <fmt:message bundle="${loc}" key="logination.sign_in.button" var="sign_in_button"/>
    <fmt:message bundle="${loc}" key="logination.to_initial_page.button" var="to_initial_page_button"/>
    <fmt:message bundle="${loc}" key="logination.failed.message" var="logination_failed_mess"/>
    <fmt:message bundle="${loc}" key="logination.exception.message" var="logination_exception_mess"/>

    <title>${title}</title>
</head>

<body>
<br/><br/><br/><br/>
<div class="container-fluid row">
    <div class="col-3">

    </div>
    <div class="col-6">
        <form action="Controller" method="post">
            <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.LOGINATION}">
            <div class="row">
                <div class="col-5 text-end">
                    <p>${login_note}</p>
                </div>
                <div class="col-7">
                    <input type="text" name="${ParameterName.USER_LOGIN}" placeholder="${login_placeholder}">
                </div>
            </div>
            <div class="row">
                <div class="col-5 text-end">
                    <p>${password_note}</p>
                </div>
                <div class="col-7">
                    <input type="password" name="${ParameterName.USER_PASSWORD}" placeholder="${password_placeholder}">
                </div>
            </div>
            <div class="text-center">
                <br/>
                <input type="submit" class="btn btn-success" value="${sign_in_button}">
            </div>
        </form>

        <div class="text-center">
            <c:set var="errorMessage" scope="page" value="${requestScope.get(ParameterName.ERROR_MESSAGE)}"/>
            <c:if test="${not empty errorMessage}">
                <p class="text-danger">
                    <c:if test="${errorMessage eq Message.LOGINATION_FAILED}">
                        <c:out value="${logination_failed_mess}"/>
                    </c:if>
                    <c:if test="${errorMessage eq Message.LOGINATION_EXCEPTION}">
                        <c:out value="${logination_failed_mess}"/>
                    </c:if>
                </p>
            </c:if>
            <c:if test="${empty errorMessage}">
                <br/>
            </c:if>
        </div>

        <div class="text-center">
            <br/><br/><br/>
            <form action="Controller" method="get">
                <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_INITIAL_PAGE}">
                <input type="submit" class="btn btn-info" value="${to_initial_page_button}">
            </form>
        </div>
    </div>
</div>

</body>
</html>
