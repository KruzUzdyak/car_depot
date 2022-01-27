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
    <fmt:message bundle="${loc}" key="default.sign_out.button" var="sign_out_button"/>
    <fmt:message bundle="${loc}" key="default.main_page.button" var="main_page_button"/>
    <fmt:message bundle="${loc}" key="default.user_cabinet.button" var="user_cabinet_button"/>
    <fmt:message bundle="${loc}" key="locale.button.ru" var="locale_button_ru"/>
    <fmt:message bundle="${loc}" key="locale.button.en" var="locale_button_en"/>
    <fmt:message bundle="${loc}" key="locale.note" var="locale_note"/>

    <title>${default_title}</title>
</head>
<body>

<c:set var="userRole" scope="page" value="${sessionScope.get(ParameterName.USER_ROLE)}"/>
<c:set var="errorMessage" scope="page" value="${requestScope.get(ParameterName.ERROR_MESSAGE)}"/>

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

<br/><br/>


<div class="container-fluid row">
    <div class="col-4">

    </div>
    <div class="col-4 ">
        <form action="Controller" method="post">
            <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.CREATE_ORDER}">
            <table class="table table-borderless table-striped text-center">
                <tr>
                    <th colspan="3">CREATE ORDER</th>
                </tr>
                <tr>
                    <th>destination from</th>
                    <td colspan="2">
                        <input type="text" name="${ParameterName.ORDER_DEST_FROM}" placeholder="INPUT YOUR FIRST ADDRESS">
                    </td>
                </tr>
                <tr>
                    <th>destination to</th>
                    <td colspan="2">
                        <input type="text" name="${ParameterName.ORDER_DEST_TO}" placeholder="INPUT YOUR SECOND ADDRESS">
                    </td>
                </tr>
                <tr>
                    <th>distance, km</th>
                    <td colspan="2">
                        <input type="number" min="0" name="${ParameterName.ORDER_DISTANCE}" value="" placeholder="INPUT DISTANCE BETWEEN ADDRESSES">
                    </td>
                </tr>
                <tr>
                    <th>date start</th>
                    <td colspan="2">
                        <input type="date" name="${ParameterName.ORDER_DATE_START}">
                    </td>
                </tr>
                <tr>
                    <th>deadline</th>
                    <td colspan="2">
                        <input type="date" name="${ParameterName.ORDER_DATE_END}">
                    </td>
                </tr>
                <tr>
                    <th>load</th>
                    <td colspan="2">
                        <input type="number" min="0" name="${ParameterName.ORDER_LOAD}" placeholder="INPUT SIZE OF THE CARGO">
                    </td>
                </tr>
                <tr>
                    <th>load note</th>
                    <td colspan="2">
                        <textarea name="${ParameterName.ORDER_LOAD_NOTE}" maxlength="200" cols="30" rows="3"
                                  placeholder="MAX 200 SYMBOLS"></textarea>
                    </td>
                </tr>
                <tr>
                    <th>payment, $</th>
                    <td colspan="2">
                        <input type="number" min="0" name="${ParameterName.ORDER_PAYMENT}" placeholder="PLEASE INPUT THE REWARD VALUE">
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input type="submit" class="btn btn-success" value="CREATE">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

</body>
</html>
