<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User page</title>
    </head>
    <body>
        <h1>${user.name}'s Library</h1>
        
        <header>
            <a href="${pageContext.request.contextPath}/album">Front page</a><br />
            <a href="${pageContext.request.contextPath}/user/">Your library</a><br />
            <a href="${pageContext.request.contextPath}/user/login">Login</a><br />
        </header>     
        
        <h2>Rated Albums</h2>
        <table border="1">
            <tr>
                <th>Artist</th>
                <th>Album</th>
                <th>Released</th>
                <th>User Rating</th>
            </tr>
            <c:forEach var="rating" items="${ratings}">
                <tr>
                    <td>${rating.album.albumArtist}</td>
                    <td>${rating.album.albumName}</td>
                    <td>${rating.album.releaseYear}</td>
                    <td>${rating.stars}</td>
                </tr>
            </c:forEach>
        </table>
        
        <h2>Albums to be rated</h2>
        <sec:authorize access="isAuthenticated()"><a href=${pageContext.request.contextPath}/user/${user.id}/rate>Rate albums</a></sec:authorize><br /><br />

        <sec:authorize access="isAuthenticated()"><a href="<c:url value="/j_spring_security_logout" />">Logout</a></sec:authorize>
    </body>
</html>
