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
                    <h2>Albums</h2>
                    <table class="table" id="sortTable">
                        <thead>
                            <tr>
                                <th>Artist</th>
                                <th>Album</th>
                                <th>Released</th>
                                <th>User Rating</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="rating" items="${ratings}">
                                <tr>
                                    <td><a href="${pageContext.request.contextPath}/app/artist/${rating.album.albumArtist}">${rating.album.albumArtist}</a></td>
                                    <td><a href="${pageContext.request.contextPath}/app/album/${rating.album.id}">${rating.album.albumName}</a></td>
                                    <td>${rating.album.releaseYear}</td>
                                    <td>${rating.stars}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <sec:authorize access="isAuthenticated()"><a href=${pageContext.request.contextPath}/app/user/${user.id}/rate>Rate albums</a></sec:authorize><br /><br />

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
