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

        <div class="container">

            <h1>${user.name}'s Library</h1>

            <div class="row">
                <div class="span12">
                    <h2>Albums to be rated</h2>
                    <table class="table" id="sortTable">
                        <thead>
                            <tr>
                                <th>Artist</th>
                                <th>Album</th>
                                <th>Released</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="rate" items="${toBeRated}">
                                <tr>
                                    <td><a href="${pageContext.request.contextPath}/app/artist/${rate.albumArtist}">${rate.albumArtist}</a></td>
                                    <td><a href="${pageContext.request.contextPath}/app/album/${rate.id}">${rate.albumName}</a></td>
                                    <td>${rate.releaseYear}</td>
                                    <sec:authorize access="isAuthenticated()"><c:if test="${user.openIdIdentifier == openId}">
                                            <td><a href="${pageContext.request.contextPath}/app/user/${user.id}/album/${rate.id}/rate/1">1</a> 
                                                <a href="${pageContext.request.contextPath}/app/user/${user.id}/album/${rate.id}/rate/2">2</a>
                                                <a href="${pageContext.request.contextPath}/app/user/${user.id}/album/${rate.id}/rate/3">3</a>
                                                <a href="${pageContext.request.contextPath}/app/user/${user.id}/album/${rate.id}/rate/4">4</a>
                                                <a href="${pageContext.request.contextPath}/app/user/${user.id}/album/${rate.id}/rate/5">5</a>
                                            </td></c:if></sec:authorize>
                                        </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div><!--/.fluid-container-->

        <!-- Le javascript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/app/assets/javascript/jquery-1.7.2.js"></script>
        <script src="${pageContext.request.contextPath}/app/assets/bootstrap/js/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/app/assets/javascript/jquery.tablesorter.js"></script>

        <script>
            $(function() {
                $("table#sortTable").tablesorter({ sortList: [[1,0]] });
            });
        </script>
    </body>
</html>
