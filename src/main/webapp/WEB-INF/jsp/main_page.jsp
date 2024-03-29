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
    <fmt:message bundle="${loc}"  key="default.title" var="default_title"/>
    <fmt:message bundle="${loc}" key="default.sign_out.button" var="sign_out_button"/>
    <fmt:message bundle="${loc}" key="default.user_cabinet.button" var="user_cabinet_button"/>
    <fmt:message bundle="${loc}" key="locale.button.ru" var="locale_button_ru"/>
    <fmt:message bundle="${loc}" key="locale.button.en" var="locale_button_en"/>
    <fmt:message bundle="${loc}" key="locale.note" var="locale_note"/>
    <fmt:message bundle="${loc}" key="main.registration_successful.message" var="registration_successful_mess"/>
    <fmt:message bundle="${loc}" key="main.logination_successful.message" var="logination_successful_mess"/>
    <fmt:message bundle="${loc}" key="main.table.name" var="table_name"/>
    <fmt:message bundle="${loc}" key="main.table.th.model" var="th_car_model"/>
    <fmt:message bundle="${loc}" key="main.table.th.driver_name" var="th_driver_name"/>
    <fmt:message bundle="${loc}" key="main.table.th.load_type" var="th_load_type"/>
    <fmt:message bundle="${loc}" key="main.table.th.capacity" var="th_capacity"/>
    <fmt:message bundle="${loc}" key="main.table.th.availability" var="th_availability"/>
    <fmt:message bundle="${loc}" key="main.table.td.available" var="td_available"/>
    <fmt:message bundle="${loc}" key="main.table.td.not_available" var="td_not_available"/>
    <fmt:message bundle="${loc}" key="main.cars_loading_failed" var="td_car_loading_failed"/>

    <title>${default_title}</title>
</head>
<body>

<c:set var="userRole" scope="page" value="${sessionScope.get(ParameterName.USER_ROLE)}"/>
<c:set var="greeting_message" value="${param.get(ParameterName.GREETING_MESSAGE)}"/>
<c:set var="errorMessage" scope="page" value="${requestScope.get(ParameterName.ERROR_MESSAGE)}"/>
<c:set var="carsList" scope="page" value="${requestScope.get(ParameterName.CAR_LIST)}"/>

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
                <div class="col-2 text-center">
                    <form action="Controller" method="get">
                        <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_USER_CABINET_PAGE}">
                        <input type="submit" class="btn btn-info" value="${user_cabinet_button}">
                    </form>
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

<div class="container-fluid row">
    <div class="col-3">

    </div>
    <c:if test="${not empty greeting_message}">
        <div class="col-6 text-center text-success">
            <h4>
                <c:if test="${greeting_message eq Message.REGISTRATION_SUCCESSFUL}">
                    <c:out value="${registration_successful_mess}"/>
                </c:if>
                <c:if test="${greeting_message eq Message.LOGINATION_SUCCESSFUL}">
                    <c:out value="${logination_successful_mess}"/>
                </c:if>
            </h4>
            <br/>
            <br/>
        </div>
    </c:if>
    <c:if test="${empty greeting_message}">
        <div class="col-6">
            <br/>
            <br/>
            <br/>
        </div>
    </c:if>


</div>

<div class="container-fluid row">
    <div class="col-3">

    </div>
    <div class="col-6">
        <table class="table table-bordered table-striped text-center">
            <tr>
                <c:if test="${userRole != Role.ADMIN}">
                    <th colspan="5">${table_name}</th>
                </c:if>
                <c:if test="${userRole eq Role.ADMIN}">
                    <th colspan="6">${table_name}</th>
                </c:if>
            </tr>
            <tr>
                <th>${th_car_model}</th>
                <th>${th_driver_name}</th>
                <th>${th_load_type}</th>
                <th>${th_capacity}</th>
                <th>${th_availability}</th>
                <c:if test="${userRole eq Role.ADMIN}">
                    <th><fmt:message bundle="${loc}" key="main.table.th.info"/></th>
                </c:if>
            </tr>
            <c:if test="${errorMessage eq Message.CARS_LOAD_FAILED}">
                <tr class="bg-warning">
                    <td colspan="5">${td_car_loading_failed}</td>
                </tr>
            </c:if>
            <c:if test="${empty errorMessage}">
                <c:forEach var="car" items="${carsList}">
                    <tr>
                        <td><p>${car.model.modelName}</p></td>
                        <td><p>${car.driver.name}</p></td>
                        <td><p>${car.model.loadType}</p></td>
                        <td><p>${car.model.capacity}</p></td>
                        <c:if test="${not car.broken}">
                            <td class="text-primary">${td_available}</td>
                        </c:if>
                        <c:if test="${car.broken}">
                            <td class="text-muted">${td_not_available}</td>
                        </c:if>
                        <c:if test="${userRole eq Role.ADMIN}">
                            <td class="text-center">
                                <form action="Controller" method="get">
                                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_CAR_INFO_PAGE}" >
                                    <input type="hidden" name="${ParameterName.CAR_REQUEST_TYPE}" value="${ParameterName.CAR_BY_ID}">
                                    <input type="hidden" name="${ParameterName.CAR_ID}" value="${car.id}">
                                    <input type="submit" class="btn btn-info" value="INFO">
                                </form>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
</div>

<div class="container-fluid row">
    <c:if test="${userRole eq Role.CLIENT}">
        <div class="col-5">

        </div>
        <div class="col-2 text-center">
            <form action="Controller" method="get">
                <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_CREATE_ORDER_PAGE}">
                <input type="submit" class="btn btn-success" value="CREATE_ORDER">
            </form>
        </div>
    </c:if>

    <c:if test="${userRole eq Role.ADMIN}">
        <div class="col-2">

        </div>
        <div class="col-2 text-center">
            <form action="Controller" method="get">
                <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_ALL_ORDERS_PAGE}">
                <input type="hidden" name="${ParameterName.ORDER_LIST_TYPE}" value="${ParameterName.ORDER_LIST_ALL}">
                <input type="submit" class="btn btn-info" value="ALL_ORDERS">
            </form>
        </div>
        <div class="col-2 text-center">
            <form action="Controller" method="get">
                <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_REGISTRATION}">
                <input type="hidden" name="${ParameterName.REGISTER_ROLE}" value="${Role.ADMIN}">
                <input type="submit" class="btn btn-info" value="REGISTER_NEW_ADMIN">
            </form>
        </div>
        <div class="col-2 text-center">
            <form action="Controller" method="get">
                <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_REGISTRATION}">
                <input type="hidden" name="${ParameterName.REGISTER_ROLE}" value="${Role.DRIVER}">
                <input type="submit" class="btn btn-info" value="REGISTER_NEW_DRIVER">
            </form>
        </div>
        <div class="col-2 text-center">
            <form action="Controller" method="get">
                <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_ADD_NEW_CAR}">
                <input type="submit" class="btn btn-info" value="ADD NEW CAR">
            </form>
        </div>
    </c:if>

    <c:if test="${userRole eq Role.DRIVER}">
        <div class="col-5">

        </div>
        <div class="col-2 text-center">
            <form action="Controller" method="get">
                <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_ALL_ORDERS_PAGE}">
                <input type="hidden" name="${ParameterName.ORDER_LIST_TYPE}" value="${ParameterName.ORDER_LIST_CAR}">
                <input type="submit" class="btn btn-info" value="ALL_ORDERS">
            </form>
        </div>
    </c:if>
</div>

</body>
</html>
