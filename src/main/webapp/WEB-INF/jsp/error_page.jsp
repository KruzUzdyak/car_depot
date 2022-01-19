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
    <fmt:message bundle="${loc}" key="default.title" var="default_title"/>

    <title>${default_title}</title>
</head>
<body>

<c:set var="errorMessage" value="${requestScope.get(ParameterName.ERROR_MESSAGE)}"/>

<br/><br/><br/>

<div class="text-center text-info">
    <p>THIS IS ERROR PAGE</p>

    <br/><br/>

    <c:if test="${not empty errorMessage}">
        <h4>${errorMessage}</h4>
    </c:if>

    <form action="Controller" method="get">
        <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_MAIN_PAGE}">
        <input type="submit" class="btn btn-info" value="GO TO MAIN PAGE">
    </form>
</div>


</body>
</html>
