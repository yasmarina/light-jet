<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="loginTitle" var="loginTitle"/>
<fmt:message bundle="${locale}" key="loginHeader" var="loginHeader"/>
<fmt:message bundle="${locale}" key="firstName" var="firstName"/>
<fmt:message bundle="${locale}" key="lastName" var="lastName"/>
<fmt:message bundle="${locale}" key="signUp" var="signUp"/>
<fmt:message bundle="${locale}" key="logIn" var="logIn"/>
<head>
    <title>${loginTitle}</title>
    <%@ include file="/WEB-INF/head-tag-data.html" %>
</head>
<body>
<div class="container">
    <h3 style="margin-left: 27%; margin-bottom: 3%">${loginHeader}</h3>
    <div class="col-xs-3 col-xs-offset-3">
        <form action="/authorization" method="post" class="form-horizontal">
            <input type="hidden" name="login" value="true"/>
            <div class="form-group">
                <input type="text" class="form-control" name="j_username" placeholder="Email" required>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" name="j_password" placeholder="Password" required>
            </div>
            <div class="form-group">
                <a href="/sign-up.jsp" class="pull-left" style="margin-top: 3%">${signUp}</a>
                <!--<button class="btn btn-lg btn-default" type="submit">Log in</button>-->
                <input type="submit" class="btn btn-default active pull-right" value="${logIn}">
                <!--<input type="reset" class="btn btn-default" value="Reset">-->
            </div>
            <!--<label class="checkbox pull-left">-->
                <!--<input type="checkbox" value="remember-me">-->
                <!--Remember me-->
            <!--</label>-->
        </form>
    </div>
</div>
</body>
</html>
