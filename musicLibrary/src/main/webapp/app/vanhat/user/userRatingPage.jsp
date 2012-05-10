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
        <h1>${user.username}'s Library</h1>
        
        <header>
            <a href="${pageContext.request.contextPath}/album">Front page</a><br />
            <a href="${pageContext.request.contextPath}/user/">Your library</a><br />
            <a href="${pageContext.request.contextPath}/user/login">Login</a><br />
        </header>     
        
        <h2>Albums to be rated</h2>
        <table border="1">
            <tr>
                <th>Artist</th>
                <th>Album</th>
                <th>Released</th>
            </tr>
            <c:forEach var="rate" items="${toBeRated}">
                <tr>
                    <td>${rate.albumArtist}</td>
                    <td>${rate.albumName}</td>
                    <td>${rate.releaseYear}</td>
                    <sec:authorize access="hasRole('user')"><c:if test="${user.username == username}"><td><a href="${pageContext.request.contextPath}/user/${user.id}/album/${rate.id}/rate/1">1</a> <a href="${pageContext.request.contextPath}/user/${user.id}/album/${rate.id}/rate/2">2</a></td></c:if></sec:authorize>
                </tr>
            </c:forEach>
        </table>
   
        <sec:authorize access="isAuthenticated()"><a href="<c:url value="/j_spring_security_logout" />">Logout</a></sec:authorize>
    </body>
</html>
