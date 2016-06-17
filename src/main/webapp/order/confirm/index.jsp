<%@ page language="java"
         contentType="text/html;charset=UTF-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"   prefix="fmt" %>
<%@ taglib uri="http://lightjet.com/common"         prefix="cmn" %>

<html>
<!-- l10n -->
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="update" var="update"/>

<head>
    <%-- TODO: Localize title, headers and messages --%>
    <title>Order confirmation</title></title>
    <%@ include file="/WEB-INF/head-tag-data.html" %>
</head>

<body>
<div class="container">

    <%@ include file="/WEB-INF/navbar.jsp" %>

    <h3 class="text-center" style="margin-top: 5%">Your order</h3>

    <div class="col-xs-10 col-xs-offset-1" style="margin-top: 3%">
        <table class="table table-bordered table-striped text-center">
            <tr>
                <th>FlightId</th>
                <th>Departure city</th>
                <th>Arrival city</th>
                <th>Date</th>
                <th>Time</th>
                <th>Luggage</th>
                <th>Priority check-in</th>
                <th>Price</th>
            </tr>
            <tr>
                <c:set var="flight" value="${sessionScope.flight}" scope="page"/>
                <c:set var="luggage" value="${sessionScope.luggage}" scope="page"/>
                <c:set var="priority" value="${sessionScope.priority}" scope="page"/>
                <td>${flight.id}</td>
                <td>${flight.departureCity}</td>
                <td>${flight.arrivalCity}</td>
                <td>${flight.date}</td>
                <td>${flight.time}</td>
                <td>${cmn:yesOrNo(luggage)}</td>
                <td>${cmn:yesOrNo(priority)}</td>
                <td>${sessionScope.price}</td>
            </tr>
        </table>
    </div>

    <div class="col-xs-6 col-xs-offset-3" style="margin-top: 1%">
        <h4 class="text-center" style="margin-bottom: 3%">Passenger data</h4>
        <form action="/orderResult" method="post">
            <%--<input type="hidden" name="orderConfirmed" value="true"/>--%>
            <table class="table table-bordered table-striped" style="; text-align: center">
                <tr>
                    <th>
                        First name
                    </th>
                    <th>
                        Last name
                    </th>
                    <th>
                        Passport number
                    </th>
                </tr>
                <tr>
                    <td>
                        <input type="text" class="form-control" id="firstName" name="firstName"
                               value="${requestScope.firstName}"
                               placeholder="${firstName}" required>
                    </td>
                    <td>
                        <input type="text" class="form-control" id="lastName" name="lastName"
                               value="${requestScope.lastName}"
                               placeholder="${lastName}" required>
                    </td>
                    <td>
                        <input type="text" class="form-control" id="passport" name="passport"
                               value="${requestScope.passport}"
                               placeholder="${passport}" required>
                    </td>
                </tr>
            </table>
            <div class="col-xs-4 col-xs-offset-4">
                <input type="submit" value="Confirm" class="btn btn-default btn-block active">
            </div>

        </form>
    </div>

</div>
</body>
</html>

