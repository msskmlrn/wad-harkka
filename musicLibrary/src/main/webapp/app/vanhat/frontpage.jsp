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
        <h1>Music Library</h1>
        
        <header>
            <a href="${pageContext.request.contextPath}/album">Front page</a><br />
            <a href="${pageContext.request.contextPath}/user/">Your library</a><br />
            <a href="${pageContext.request.contextPath}/user/login">Login</a><br />
        </header>     
        
        <h2>Albums</h2>
        <table border="1">
            <tr>
                <th>Artist</th>
                <th>Album</th>
                <th>Released</th>
                <th>Overall Rating</th>
            </tr>
            <c:forEach var="album" items="${albums}">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/artist/${album.albumArtist}">${album.albumArtist}</a></td>
                    <td><a href="${pageContext.request.contextPath}/album/${album.id}">${album.albumName}</a></td>
                    <td>${album.releaseYear}</td>
                    <td>${album.averageRating}</td>    
                    <sec:authorize access="hasRole('user')"><td><a href="${pageContext.request.contextPath}/album/${album.id}/rate">Rate album+-</a></td></sec:authorize>
                    <sec:authorize access="hasRole('user')">
                        <td>
                            <form id="addAlbumToCollection" method="POST">
                                <input type="hidden" name="albumId" value="${album.id}" />
                                <input type="submit" value="Add" />
                            </form>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
        
        <h2>Users</h2>
        <c:forEach var="user" items="${users}">
            <a href="${pageContext.request.contextPath}/user/${user.id}">${user.name}</a><br />
        </c:forEach><br />
        
        <sec:authorize access="isAuthenticated()"><a href="${pageContext.request.contextPath}/album/new">Add new album</a></sec:authorize><br /><br />
        
        <sec:authorize access="isAuthenticated()"><a href="<c:url value="/j_spring_security_logout" />">Logout</a></sec:authorize>
    </body>
</html>
