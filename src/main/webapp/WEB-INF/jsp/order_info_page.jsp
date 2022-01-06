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

<br/><br/>

<div class="container-fluid row">
    <div class="col-4">

    </div>
    <div class="col-4 ">
        <c:set var="errorMessage" scope="page" value="${requestScope.get(ParameterName.ERROR_MESSAGE)}"/>
        <c:set var="order" scope="page" value="${requestScope.get(ParameterName.ORDER)}"/>
        <table class="table table-borderless table-striped text-center">
            <tr>
                <th colspan="3">ORDER</th>
            </tr>
            <c:if test="${not empty errorMessage}">
                <tr class="bg-warning">
                    <td colspan="3">ORDER_LOAD_FAILED</td>
                </tr>
            </c:if>
            <c:if test="${empty errorMessage}">
                <tr>
                    <th>order id</th>
                    <td colspan="2">${order.id}</td>
                </tr>
                <tr>
                    <th>destination from</th>
                    <td colspan="2">${order.destFrom}</td>
                </tr>
                <tr>
                    <th>destination to</th>
                    <td colspan="2">${order.destTo}</td>
                </tr>
                <tr>
                    <th>distance, km</th>
                    <td colspan="2">${order.distance}</td>
                </tr>
                <tr>
                    <th>date start</th>
                    <td colspan="2">${order.dateStart}</td>
                </tr>
                <tr>
                    <th>deadline</th>
                    <td colspan="2">${order.dateEnd}</td>
                </tr>
                <tr>
                    <th>load</th>
                    <td colspan="2">${order.load}</td>
                </tr>
                <tr>
                    <th>load note</th>
                    <td colspan="2">${order.loadNote}</td>
                </tr>
                <tr>
                    <th>completed</th>
                    <td colspan="2">${order.completed}</td>
                </tr>
                <tr>
                    <th>payment, $</th>
                    <td colspan="2">${order.payment}</td>
                </tr>
                <tr>
                    <th>client</th>
                    <td colspan="2">${order.client.name}</td>
                </tr>
                <tr>
                    <th>admin</th>
                    <td colspan="2">${order.admin.name}</td>
                </tr>
                <tr>
                    <th>car model</th>
                    <td colspan="2">${order.car.model.modelName}</td>
                </tr>
                <tr>
                    <th>car plate number</th>
                    <td colspan="2">${order.car.plateNumber}</td>
                </tr>
            </c:if>
        </table>
    </div>
</div>

</body>
</html>
