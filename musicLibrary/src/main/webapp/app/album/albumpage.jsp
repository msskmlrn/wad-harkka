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

            <div class="row">
                <div class="span12">
                    <h2>${album.albumName}</h2>
                    <p>Artist: <a href="${pageContext.request.contextPath}/app/artist/${album.albumArtist}">${album.albumArtist}</a></p>
                    <p>Release Year: ${album.releaseYear}</p>
                    <p>Genre: ${album.genre}</p>
                    <p>Description: ${album.description}</p>
                    <p>Overall Rating: ${album.averageRating}</p>

                    <sec:authorize access="isAuthenticated()">
                        <p>
                        <form id="addAlbumToCollection" action="${pageContext.request.contextPath}/app/album" method="POST">
                            <input type="hidden" name="albumId" value="${album.id}" />
                            <input type="submit" value="Add" />
                        </form>
                        </p>    
                    </sec:authorize>
                </div>
            </div>

            <div class="row">
                <div class="span12">
                    <h3>Album owners</h3>
                    <p>
                        <c:forEach var="owner" items="${owners}">
                            <a href="${pageContext.request.contextPath}/app/user/${owner.id}">${owner.name}</a>
                        </c:forEach>
                    </p>
                    <sec:authorize access="isAuthenticated()"><a href="${pageContext.request.contextPath}/app/album/new">Add new album</a></sec:authorize>
                    </div>
                </div>

            </div>

        </div><!--/.fluid-container-->

        <!-- Le javascript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/app/assets/javascript/jquery-1.7.2.js"></script>
        <script src="${pageContext.request.contextPath}/app/assets/bootstrap/js/bootstrap.js"></script>

</body>
</html>
