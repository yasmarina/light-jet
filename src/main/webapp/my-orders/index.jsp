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
    <title>My Orders</title>
    <%@ include file="/WEB-INF/head-tag-data.html" %>
</head>

<body>
<div class="container">

    <%@ include file="/WEB-INF/navbar.jsp" %>

    <h3 class="text-center" style="margin-top: 5%">Your tickets</h3>

    <div class="col-xs-12 <%--col-xs-offset-1--%>" style="margin-top: 3%">
        <table class="table table-bordered table-striped text-center">
            <tr>
                <th>FlightId</th>
                <th>Departure city</th>
                <th>Arrival city</th>
                <th>Date</th>
                <th>Time</th>
                <th>Luggage</th>
                <th>Priority check-in</th>
                <th>Passenger</th>
                <th>Passport</th>
                <th>Price</th>
            </tr>
            <c:forEach var="ticket" items="${requestScope.tickets}">
                <tr>
                    <td>${ticket.flight.id}</td>
                    <td>${ticket.flight.departureCity}</td>
                    <td>${ticket.flight.arrivalCity}</td>
                    <td>${ticket.flight.date}</td>
                    <td>${ticket.flight.time}</td>
                    <td>${cmn:yesOrNo(ticket.luggage)}</td>
                    <td>${cmn:yesOrNo(ticket.priority)}</td>
                    <td>${ticket.firstName} ${ticket.lastName}</td>
                    <td>${ticket.passport}</td>
                    <td>${ticket.price}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>

