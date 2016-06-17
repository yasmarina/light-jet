<%@ page language="java"
         contentType="text/html;charset=UTF-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<html>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="signUpTitle" var="signUpTitle"/>
<fmt:message bundle="${locale}" key="signUpHeader" var="signUpHeader"/>
<fmt:message bundle="${locale}" key="firstName" var="firstName"/>
<fmt:message bundle="${locale}" key="lastName" var="lastName"/>
<fmt:message bundle="${locale}" key="dateOfBirth" var="dateOfBirth"/>
<fmt:message bundle="${locale}" key="day" var="day"/>
<fmt:message bundle="${locale}" key="month" var="month"/>
<fmt:message bundle="${locale}" key="year" var="year"/>
<fmt:message bundle="${locale}" key="email" var="email"/>
<fmt:message bundle="${locale}" key="password" var="password"/>
<fmt:message bundle="${locale}" key="confirmPassword" var="confirmPassword"/>
<fmt:message bundle="${locale}" key="phone" var="phone"/>
<fmt:message bundle="${locale}" key="address" var="address"/>
<fmt:message bundle="${locale}" key="signUp" var="signUp"/>
<fmt:message bundle="${locale}" key="reset" var="reset"/>

<head>
    <title>${SignUpTitle}</title>
    <%@ include file="/WEB-INF/head-tag-data.html" %>
</head>

<body>
<div class="container">

    <h3 style="margin-left: 29%; margin-bottom: 3%">${signUpHeader}</h3>

    <form action="/authorization" method="post" class="form-horizontal">
        <input type="hidden" name="signUp" value="true">
        <div class="form-group">
            <label class="control-label col-xs-3" for="firstName">${firstName} :</label>
            <div class="col-xs-3">
                <input type="text" class="form-control" id="firstName" name="firstName" value="${requestScope.firstName}"
                       placeholder="${firstName}" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-3" for="lastName">${lastName} :</label>
            <div class="col-xs-3">
                <input type="text" class="form-control" id="lastName" name="lastName" value="${requestScope.lastName}"
                       placeholder="${lastName}" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-3">${dateOfBirth} :</label>
            <div class="col-xs-1">
                <input type="text" class="form-control" name="day" value="${requestScope.day}"
                       pattern="(0?[1-9]|[12][0-9]|3[01])" placeholder="${day}" title="">
            </div>
            <div class="col-xs-1">
                <input type="text" class="form-control" name="month" value="${requestScope.month}"
                       pattern="(0?[1-9]|[1][012])" placeholder="${month}">
            </div>
            <div class="col-xs-1">
                <input type="text" class="form-control" name="year" value="${requestScope.year}"
                       pattern="((19|20)\d\d)" placeholder="${year}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-3" for="email">${email} :</label>
            <div class="col-xs-3">
                <input type="email" class="form-control" id="email" name="email" value="${requestScope.email}"
                       placeholder="${email}" required>
            </div>
            <div class="col-xs-3">
                <c:if test="${requestScope.emailIsUsed}">
                    <%--TODO: Localize--%>
                    <h5 style="text-align:left;">This email is used!</h5><%--${LoginIsUsed}--%>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-3" for="password">${password} :</label>
            <div class="col-xs-3">
                <input type="password" class="form-control" id="password" name="password"
                       placeholder="${password}" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-3" for="confirmPassword">${confirmPassword} :</label>
            <div class="col-xs-3">
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                       placeholder="${password}" required>
            </div>
            <div class="col-xs-3">
                <c:if test="${requestScope.passwordNotConfirmed}">
                    <%--TODO: Localize--%>
                    <h5 style="text-align:left;">Passwords do not match!</h5>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-3" for="phoneNumber">${phone} :</label>
            <div class="col-xs-3">
                <input type="tel" class="form-control" id="phoneNumber"
                       pattern="([+]{1}7)( ([\d]{3})){2}( ([\d]){2}){2}" name="phone"
                       placeholder="+7 xxx xxx xx xx">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-3" for="address">${address} :</label>
            <div class="col-xs-3">
                <textarea rows="3" class="form-control" id="address" name="address"
                          placeholder="${address}"></textarea>
            </div>
        </div>
        <br/>
        <div class="form-group">
            <div class="col-xs-offset-3 col-xs-3">
                <input type="submit" class="btn btn-default active pull-left" value="${signUp}">
                <input type="reset" class="btn btn-default pull-right" value="${reset}">
            </div>
        </div>
    </form>
</div>
</body>
</html>