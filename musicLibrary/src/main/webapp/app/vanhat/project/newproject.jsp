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
        <h1>Projects</h1>
        
        <h2>Create a new project</h2>
        <form:form commandName="project" action="${pageContext.request.contextPath}/project/newProject" method="POST">
            Project name: <form:input path="name" /><form:errors path="name" /><br />
            Project description: <form:input path="description" /><form:errors path="description" /><br />
            <input type="submit" value="Create new project" />
        </form:form>

    </body>
</html>