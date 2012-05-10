<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="brand" href="${pageContext.request.contextPath}/app/album">Music Library</a>
            <sec:authorize access="isAnonymous()">
                <ul class="nav pull-right">
                    <li><a href="${pageContext.request.contextPath}/app/user/login">Login</a></li>
                </ul>   
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <div class="btn-group pull-right">
                    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="icon-user"></i> Welcome
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="divider"></li>
                        <li><a href="<c:url value="/j_spring_security_logout" />">Logout</a></li>
                    </ul>
                </div>
            </sec:authorize>
            <div class="nav-collapse">
                <ul class="nav">
                    <li class="active"><a href="#">Home</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>