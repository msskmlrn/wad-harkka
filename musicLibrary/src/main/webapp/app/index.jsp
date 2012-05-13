<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
                <sec:authorize access="isAuthenticated()">
                    <p>Have fun with rating.</p>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <p>See how different albums rank among out users. Add your albums to the database and rate them when hear them. Log in to start rating.</p>    
                </sec:authorize>
            </div>

            <div class="row">
                <div class="span12">
                    <h2>Albums</h2>
                    <table class="table" id="sortTable">
                        <thead>
                            <tr>
                                <th>Artist</th>
                                <th>Album</th>
                                <th>Released</th>
                                <th>Overall Rating</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="album" items="${albums}">
                                <tr>
                                    <td><a href="${pageContext.request.contextPath}/app/artist/${album.albumArtist}">${album.albumArtist}</a></td>
                                    <td><a href="${pageContext.request.contextPath}/app/album/${album.id}">${album.albumName}</a></td>
                                    <td>${album.releaseYear}</td>
                                    <td>${album.averageRating}</td>
                                    <sec:authorize access="isAuthenticated()">
                                        <td>
                                            <form id="addAlbumToCollection" method="POST">
                                                <input type="hidden" name="albumId" value="${album.id}" />
                                                <input type="submit" value="Add" />
                                            </form>
                                        </td>
                                    </sec:authorize>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="row">
                <div class="span12">
                    <h2>Users</h2>
                    <c:forEach var="user" items="${users}">
                        <a href="${pageContext.request.contextPath}/app/user/${user.id}">${user.name}</a><br />
                    </c:forEach><br />

                    <sec:authorize access="isAuthenticated()"><a href="${pageContext.request.contextPath}/app/album/new">Add new album</a></sec:authorize><br /><br />

                </div>
            </div>

            <hr>

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
