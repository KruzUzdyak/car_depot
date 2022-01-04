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
    <fmt:message bundle="${loc}" key="locale.button.ru" var="locale_button_ru"/>
    <fmt:message bundle="${loc}" key="locale.button.en" var="locale_button_en"/>
    <fmt:message bundle="${loc}" key="locale.note" var="locale_note"/>
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
                <div class="col-2 text-end">
                    <form action="Controller" method="get">
                        <input type="hidden" name="TO_USER_PAGE" value="TO_USER_PAGE">
                        <input type="submit" class="btn btn-info" value="USER_CABINET">
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
    <div class="col-1">

    </div>
    <div class="col-10">
        <c:set var="errorMessage" scope="page" value="${requestScope.get(ParameterName.ERROR_MESSAGE)}"/>
        <c:set var="ordersList" scope="page" value="${requestScope.get(ParameterName.ORDERS_LIST)}"/>
        <table class="table table-bordered table-striped text-center">
            <tr>
                <th colspan="13">CAR_TABLE</th>
            </tr>
            <tr>
                <th>order id</th>
                <th>destination from</th>
                <th>destination to</th>
                <th>distance</th>
                <th>date start</th>
                <th>date end</th>
                <th>load amount</th>
                <th>load note</th>
                <th>status</th>
                <th>payment</th>
                <th>client name</th>
                <th>admin name</th>
                <th>car's plate number</th>
            </tr>
            <c:if test="${not empty errorMessage}">
                <tr class="bg-warning">
                    <td>ORDER_LOAD_FAILED</td>
                    <td>ORDER_LOAD_FAILED</td>
                    <td>ORDER_LOAD_FAILED</td>
                    <td>ORDER_LOAD_FAILED</td>
                    <td>ORDER_LOAD_FAILED</td>
                </tr>
            </c:if>
            <c:if test="${empty errorMessage}">
                <c:forEach var="order" items="${ordersList}">
                    <c:if test="${order.completed}">
                        <tr class="table-secondary">
                            <td><p>${order.id}</p></td>
                            <td><p>${order.destFrom}</p></td>
                            <td><p>${order.destTo}</p></td>
                            <td><p>${order.distance}</p></td>
                            <td><p>${order.dateStart}</p></td>
                            <td><p>${order.dateEnd}</p></td>
                            <td><p>${order.load}</p></td>
                            <td><p>${order.loadNote}</p></td>
                            <td>COMPLETED</td>
                            <td><p>${order.payment}</p></td>
                            <td><p>${order.client.name}</p></td>
                            <td><p>${order.admin.name}</p></td>
                            <td><p>${order.car.plateNumber}</p></td>
                        </tr>
                    </c:if>
                    <c:if test="${not order.completed}">
                        <tr class="table-info">
                            <td><p>${order.id}</p></td>
                            <td><p>${order.destFrom}</p></td>
                            <td><p>${order.destTo}</p></td>
                            <td><p>${order.distance}</p></td>
                            <td><p>${order.dateStart}</p></td>
                            <td><p>${order.dateEnd}</p></td>
                            <td><p>${order.load}</p></td>
                            <td><p>${order.loadNote}</p></td>
                            <td>NOT COMPLETED</td>
                            <td><p>${order.payment}</p></td>
                            <td><p>${order.client.name}</p></td>
                            <td><p>${order.admin.name}</p></td>
                            <td><p>${order.car.plateNumber}</p></td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:if>
        </table>
    </div>
</div>
</body>
</html>
