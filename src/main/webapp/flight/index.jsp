<%@ page language="java"
         contentType="text/html;charset=UTF-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<html>
<!-- l10n -->
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="flightsHeader" var="flightsHeader"/>
<fmt:message bundle="${locale}" key="BuyTicket" var="BuyTicket" />

<head>
    <%-- TODO: Localize title, headers and messages --%>
    <title>Flights</title>
    <%@ include file="/WEB-INF/head-tag-data.html" %>
</head>

<body>
<div class="container">

    <%@ include file="/WEB-INF/navbar.jsp" %>

    <h3 class="text-center" style="margin-top: 5%">${flightsHeader}</h3>

    <div class="col-xs-8 col-xs-offset-2" style="margin-top: 3%">
        <table class="table" style="text-align: left">
            <c:forEach var="flight" items="${requestScope.flights}">
                <tr>
                    <td>${flight.departureCity} - ${flight.arrivalCity}</td>
                    <%--TODO: display date TagLib? --%>
<%--                    <td><fmt:formatDate type="date"
                                        dateStyle="long"
                                        value="${flight.date}"/></td>--%>
                    <td>${flight.date}</td>
                    <td>${flight.time}</td>
                    <td>${flight.price}</td>
                    <td>
                        <form action="/ticketOptions" method="post">
                            <input type="hidden" name="flightId" value="${flight.id}"/>
                            <input type="submit" value="${BuyTicket}" class="btn btn-default btn-block active">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
