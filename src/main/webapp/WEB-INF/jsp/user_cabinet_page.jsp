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
    <fmt:message bundle="${loc}" key="default.sign_out.button" var="sign_out_button"/>
    <fmt:message bundle="${loc}" key="default.main_page.button" var="main_page_button"/>
    <fmt:message bundle="${loc}" key="locale.button.ru" var="locale_button_ru"/>
    <fmt:message bundle="${loc}" key="locale.button.en" var="locale_button_en"/>
    <fmt:message bundle="${loc}" key="locale.note" var="locale_note"/>
    <fmt:message bundle="${loc}" key="cabinet.table_title.note" var="table_title_note"/>
    <fmt:message bundle="${loc}" key="cabinet.table_title.admin" var="table_title_admin"/>
    <fmt:message bundle="${loc}" key="cabinet.table_title.client" var="table_title_client"/>
    <fmt:message bundle="${loc}" key="cabinet.table_title.driver" var="table_title_driver"/>
    <fmt:message bundle="${loc}" key="cabinet.login.note" var="login_note"/>
    <fmt:message bundle="${loc}" key="cabinet.name.note" var="name_note"/>
    <fmt:message bundle="${loc}" key="cabinet.phone.note" var="phone_note"/>
    <fmt:message bundle="${loc}" key="cabinet.admin_work_since.note" var="admin_work_since_note"/>
    <fmt:message bundle="${loc}" key="cabinet.admin_note.note" var="admin_note_note"/>
    <fmt:message bundle="${loc}" key="cabinet.admin_note.placeholder" var="admin_note_placeholder"/>
    <fmt:message bundle="${loc}" key="cabinet.client_company.note" var="client_company_note"/>
    <fmt:message bundle="${loc}" key="cabinet.client_note.note" var="client_note_note"/>
    <fmt:message bundle="${loc}" key="cabinet.client_note.placeholder" var="client_note_placeholder"/>
    <fmt:message bundle="${loc}" key="cabinet.new_pass.note" var="new_pass_note"/>
    <fmt:message bundle="${loc}" key="cabinet.new_pass.placeholder" var="new_pass_placeholder"/>
    <fmt:message bundle="${loc}" key="cabinet.new_pass_repeat.note" var="new_pass_repeat_note"/>
    <fmt:message bundle="${loc}" key="cabinet.new_pass_repeat_placeholder" var="new_pass_repeat_placeholder"/>
    <fmt:message bundle="${loc}" key="cabinet.update_login.button" var="update_login_button"/>
    <fmt:message bundle="${loc}" key="cabinet.update_pass.button" var="update_pass_button"/>
    <fmt:message bundle="${loc}" key="cabinet.update_info.button" var="update_info_button"/>
    <fmt:message bundle="${loc}" key="cabinet.licenses_table_title.note" var="licenses_table_title_note"/>
    <fmt:message bundle="${loc}" key="cabinet.license_category.note" var="licenses_category_note"/>
    <fmt:message bundle="${loc}" key="cabinet.obtaining_date.note" var="obtaining_date"/>
    <fmt:message bundle="${loc}" key="cabinet.license_number.note" var="license_number"/>
    <fmt:message bundle="${loc}" key="cabinet.license_delete_confirm.button" var="license_delete_confirm_button"/>
    <fmt:message bundle="${loc}" key="cabinet.license_delete.button" var="license_delete_button"/>
    <fmt:message bundle="${loc}" key="cabinet.license_save.button" var="license_save_button"/>
    <fmt:message bundle="${loc}" key="cabinet.view_orders.button" var="view_orders_button"/>
    <fmt:message bundle="${loc}" key="cabinet.view_car.button" var="view_car_button"/>
    <fmt:message bundle="${loc}" key="cabinet.license_delete_successful" var="license_delete_successful_mess"/>
    <fmt:message bundle="${loc}" key="cabinet.login_update_successful" var="login_update_mess"/>
    <fmt:message bundle="${loc}" key="cabinet.pass_update_successful" var="pass_update_mess"/>
    <fmt:message bundle="${loc}" key="cabinet.info_update_successful" var="info_update_mess"/>
    <fmt:message bundle="${loc}" key="cabinet.update_login_failed" var="update_login_failed"/>
    <fmt:message bundle="${loc}" key="cabinet.update_password_failed" var="update_pass_failed"/>
    <fmt:message bundle="${loc}" key="cabinet.update_info_failed" var="update_info_failed"/>
    <fmt:message bundle="${loc}" key="cabinet.save_new_license_failed" var="save_new_license_failed"/>
    <fmt:message bundle="${loc}" key="cabinet.delete_license_failed" var="delete_license_failed"/>

    <title>${default_title}</title>
</head>
<body>
<c:set var="locale" value="${sessionScope.get(ParameterName.LOCALE)}"/>
<c:set var="user" value="${requestScope.get(ParameterName.USER)}"/>
<c:set var="updateInfo" value="${requestScope.get(ParameterName.USER_UPDATE_INFO)}"/>
<c:set var="updateLogin" value="${requestScope.get(ParameterName.USER_UPDATE_LOGIN)}"/>
<c:set var="updatePass" value="${requestScope.get(ParameterName.USER_UPDATE_PASS)}"/>
<c:set var="saveLicense" value="${requestScope.get(CommandName.SAVE_LICENSE)}"/>
<c:set var="deleteLicense" value="${requestScope.get(CommandName.DELETE_LICENSE)}"/>
<c:set var="userRole" value="${sessionScope.get(ParameterName.USER_ROLE)}"/>
<c:set var="message" value="${param.get(ParameterName.MESSAGE)}"/>
<c:set var="errorMessage" value="${requestScope.get(ParameterName.ERROR_MESSAGE)}"/>

<nav class="navbar navbar-default">
    <div class="container-fluid bg-light">
        <div class="navbar-header row">
            <div class="col-4">
                <c:out value="${locale_note}"/>
            </div>
            <div class="col-2">
                <form action="Controller" method="post">
                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.LOCALIZATION}">
                    <input type="hidden" name="${ParameterName.LOCALE}" value="${ParameterName.LOCALE_RU}">
                    <input type="submit" class="btn btn-outline-info" value="${locale_button_ru}">
                </form>
            </div>
            <div class="col-2">
                <form action="Controller" method="post">
                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.LOCALIZATION}">
                    <input type="hidden" name="${ParameterName.LOCALE}" value="${ParameterName.LOCALE_EN}">
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

<br/>

<c:if test="${not empty errorMessage}">
    <div class="text-center text-warning">
        <h3>
            <c:if test="${errorMessage eq Message.UPDATE_LOGIN_FAILED}">
                ${update_login_failed}
            </c:if>
            <c:if test="${errorMessage eq Message.UPDATE_PASS_FAILED}">
                ${update_pass_failed}
            </c:if>
            <c:if test="${errorMessage eq Message.UPDATE_INFO_FAILED}">
                ${update_info_failed}
            </c:if>
            <c:if test="${errorMessage eq Message.LICENSE_SAVE_FAILED}">
                ${save_new_license_failed}
            </c:if>
            <c:if test="${errorMessage eq Message.LICENSE_DELETE_FAILED}">
                ${delete_license_failed}
            </c:if>
        </h3>
    </div>
</c:if>

<c:if test="${not empty message}">
    <div class="text-center text-info">
        <h3>
            <c:if test="${message eq Message.UPDATE_LOGIN_SUCCESSFUL}">
                ${login_update_mess}
            </c:if>
            <c:if test="${message eq Message.UPDATE_PASS_SUCCESSFUL}">
                ${pass_update_mess}
            </c:if>
            <c:if test="${message eq Message.UPDATE_INFO_SUCCESSFUL}">
                ${info_update_mess}
            </c:if>
            <c:if test="${message eq Message.LICENSE_DELETE_SUCCESSFUL}">
                ${license_delete_successful_mess}
            </c:if>
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
                    <th colspan="4" class="text-center">
                        <c:if test="${locale eq ParameterName.LOCALE_EN}">
                            <c:if test="${userRole eq Role.ADMIN}">
                                ${table_title_admin}
                            </c:if>
                            <c:if test="${userRole eq Role.CLIENT}">
                                ${table_title_client}
                            </c:if>
                            <c:if test="${userRole eq Role.DRIVER}">
                                ${table_title_driver}
                            </c:if>
                             ${table_title_note}
                        </c:if>
                        <c:if test="${locale eq ParameterName.LOCALE_RU}">
                            ${table_title_note}
                            <c:if test="${userRole eq Role.ADMIN}">
                                ${table_title_admin}
                            </c:if>
                            <c:if test="${userRole eq Role.CLIENT}">
                                ${table_title_client}
                            </c:if>
                            <c:if test="${userRole eq Role.DRIVER}">
                                ${table_title_driver}
                            </c:if>
                        </c:if>
                    </th>
                </tr>
                <tr>
                    <c:if test="${not updateLogin}">
                        <th>${login_note}</th>
                        <td colspan="3">${user.login}</td>
                    </c:if>
                    <c:if test="${updateLogin}">
                        <th><label for="newLogin">${login_note}</label></th>
                        <td colspan="3">
                            <input type="text" id="newLogin" required name="${ParameterName.USER_LOGIN}" value="${user.login}">
                        </td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${not updateInfo}">
                        <th>${name_note}</th>
                        <td colspan="3">${user.name}</td>
                    </c:if>
                    <c:if test="${updateInfo}">
                        <th><label for="newName">${name_note}</label></th>
                        <td colspan="3">
                            <input type="text" id="newName" name="${ParameterName.USER_NAME}" value="${user.name}">
                        </td>
                    </c:if>
                </tr>
                <tr>
                    <c:if test="${not updateInfo}">
                        <th>${phone_note}</th>
                        <td colspan="3">${user.phone}</td>
                    </c:if>
                    <c:if test="${updateInfo}">
                        <th><label for="newPhone">${phone_note}</label></th>
                        <td colspan="3">
                            <input type="text" id="newPhone" name="${ParameterName.USER_PHONE}" value="${user.phone}">
                        </td>
                    </c:if>
                </tr>

                <c:if test="${userRole eq Role.ADMIN}">
                    <tr>
                        <c:if test="${not updateInfo}">
                            <th>${admin_work_since_note}</th>
                            <td colspan="3">${user.worksSince}</td>
                        </c:if>
                        <c:if test="${updateInfo}">
                            <th><label for="newWordSince">${admin_work_since_note}</label></th>
                            <td colspan="3"><p>
                                <input type="date" id="newWordSince" name="${ParameterName.ADMIN_WORKS_SINCE}" value="${user.worksSince}">
                            </p></td>
                        </c:if>
                    </tr>
                    <tr>
                        <c:if test="${not updateInfo}">
                            <th>${admin_note_note}</th>
                            <td colspan="3">${user.note}</td>
                        </c:if>
                        <c:if test="${updateInfo}">
                            <th><label for="newAdminNote">${admin_note_note}</label></th>
                            <td colspan="3">
                                <textarea cols="50" rows="5" id="newAdminNote" name="${ParameterName.ADMIN_NOTE}"
                                          placeholder="${admin_note_placeholder}" maxlength="500">${user.note}</textarea>
                            </td>
                        </c:if>

                    </tr>
                </c:if>
                <c:if test="${userRole eq Role.CLIENT}">
                    <tr>
                        <c:if test="${not updateInfo}">
                            <th>${client_company_note}</th>
                            <td colspan="3">${user.company}</td>
                        </c:if>
                        <c:if test="${updateInfo}">
                            <th><label for="newCompany">${client_company_note}</label></th>
                            <td colspan="3">
                                <input type="text" id="newCompany" name="${ParameterName.CLIENT_COMPANY}" value="${user.company}">

                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <c:if test="${not updateInfo}">
                            <th>${client_note_note}</th>
                            <td colspan="3">${user.note}</td>
                        </c:if>
                        <c:if test="${updateInfo}">
                            <th><label for="newClientNote">${client_note_note}</label></th>
                            <td colspan="3">
                                <textarea cols="50" rows="5" id="newClientNote" name="${ParameterName.CLIENT_NOTE}"
                                          placeholder="${client_note_placeholder}" maxlength="500">${user.note}</textarea>
                            </td>
                        </c:if>
                    </tr>
                </c:if>

                <c:if test="${updateLogin}">
                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.UPDATE_LOGIN_CONFIRM}">
                    <tr>
                        <td colspan="4" class="text-center">
                            <input type="submit" class="btn btn-success" value="${update_login_button}">
                        </td>
                    </tr>
                </c:if>

                <c:if test="${updatePass}">
                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.UPDATE_PASS_CONFIRM}">
                    <tr>
                        <th><label for="pass">${new_pass_note}</label></th>
                        <td>
                            <input type="password" id="pass" required name="${ParameterName.USER_PASS}"
                                   placeholder="${new_pass_placeholder}">
                        </td>
                    </tr>
                    <tr>
                        <th><label for="repeatPass">${new_pass_repeat_note}</label></th>
                        <td>
                            <input type="password" id="repeatPass" required name="${ParameterName.USER_PASS_REPEAT}"
                                   placeholder="${new_pass_repeat_placeholder}">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" class="text-center">
                            <input type="submit" class="btn btn-success" value="${update_pass_button}">
                        </td>
                    </tr>
                </c:if>

                <c:if test="${updateInfo}">
                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.UPDATE_INFO_CONFIRM}">
                    <tr>
                        <td colspan="4" class="text-center">
                            <input type="submit" class="btn btn-success" value="${update_info_button}">
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
                        <input type="submit" class="btn btn-outline-info" value="${update_login_button}">
                    </form>
                </div>
                <div class="col-4">
                    <form action="Controller" method="get">
                        <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.UPDATE_PASS}">
                        <input type="submit" class="btn btn-outline-info" value="${update_pass_button}">
                    </form>
                </div>
                <div class="col-4">
                    <form action="Controller" method="get">
                        <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.UPDATE_INFO}">
                        <input type="submit" class="btn btn-outline-info" value="${update_info_button}">
                    </form>
                </div>
            </div>
        </c:if>

        <br/><br/>

        <c:if test="${userRole eq Role.DRIVER}">
            <table class="table table-bordered table-striped text-center">
                <tr>
                    <th colspan="3">${licenses_table_title_note}</th>
                </tr>
                <tr>
                    <th>${licenses_category_note}</th>
                    <th>${obtaining_date}</th>
                    <th>${license_number}</th>
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
                                    <input type="submit" class="btn btn-warning" value="${license_delete_confirm_button} ${license.licenseType}">
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
                                <input type="date" required name="${ParameterName.LICENSE_OBTAINING_DATE}" placeholder="${obtaining_date}">
                            </td>
                            <td>
                                <input type="text" required name="${ParameterName.LICENSE_NUMBER}" placeholder="${license_number}">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <input type="submit" class="btn btn-success" value="${license_save_button}">
                            </td>
                        </tr>
                    </form>
                </c:if>
            </table>


            <c:if test="${not saveLicense and not deleteLicense}">
                <div class="container-fluid row text-center">
                    <div class="col-6">
                        <form action="Controller" method="get">
                            <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.SAVE_LICENSE}">
                            <input type="submit" class="btn btn-outline-info" value="${license_save_button}">
                        </form>
                    </div>
                    <div class="col-6">
                        <form action="Controller" method="get">
                            <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.DELETE_LICENSE}">
                            <input type="submit" class="btn btn-outline-info" value="${license_delete_button}">
                        </form>
                    </div>
                </div>
            </c:if>
        </c:if>
        
        <br/>

        <div class="text-center">
            <form action="Controller" method="get">
                <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_ALL_ORDERS_PAGE}">
                <c:if test="${userRole eq Role.ADMIN}">
                    <input type="hidden" name="${ParameterName.ORDER_LIST_TYPE}" value="${ParameterName.ORDER_LIST_ADMIN}">
                </c:if>
                <c:if test="${userRole eq Role.CLIENT}">
                    <input type="hidden" name="${ParameterName.ORDER_LIST_TYPE}" value="${ParameterName.ORDER_LIST_CLIENT}">
                </c:if>
                <c:if test="${userRole eq Role.DRIVER}">
                    <input type="hidden" name="${ParameterName.ORDER_LIST_TYPE}" value="${ParameterName.ORDER_LIST_CAR}">
                </c:if>
                <input type="submit" class="btn btn-info" value="${view_orders_button}">
            </form>

            <c:if test="${userRole eq Role.DRIVER}">
                <form action="Controller" method="get">
                    <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_CAR_INFO_PAGE}">
                    <input type="hidden" name="${ParameterName.CAR_REQUEST_TYPE}" value="${ParameterName.CAR_BY_DRIVER_ID}">
                    <input type="submit" class="btn btn-info" value="${view_car_button}">
                </form>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>
