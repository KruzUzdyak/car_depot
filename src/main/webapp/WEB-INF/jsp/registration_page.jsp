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
    <fmt:message bundle="${loc}"  key="registration.login.note" var="login_note"/>
    <fmt:message bundle="${loc}"  key="registration.password.note" var="pass_note"/>
    <fmt:message bundle="${loc}"  key="registration.pass_restrict.note" var="pass_restrict_note"/>
    <fmt:message bundle="${loc}"  key="registration.repeat_password.note" var="repeat_pass_note"/>
    <fmt:message bundle="${loc}"  key="registration.name.note" var="name_note"/>
    <fmt:message bundle="${loc}"  key="registration.phone.note" var="phone_note"/>
    <fmt:message bundle="${loc}"  key="registration.register.button" var="register_button"/>
    <fmt:message bundle="${loc}"  key="registration.to_initial_page.button" var="to_initial_page_button"/>
    <fmt:message bundle="${loc}"  key="registration.pass_restrict.message" var="pass_restrict_mess"/>
    <fmt:message bundle="${loc}"  key="registration.failed.message" var="registration_failed_mess"/>
    <fmt:message bundle="${loc}"  key="registration.exception.message" var="registration_exception_mess"/>

    <title>${title}</title>
</head>
<body>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="registration">
    ${login_note}<br/>
    <input type="text" name="user_login" value="">
    <br/><br/>
    ${pass_note}<br/>
    <input type="password" name="user_password" value=""><br/>
    <p class="help-footnote"> <c:out value="${pass_restrict_note}"/> </p>
    <br/><br/>
    ${repeat_pass_note}<br/>
    <input type="password" name="user_repeat_password" value="">
    <br/><br/>
    ${name_note}<br/>
    <input type="text" name="user_name" value="">
    <br/><br/>
    ${phone_note}<br/>
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
    <input type="submit" value="${register_button}">
</form>


<c:set var="errorMessage" scope="page" value="${requestScope.get(ParameterName.ERROR_MESSAGE)}"/>
<c:if test="${not empty errorMessage}">
    <h3 style="color:crimson">
        <c:if test="${errorMessage eq Message.PASSWORD_RESTRICTION_WARN}">
            <c:out value="${pass_restrict_mess}"/>
        </c:if>
        <c:if test="${errorMessage eq Message.REGISTRATION_FAILED}">
            <c:out value="${registration_failed_mess}"/>
        </c:if>
        <c:if test="${errorMessage eq Message.REGISTRATION_EXCEPTION}">
             <c:out value="${registration_exception_mess}"/>
        </c:if>
    </h3><br/>
</c:if>

<form action="Controller" method="get">
    <input type="hidden" name="command" value="go_to_initial_page">
    <input type="submit" value="${to_initial_page_button}">
</form>

</body>
</html>
