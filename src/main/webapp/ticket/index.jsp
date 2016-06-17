<%@ page language="java"
         contentType="text/html;charset=UTF-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<html>
<!-- l10n -->
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="BuyTicket" var="BuyTicket" />

<head>
    <%-- TODO: Localize title, headers and messages --%>
    <title>Ticket options</title>
    <%@ include file="/WEB-INF/head-tag-data.html" %>
</head>

<body>
<div class="container">

    <%@ include file="/WEB-INF/navbar.jsp" %>

    <h3 class="text-center" style="margin-top: 5%">Ticket options</h3>

    <div class="col-xs-10 col-xs-offset-1" style="margin-top: 3%">
        <form action="/orderDetails" method="post">
            <input type="hidden" name="confirm" value="true"/>
            <table class="table" style="text-align: center">
                <tr>
                    <td>Luggage (1 bug)</td>
                    <td><b>1000 rub</b></td>
                    <td><input type="checkbox" name="luggage" value="true"/></td>
                    <td>Priority check-in and boarding</td>
                    <td><b>1000 rub</b></td>
                    <td><input type="checkbox" name="priority" value="true"/></td>
                    <td><input type="submit" value="${BuyTicket}" class="btn btn-default btn-block active"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>

