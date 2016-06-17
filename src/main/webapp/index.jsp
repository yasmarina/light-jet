<%@ page language="java"
         contentType="text/html;charset=UTF-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<html>
<!-- l10n -->
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="mainHeader" var="mainHeader"/>
<fmt:message bundle="${locale}" key="update" var="update"/>

<head>
    <title>LightJet</title>
    <%@ include file="/WEB-INF/head-tag-data.html" %>
</head>

<body>
<div class="container">

    <%@ include file="/WEB-INF/navbar.jsp" %>

    <h3 class="text-center" style="margin-top: 5%">${mainHeader}</h3>

    <div style="margin: 3%">
        <form action="/flightList" method="post" class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-xs-2 input-lg">Departure city </label>
                <div class="col-xs-3">
                    <select name="departureCity" class="form-control">
                        <c:forEach var="city" items="${requestScope.cities}">
                            <option value="${city}">${city}</option>
                        </c:forEach>
                    </select>
                </div>
                <label class="control-label col-xs-2 input-lg" style="width: 13%">Arrival city</label>
                <div class="col-xs-3">
                    <select name="arrivalCity" class="form-control">
                        <c:forEach var="city" items="${requestScope.cities}">
                            <option value="${city}">${city}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-xs-2">
                    <input type="submit" class="btn btn-default active btn-block" value="${update}">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>

<%--
<form class="form-inline">
    <div class="form-group">
        <label &lt;%&ndash;class="sr-only"&ndash;%&gt; for="inputEmail">Email</label>
        <input type="email" class="form-control" id="inputEmail" placeholder="Email">
    </div>
    <div class="form-group">
        <label &lt;%&ndash;class="sr-only"&ndash;%&gt; for="inputPassword">Пароль</label>
        <input type="password" class="form-control" id="inputPassword" placeholder="Пароль">
    </div>
    <div class="checkbox">
        <label><input type="checkbox"> Запомнить</label>
    </div>
    <button type="submit" class="btn btn-primary">Войти</button>
</form>--%>
