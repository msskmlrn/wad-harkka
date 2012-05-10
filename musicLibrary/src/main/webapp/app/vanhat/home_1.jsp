<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Music Library</h1>
                
        <h2>Albums</h2>
        <c:forEach var="album" items="${albums}">
            <a href="${pageContext.request.contextPath}/album/${album.id}">${album.albumName}</a><br />
            ${album.albumArtist} <br />
            ${album.releaseYear} <br />
            <c:forEach var="albumRating" items="${album.albumRatings}">
                Stars: ${albumRating.stars} <br />
            </c:forEach>
            <sec:authorize access="hasRole('user')"><a href="${pageContext.request.contextPath}/album/${album.id}/rating"> rate</a></sec:authorize><br/>            
        </c:forEach><br />
        <sec:authorize access="hasRole('user')"><a href="${pageContext.request.contextPath}/album/form">Create new album</a></sec:authorize><br/>
            
        <c:forEach var="user" items="${users}">
            <a href="${pageContext.request.contextPath}/user/${user.id}">${user.username}</a><br />
        </c:forEach><br />
        
        <c:forEach var="rating" items="${ratings}">
            Rating: ${rating.stars} <br />
            ${rating.album.albumName} <br />
        </c:forEach><br />
        
        <c:forEach var="mew" items="${mews}">
            ${mew.albumArtist} <br />
            ${mew.albumName} <br />
            ${mew.releaseYear} <br />
        </c:forEach><br />
        
        TEST: ${frengers.albumName} <br />
        ${frengers.albumArtist} <br />
        
        ALBUMRATINGS
        <c:forEach var="albumRating" items="${albumRatings}">
            Rating: ${albumRating.stars} <br />
        </c:forEach><br />
        
        <sec:authorize access="isAuthenticated()"><a href="<c:url value="/j_spring_security_logout" />">Logout</a></sec:authorize>
    </body>
</html>
