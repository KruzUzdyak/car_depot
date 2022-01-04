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
    <fmt:message bundle="${loc}"  key="default.title" var="default_title"/>
    <fmt:message bundle="${loc}"  key="registration.login.note" var="login_note"/>
    <fmt:message bundle="${loc}"  key="registration.login.placeholder" var="login_placeholder"/>
    <fmt:message bundle="${loc}"  key="registration.password.note" var="pass_note"/>
    <fmt:message bundle="${loc}"  key="registration.password.placeholder" var="pass_placeholder"/>
    <fmt:message bundle="${loc}"  key="registration.pass_restrict.note" var="pass_restrict_note"/>
    <fmt:message bundle="${loc}"  key="registration.repeat_password.note" var="repeat_pass_note"/>
    <fmt:message bundle="${loc}"  key="registration.repeat_password.placeholder" var="repeat_pass_placeholder"/>
    <fmt:message bundle="${loc}"  key="registration.name.note" var="name_note"/>
    <fmt:message bundle="${loc}"  key="registration.name.placeholder" var="name_placeholder"/>
    <fmt:message bundle="${loc}"  key="registration.phone.note" var="phone_note"/>
    <fmt:message bundle="${loc}"  key="registration.phone.placeholder" var="phone_placeholder"/>
    <fmt:message bundle="${loc}"  key="registration.register.button" var="register_button"/>
    <fmt:message bundle="${loc}"  key="registration.to_initial_page.button" var="to_initial_page_button"/>
    <fmt:message bundle="${loc}"  key="registration.pass_restrict.message" var="pass_restrict_mess"/>
    <fmt:message bundle="${loc}"  key="registration.failed.message" var="registration_failed_mess"/>
    <fmt:message bundle="${loc}"  key="registration.exception.message" var="registration_exception_mess"/>

    <title>${default_title}</title>
</head>
<body>
<br/><br/><br/><br/>
<div class="container-fluid row">
    <div class="col-3">

    </div>
    <div class="col-6">
        <form action="Controller" method="post" autocomplete="off">
            <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.REGISTRATION}">
            <div class="row">
                <div class="col-5 text-end">
                    <label for="login">${login_note}</label>
                </div>
                <div class="col-5">
                    <input type="text" id="login" required autocomplete="on" name="${ParameterName.USER_LOGIN}" placeholder="${login_placeholder}">
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-5 text-end">
                    <label for="pass">${pass_note}</label>
                </div>
                <div class="col-5">
                    <input type="password" id="pass" required name="${ParameterName.USER_PASSWORD}" placeholder="${pass_placeholder}">
                    <p class="help-footnote small"> ${pass_restrict_note} </p>
                </div>
            </div>
            <div class="row">
                <div class="col-5 text-end">
                    <label for="pass_repeat">${repeat_pass_note}</label>
                </div>
                <div class="col-5">
                    <input type="password" id="pass_repeat"  required name="${ParameterName.USER_REPEAT_PASSWORD}" placeholder="${repeat_pass_placeholder}">
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-5 text-end">
                    <label for="name">${name_note}</label>
                </div>
                <div class="col-5">
                    <input type="text" id="name" autocomplete="on" name="${ParameterName.USER_NAME}" placeholder="${name_placeholder}">
                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-5 text-end">
                    <label for="phone">${phone_note}</label>
                </div>
                <div class="col-5">
                    <input type="text" id="phone" autocomplete="on" name="${ParameterName.USER_PHONE}" placeholder="${phone_placeholder}">
                </div>
            </div>
            <br/>
            <div class="text-center">
                Role<br/>
                <select name="user_role" size="1">
                    <option disabled selected>Choose your role</option>
                    <option value="admin">admin</option>
                    <option value="client">client</option>
                    <option value="driver">driver</option>
                </select>
                <br/><br/>

                <input type="submit" class="btn btn-success" value="${register_button}">
            </div>
        </form>

        <div class="text-center">
            <c:set var="errorMessage" scope="page" value="${requestScope.get(ParameterName.ERROR_MESSAGE)}"/>
            <c:if test="${not empty errorMessage}">
                <p class="text-danger">
                    <c:if test="${errorMessage eq Message.PASSWORD_RESTRICTION_WARN}">
                        <c:out value="${pass_restrict_mess}"/>
                    </c:if>
                    <c:if test="${errorMessage eq Message.REGISTRATION_FAILED}">
                        <c:out value="${registration_failed_mess}"/>
                    </c:if>
                    <c:if test="${errorMessage eq Message.REGISTRATION_EXCEPTION}">
                        <c:out value="${registration_exception_mess}"/>
                    </c:if>
                <p/>
            </c:if>
            <c:if test="${empty errorMessage}">
                <br/>
            </c:if>
        </div>

        <div class="text-center">
            <br/><br/><br/>
            <form action="Controller" method="get">
                <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_INITIAL_PAGE}">
                <input type="submit" class="btn btn-outline-info" value="${to_initial_page_button}">
            </form>
        </div>
    </div>
</div>
</body>
</html>
