<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New album</title>
    </head>
    <body>
        <h1>Add new album to the database</h1>
        
        <form:form commandName="album" action="${pageContext.request.contextPath}/album/new" method="POST">
            Album artist: <form:input path="albumArtist" /><form:errors path="albumArtist" /><br />
            Album name: <form:input path="albumName" /><form:errors path="albumName" /><br />
            Genre: <form:input path="genre" /><form:errors path="genre" /><br />
            Description: <form:textarea path="description" /><form:errors path="description" /><br />
            Release year <form:input path="releaseYear" /><form:errors path="releaseYear" /><br />
            <input type="submit" value="Add album" />
        </form:form>  
    </body>
</html>