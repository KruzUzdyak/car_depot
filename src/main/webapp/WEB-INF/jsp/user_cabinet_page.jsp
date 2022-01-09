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
                <div class="col-2">

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

<c:set var="user" value="${requestScope.get(ParameterName.USER)}"/>
<c:set var="updateInfo" value="${requestScope.get(ParameterName.USER_UPDATE_INFO)}"/>
<c:set var="updateLogin" value="${requestScope.get(ParameterName.USER_UPDATE_LOGIN)}"/>
<c:set var="updatePass" value="${requestScope.get(ParameterName.USER_UPDATE_PASS)}"/>
<c:set var="saveLicense" value="${requestScope.get(CommandName.SAVE_LICENSE)}"/>
<c:set var="deleteLicense" value="${requestScope.get(CommandName.DELETE_LICENSE)}"/>
<c:set var="userRole" value="${requestScope.get(ParameterName.USER_ROLE)}"/>
<c:set var="message" value="${requestScope.get(ParameterName.MESSAGE)}"/>
<c:set var="errorMessage" value="${requestScope.get(ParameterName.ERROR_MESSAGE)}"/>

<c:if test="${not empty errorMessage}">
    <div class="text-center text-warning">
        <h3>
            ${errorMessage}
        </h3>
    </div>
</c:if>

<br/><br/><br/>

<div class="container-fluid row">
    <div class="col-4">

    </div>
    <div class="col-4">
        <form action="Controller" method="post">
            <table class="table table-borderless table-striped">
                <tr>
                    <th colspan="4" class="text-center">${user.role.toString()} cabinet</th>
                </tr>
                <tr>
                    <c:if test="${not updateLogin}">
                        <th>Login: </th>
                        <td colspan="3">${user.login}</td>
                    </c:if>
                    <c:if test="${updateLogin}">
                        <th><label for="newLogin">Login: </label></th>
                        <td colspan="3">
                            <input type="text" id="newLogin" required name="${ParameterName.USER_LOGIN}" value="${user.login}">
                        </td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${not updateInfo}">
                        <th>Name: </th>
                        <td colspan="3">${user.name}</td>
                    </c:if>
                    <c:if test="${updateInfo}">
                        <th><label for="newName">Login: </label></th>
                        <td colspan="3">
                            <input type="text" id="newName" name="${ParameterName.USER_NAME}" value="${user.name}">
                        </td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${not updateInfo}">
                        <th>Phone: </th>
                        <td colspan="3">${user.phone}</td>
                    </c:if>
                    <c:if test="${updateInfo}">
                        <th><label for="newPhone">Phone: </label></th>
                        <td colspan="3">
                            <input type="text" id="newPhone" name="${ParameterName.USER_PHONE}" value="${user.phone}">
                        </td>
                    </c:if>
                </tr>

                <c:if test="${user.role eq Role.ADMIN}">
                    <tr>
                        <c:if test="${not updateInfo}">
                            <th>Work since: </th>
                            <td colspan="3">${user.worksSince}</td>
                        </c:if>
                        <c:if test="${updateInfo}">
                            <th><label for="newWordSince">WorkSince: </label></th>
                            <td colspan="3"><p>
                                <input type="date" id="newWordSince" name="${ParameterName.ADMIN_WORKS_SINCE}" value="${user.worksSince}">
                            </p></td>
                        </c:if>
                    </tr>
                    <tr>
                        <c:if test="${not updateInfo}">
                            <th>Note: </th>
                            <td colspan="3">${user.note}</td>
                        </c:if>
                        <c:if test="${updateInfo}">
                            <th><label for="newAdminNote">Note: </label></th>
                            <td colspan="3">
                                <textarea cols="50" rows="5" id="newAdminNote" name="${ParameterName.ADMIN_NOTE}"
                                          placeholder="500 symbols max" maxlength="500">${user.note}</textarea>
                            </td>
                        </c:if>

                    </tr>
                </c:if>
                <c:if test="${user.role eq Role.CLIENT}">
                    <tr>
                        <c:if test="${not updateInfo}">
                            <th>Company: </th>
                            <td colspan="3">${user.company}</td>
                        </c:if>
                        <c:if test="${updateInfo}">
                            <th><label for="newCompany">Company: </label></th>
                            <td colspan="3">
                                <input type="text" id="newCompany" name="${ParameterName.CLIENT_COMPANY}" value="${user.company}">

                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <c:if test="${not updateInfo}">
                            <th>Note: </th>
                            <td colspan="3">${user.note}</td>
                        </c:if>
                        <c:if test="${updateInfo}">
                            <th><label for="newClientNote">Note: </label></th>
                            <td colspan="3">
                                <textarea cols="50" rows="5" id="newClientNote" name="${ParameterName.CLIENT_NOTE}"
                                          placeholder="500 symbols max" maxlength="500">${user.note}</textarea>
                            </td>
                        </c:if>
                    </tr>
                </c:if>

                <c:if test="${updateLogin}">
                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.UPDATE_LOGIN_CONFIRM}">
                    <tr>
                        <td colspan="4" class="text-center">
                            <input type="submit" class="btn btn-success" value="Save new login">
                        </td>
                    </tr>
                </c:if>

                <c:if test="${updatePass}">
                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.UPDATE_PASS_CONFIRM}">
                    <tr>
                        <th><label for="pass">New password</label></th>
                        <td>
                            <input type="password" id="pass" required name="${ParameterName.USER_PASS}" placeholder="Input new pass">
                        </td>
                    </tr>
                    <tr>
                        <th><label for="repeatPass">Repeat password</label></th>
                        <td>
                            <input type="password" id="repeatPass" required name="${ParameterName.USER_REPEAT_PASS}" placeholder="Repeat pass">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" class="text-center">
                            <input type="submit" class="btn btn-success" value="Save new pass">
                        </td>
                    </tr>
                </c:if>

                <c:if test="${updateInfo}">
                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.UPDATE_INFO_CONFIRM}">
                    <tr>
                        <td colspan="4" class="text-center">
                            <input type="submit" class="btn btn-success" value="Save updated info">
                        </td>
                    </tr>
                </c:if>
            </table>
        </form>

        <c:if test="${not updateLogin and not updatePass and not updateInfo}">
            <div class="container-fluid row">
                <div class="col-4">
                    <form action="Controller" method="get">
                        <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.UPDATE_LOGIN}">
                        <input type="submit" class="btn btn-outline-info" value="UPDATE_LOGIN">
                    </form>
                </div>
                <div class="col-4">
                    <form action="Controller" method="get">
                        <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.UPDATE_PASS}">
                        <input type="submit" class="btn btn-outline-info" value="UPDATE_PASS">
                    </form>
                </div>
                <div class="col-4">
                    <form action="Controller" method="get">
                        <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.UPDATE_INFO}">
                        <input type="submit" class="btn btn-outline-info" value="UPDATE_INFO">
                    </form>
                </div>
            </div>
        </c:if>

        <br/><br/>

        <c:if test="${user.role eq Role.DRIVER}">
            <table class="table table-bordered table-striped text-center">
                <tr>
                    <th colspan="3">Your driver licenses</th>
                </tr>
                <tr>
                    <th>License category</th>
                    <th>Obtaining date</th>
                    <th>License number</th>
                </tr>
                <c:forEach var="license" items="${user.licenses}">
                    <tr>
                        <td>
                            <c:if test="${not deleteLicense}">
                                ${license.licenseType}
                            </c:if>
                            <c:if test="${deleteLicense}">
                                <form action="Controller" method="post">
                                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.DELETE_LICENSE_CONFIRM}">
                                    <input type="hidden" name="${ParameterName.LICENSE_TYPE_ID}" value="${license.licenseType.id}">
                                    <input type="submit" class="btn btn-warning" value="DELETE ${license.licenseType}">
                                </form>
                            </c:if>
                        </td>
                        <td>${license.obtainingDate}</td>
                        <td>${license.licenseNumber}</td>
                    </tr>
                </c:forEach>
                <c:if test="${saveLicense}">
                    <form action="Controller" method="post">
                        <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.SAVE_LICENSE_CONFIRM}">
                        <tr>
                            <td>
                                <select required name="${ParameterName.LICENSE_TYPE_ID}">
                                    <option value="${DriverLicenseType.AM.id}">${DriverLicenseType.AM}</option>
                                    <option value="${DriverLicenseType.A.id}">${DriverLicenseType.A}</option>
                                    <option value="${DriverLicenseType.A1.id}">${DriverLicenseType.A1}</option>
                                    <option value="${DriverLicenseType.B.id}">${DriverLicenseType.B}</option>
                                    <option value="${DriverLicenseType.C.id}">${DriverLicenseType.C}</option>
                                    <option value="${DriverLicenseType.D.id}">${DriverLicenseType.A}</option>
                                    <option value="${DriverLicenseType.BE.id}">${DriverLicenseType.BE}</option>
                                    <option value="${DriverLicenseType.CE.id}">${DriverLicenseType.CE}</option>
                                    <option value="${DriverLicenseType.DE.id}">${DriverLicenseType.DE}</option>
                                    <option value="${DriverLicenseType.F.id}">${DriverLicenseType.F}</option>
                                    <option value="${DriverLicenseType.I.id}">${DriverLicenseType.I}</option>
                                </select>
                            </td>
                            <td>
                                <input type="date" required name="${ParameterName.LICENSE_OBTAINING_DATE}" placeholder="OBTAINING DATE">
                            </td>
                            <td>
                                <input type="text" required name="${ParameterName.LICENSE_NUMBER}" placeholder="LICENSE NUMBER">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <input type="submit" class="btn btn-success" value="SAVE NEW LICENSE">
                            </td>
                        </tr>
                    </form>
                </c:if>
            </table>

            <c:if test="${message eq Message.LICENSE_DELETE_SUCCESSFUL}">
                <div class="text-center text-success">
                    <p>${message}</p>
                </div>
            </c:if>

            <c:if test="${not saveLicense and not deleteLicense}">
                <div class="container-fluid row text-center">
                    <div class="col-6">
                        <form action="Controller" method="get">
                            <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.SAVE_LICENSE}">
                            <input type="submit" class="btn btn-outline-info" value="SAVE_NEW_LICENSE">
                        </form>
                    </div>
                    <div class="col-6">
                        <form action="Controller" method="get">
                            <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.DELETE_LICENSE}">
                            <input type="submit" class="btn btn-outline-info" value="DELETE_LICENSE">
                        </form>
                    </div>
                </div>
            </c:if>
        </c:if>
    </div>
</div>

</body>
</html>
