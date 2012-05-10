<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="/app/header.jsp"%>
    </head>

    <body>

        <%@include file="/app/navigation.jsp"%>

        <h1>Login</h1><br />
        <c:url var="openIDLoginUrl" value="/j_spring_openid_security_check" />
        <c:url var="googleLogoUrl" value="/app/assets/google-logo.png" />
        <img src="${googleLogoUrl}"></img>
        <form action="${openIDLoginUrl}" method="post">
            <input name="openid_identifier" type="hidden" value="https://www.google.com/accounts/o8/id"/>
            <input type="submit" value="Sign in with Google"/>
        </form>

        <!-- Le javascript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/app/assets/javascript/jquery-1.7.2.js"></script>
        <script src="${pageContext.request.contextPath}/app/assets/bootstrap/js/bootstrap.js"></script>

    </body>
</html>
