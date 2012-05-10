<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Project</h1>
        
        <h2>Modify project information</h2>
        <form:form commandName="project" action="${pageContext.request.contextPath}/project/edit" method="POST">
            Name: <form:input path="name" /><form:errors path="name" /><br />
            Description: <form:input path="description" /><form:errors path="description" /><br />
            <input type="hidden" name="id" value="${project.id}">
            <input type="submit" value="Update project info" />
        </form:form>  
        <p><a href="<c:url value="/j_spring_security_logout" />" > Logout</a></p>
    </body>
</html>