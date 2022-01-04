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
    <fmt:message bundle="${loc}" key="main.table.name" var="table_name"/>
    <fmt:message bundle="${loc}" key="main.table.th.model" var="th_car_model"/>
    <fmt:message bundle="${loc}" key="main.table.th.driver_name" var="th_driver_name"/>
    <fmt:message bundle="${loc}" key="main.table.th.load_type" var="th_load_type"/>
    <fmt:message bundle="${loc}" key="main.table.th.capacity" var="th_capacity"/>
    <fmt:message bundle="${loc}" key="main.table.th.availability" var="th_availability"/>
    <fmt:message bundle="${loc}" key="main.table.td.available" var="td_available"/>
    <fmt:message bundle="${loc}" key="main.table.td.not_available" var="td_not_available"/>
    <fmt:message bundle="${loc}" key="main.cars_loading_failed" var="td_car_loading_failed"/>



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

<div class="text-center">
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
    <c:if test="${empty greeting_message}">
        <br/>
    </c:if>
</div>

<c:set var="errorMessage" scope="page" value="${requestScope.get(ParameterName.ERROR_MESSAGE)}"/>
<c:set var="carsList" scope="page" value="${requestScope.get(ParameterName.CARS_ALL)}"/>

<div class="container-fluid row">
    <div class="col-3">

    </div>
    <div class="col-6">
        <table class="table table-bordered table-striped text-center">
            <tr>
                <th>
                    ${table_name}
                </th>
            </tr>
            <tr>
                <th>
                    ${th_car_model}
                </th>
                <th>
                    ${th_driver_name}
                </th>
                <th>
                    ${th_load_type}
                </th>
                <th>
                    ${th_capacity}
                </th>
                <th>
                    ${th_availability}
                </th>
            </tr>
            <c:if test="${not empty errorMessage}">
                <tr class="bg-danger">
                    <td>
                        ${td_car_loading_failed}
                    </td>
                    <td>
                        ${td_car_loading_failed}
                    </td>
                    <td>
                        ${td_car_loading_failed}
                    </td>
                    <td>
                        ${td_car_loading_failed}
                    </td>
                    <td>
                        ${td_car_loading_failed}
                    </td>
                </tr>
            </c:if>
            <c:if test="${empty errorMessage}">
                <c:forEach var="car" items="${carsList}">
                    <tr>
                        <td>
                            <p>${car.model.modelName}</p>
                        </td>
                        <td>
                            <p>${car.driver.name}</p>
                        </td>
                        <td>
                            <p>${car.model.loadType}</p>
                        </td>
                        <td>
                            <p>${car.model.capacity}</p>
                        </td>
                        <td>
                            <c:if test="${not car.broken}">
                                <p class="text-primary">${td_available}</p>
                            </c:if>
                            <c:if test="${car.broken}">
                                <p class="text-muted">${td_not_available}</p>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
</div>

</body>
</html>
