<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="header.jsp"%>
    </head>

    <body>
        <%@include file="navigation.jsp"%>

        <div class="container">

            <!-- Main hero unit for a primary marketing message or call to action -->
            <div class="hero-unit">
                <h1>Music Library</h1>
                <p>See how different albums rank among out users. Add your albums to the database and rate them when hear them. Log in to start rating.</p>
            </div>


            <hr>

        </div><!--/.fluid-container-->

        <!-- Le javascript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/app/assets/javascript/jquery-1.7.2.js"></script>
        <script src="${pageContext.request.contextPath}/app/assets/bootstrap/js/bootstrap.js"></script>

    </body>
</html>
