<!-- l10n -->
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="logIn" var="logIn"/>
<fmt:message bundle="${locale}" key="logOut" var="logOut"/>
<fmt:message bundle="${locale}" key="signUp" var="signUp"/>
<fmt:message bundle="${locale}" key="myOrders" var="myOrders"/>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <!-- Brand and toggle get grouped for better mobile display -->
            <button type="button" data-target="#navbar-collapse" data-toggle="collapse" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">
                <img src="/images/airplane.png" alt="">
                <%--<span class="glyphicon glyphicon-plane" style="color:dimgray;"></span> Light Jet--%>
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="navbar-collapse collapse" id="navbar-collapse">
            <!-- l10n buttons -->
            <form action="/l10n" method="post" class="navbar-form navbar-left">
                <input type="hidden" name="locale" value="en"/>
                <button type="submit" class="btn btn-default btn-img"><img src="/images/eng.png" alt=""></button>
            </form>
            <form action="/l10n" method="post" class="navbar-form navbar-left" style="margin-left: -2%">
                <input type="hidden" name="locale" value="ru"/>
                <button type="submit" class="btn btn-default btn-img"><img src="/images/rus.png" alt=""></button>
            </form>

            <ul class="nav navbar-nav navbar-right">
                <!-- "My Orders" link for authorized users -->
                <c:if test="${requestScope.isLoggedIn}">
                    <li><a href="/my-orders/" class="navbar-link" style="margin-right: 40px">${myOrders}</a></li>
                </c:if>
                <!-- Log in, log out, registration group -->
                <c:choose>
                    <c:when test="${!requestScope.isLoggedIn}">

                        <a href="/login.jsp" class="btn btn-default navbar-btn active" style="margin-right: 9px;">${logIn}</a>
                        <a href="/sign-up.jsp"  class="btn btn-default navbar-btn">${signUp}</a>

                        <%--<div class="btn-toolbar" role="toolbar">--%>
                        <%--<div class="btn-group">--%>
                        <%--                            <form action="/sign-up.jsp" method="get" class="navbar-form navbar-right">
                                                        <button type="submit" class="btn btn-default">${signUp}</button>
                                                    </form>
                                                    <form action="/login.jsp" method="get" class="navbar-form navbar-right">
                                                        <button type="submit" class="btn btn-default">${logIn}</button>
                                                    </form>--%>
                        <%--</div>--%>

                    </c:when>
                    <c:otherwise>
                        <li class="navbar-text" style="margin-right: -5px">
                                ${requestScope.login}
                                <%--Your login--%>
                        </li>
                        <form action="/authorization" method="post" class="navbar-form navbar-right">
                            <input type="hidden" name="logout" value="true"/>
                            <button type="submit" class="btn btn-default">${logOut}</button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </ul> <!-- /.navbar-right -->
            <%--<ul class="nav navbar-nav navbar-right">--%>
            <%--<button type="submit" class="btn btn-default navbar-btn">Sign in</button>--%>
            <%--</ul>--%>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

