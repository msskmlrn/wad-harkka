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
        
            <h1>${artist}</h1><br />
        
            <div class="row">
                <div class="span12">
                    <h2>Albums</h2>
                    <table class="table" id="sortTable">
                        <thead>
                            <tr>
                                <th>Album</th>
                                <th>Released</th>
                                <th>Overall Rating</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="album" items="${albums}">
                                <tr>
                                    <td><a href="${pageContext.request.contextPath}/app/album/${album.id}">${album.albumName}</a></td>
                                    <td>${album.releaseYear}</td>
                                    <td>${album.averageRating}</td>    
                                    <sec:authorize access="isAuthenticated()">
                                        <td>
                                            <form id="addAlbumToCollection" action="${pageContext.request.contextPath}/app/album" method="POST">
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
