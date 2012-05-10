<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Music</title>
    </head>
    <body>
        <h1>${album.albumName}</h1>
        
        <header>
            <a href="${pageContext.request.contextPath}/album">Front page</a><br />
            <a href="${pageContext.request.contextPath}/user/">Your library</a><br />
            <a href="${pageContext.request.contextPath}/user/login">Login</a><br />
        </header>     
        
        <h2>${album.albumName}</h2>
        Artist: <a href="${pageContext.request.contextPath}/artist/${album.albumArtist}">${album.albumArtist}</a><br />
        Release Year: ${album.releaseYear}<br />
        Genre: ${album.genre}<br />
        Description: ${album.description}<br />
        Average Rating: ${album.averageRating}<br />

        <sec:authorize access="hasRole('user')">
            <p>
                <form id="addAlbumToCollection" action="${pageContext.request.contextPath}/album" method="POST">
                    <input type="hidden" name="albumId" value="${album.id}" />
                    <input type="submit" value="Add" />
                </form>
            </p>    
        </sec:authorize>
        
        <h3>Album owners</h3>
        
        <p>
        <c:forEach var="owner" items="${owners}">
            <a href="${pageContext.request.contextPath}/user/${owner.id}">${owner.username}</a>
        </c:forEach>
        </p>
        
        <sec:authorize access="isAuthenticated()"><a href="${pageContext.request.contextPath}/album/new">Add new album</a></sec:authorize><br /><br />
        
        <sec:authorize access="isAuthenticated()"><a href="<c:url value="/j_spring_security_logout" />">Logout</a></sec:authorize>
    </body>
</html>
