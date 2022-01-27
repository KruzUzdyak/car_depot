<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.volodko.controller.constant.CommandName" %>
<%@ page import="com.epam.volodko.controller.constant.ParameterName" %>
<%@ page import="com.epam.volodko.controller.constant.Message" %>
<%@ page import="com.epam.volodko.entity.user.Role" %>
<%@ page import="com.epam.volodko.entity.user.DriverLicenseType" %>
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
    <fmt:message bundle="${loc}" key="default.main_page.button" var="main_page_button"/>
    <fmt:message bundle="${loc}" key="default.sign_out.button" var="sign_out_button"/>
    <fmt:message bundle="${loc}" key="default.user_cabinet.button" var="user_cabinet_button"/>
    <fmt:message bundle="${loc}" key="locale.button.ru" var="locale_button_ru"/>
    <fmt:message bundle="${loc}" key="locale.button.en" var="locale_button_en"/>
    <fmt:message bundle="${loc}" key="locale.note" var="locale_note"/>


    <title>${default_title}</title>
</head>
<body>
<c:set var="userRole" value="${sessionScope.get(ParameterName.USER_ROLE)}"/>
<c:set var="newCar" value="${requestScope.get(ParameterName.NEW_CAR)}"/>
<c:set var="message" value="${requestScope.get(ParameterName.MESSAGE)}"/>
<c:set var="errorMessage" value="${param.get(ParameterName.ERROR_MESSAGE)}"/>
<c:set var="car" value="${requestScope.get(ParameterName.CAR)}"/>
<c:set var="carModelList" value="${requestScope.get(ParameterName.CAR_MODEL_LIST)}"/>
<c:set var="driverList" value="${requestScope.get(ParameterName.DRIVER_LIST)}"/>

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
                <div class="col-6">

                </div>
                <div class="col-2 text-center">
                    <form action="Controller" method="get">
                        <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_MAIN_PAGE}">
                        <input type="submit" class="btn btn-info" value="${main_page_button}">
                    </form>
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

<br/>
<c:if test="${not newCar}">
    <div class="container-fluid row">
        <div class="col-4 text-center">

        </div>
        <div class="col-4 ">
            <table class="table table-borderless table-striped text-center">
                <tr>
                    <th colspan="3">CAR</th>
                </tr>
                <c:if test="${errorMessage eq Message.CAR_INFO_LOAD_FAILED}">
                    <tr class="bg-warning">
                        <td colspan="3">${errorMessage}</td>
                    </tr>
                </c:if>
                <c:if test="${!errorMessage.equals(Message.CAR_INFO_LOAD_FAILED)}">
                    <tr>
                        <th>car id</th>
                        <td colspan="2">${car.id}</td>
                    </tr>
                    <tr>
                        <th>current driver id</th>
                        <td colspan="2">${car.driver.id}</td>
                    </tr>
                    <tr>
                        <th>current driver name</th>
                        <td colspan="2">${car.driver.name}</td>
                    </tr>
                    <tr>
                        <th>plate number</th>
                        <td colspan="2">${car.plateNumber}</td>
                    </tr>
                    <tr>
                        <th>current fuel</th>
                        <td colspan="2">${car.fuelLevel}</td>
                    </tr>
                    <tr>
                        <th>fuel tank capacity</th>
                        <td colspan="2">${car.model.fuelTank}</td>
                    </tr>
                    <tr>
                        <th>current mileage</th>
                        <td colspan="2">${car.mileage}</td>
                    </tr>
                    <tr>
                        <th>need repair</th>
                        <c:if test="${car.broken}">
                            <td colspan="2" class="text-danger">NEED REPAIR</td>
                        </c:if>
                        <c:if test="${not car.broken}">
                            <td colspan="2">workable</td>
                        </c:if>
                    </tr>
                    <tr>
                        <th>model id</th>
                        <td colspan="2">${car.model.id}</td>
                    </tr>
                    <tr>
                        <th>model name</th>
                        <td colspan="2">${car.model.modelName}</td>
                    </tr>
                    <tr>
                        <th>car load type</th>
                        <td colspan="2">${car.model.loadType}</td>
                    </tr>
                    <tr>
                        <th>car capacity</th>
                        <td colspan="2">${car.model.capacity}</td>
                    </tr>
                    <tr>
                        <th>car type id</th>
                        <td colspan="2">${car.model.type.carTypeId}</td>
                    </tr>
                    <tr>
                        <th>car type name</th>
                        <td colspan="2">${car.model.type.typeName}</td>
                    </tr>
                    <tr>
                        <th>required driving license</th>
                        <td colspan="2">${car.model.type.requiredLicense}</td>
                    </tr>
                </c:if>
            </table>

            <br/><br/>

        </div>
    </div>
</c:if>


<c:if test="${newCar}">
    <div class="container-fluid row">
        <div class="col-4">

        </div>
        <div class="col-4 ">
            <c:if test="${errorMessage eq Message.ADD_NEW_CAR_FAILED}">
                ${errorMessage}
            </c:if>
            <table class="table table-borderless table-striped text-center">
                <form action="Controller" method="post">
                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.ADD_NEW_CAR}">
                    <tr>
                        <th colspan="3">CAR</th>
                    </tr>
                    <tr>
                        <th>current driver</th>
                        <td colspan="2">
                            <c:if test="${errorMessage eq Message.DRIVER_LIST_LOAD_FAIL}">
                                ${errorMessage}
                            </c:if>
                            <select required name="${ParameterName.CAR_DRIVER_ID}">
                                <option selected value="0">EMPTY</option>
                                <c:forEach var="driver" items="${driverList}">
                                    <option value="${driver.id}">${driver.id} : ${driver.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>plate number</th>
                        <td colspan="2"><input type="text" required name="${ParameterName.CAR_PLATE_NUMBER}"></td>
                    </tr>
                    <tr>
                        <th>current fuel</th>
                        <td colspan="2"><input type="number" min="0" required  name="${ParameterName.CAR_FUEL_LEVEL}" placeholder="0"></td>
                    </tr>
                    <tr>
                        <th>current mileage</th>
                        <td colspan="2"><input type="number" min="0" required name="${ParameterName.CAR_MILEAGE}" placeholder="0"></td>
                    </tr>
                    <tr>
                        <th>Need repair</th>
                        <td colspan="2"><input type="checkbox" name="${ParameterName.CAR_BROKEN}"></td>
                    </tr>
                    <tr>
                        <th>model id</th>
                        <td colspan="2">
                            <c:if test="${errorMessage eq Message.CAR_MODELS_LOAD_FAILED}">
                                ${errorMessage}
                            </c:if>
                            <select required name="${ParameterName.CAR_MODEL_ID}">
                                <c:forEach var="carModel" items="${carModelList}">
                                    <option value="${carModel.id}">${carModel.modelName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" class="text-center">
                            <input type="submit" class="btn btn-success" value="ADD NEW CAR">
                        </td>
                    </tr>
                </form>
            </table>
        </div>
    </div>
</c:if>
</body>
</html>
