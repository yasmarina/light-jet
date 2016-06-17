<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>

<head>
    <title>${loginTitle}</title>
    <%@ include file="/WEB-INF/head-tag-data.html" %>
</head>
<body>
<div class="container">
    <h3 style="margin-left: 27%; margin-bottom: 3%">
        Invalid username and/or password,
        please <a href="/login.jsp">try again</a>.
    </h3>
</div>
</body>
</html>
