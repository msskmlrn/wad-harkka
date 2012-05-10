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
        <h1>Project</h1>
        Name: ${project.name} <br />
        Description: ${project.description} <br />
        Number of likes: ${fn:length(project.likers)} <br />
        <sec:authorize access="hasRole('customer')"><c:if test="${project.owner.username == username}"><a href="${pageContext.request.contextPath}/project/edit/${project.id}">Edit project</a></c:if></sec:authorize><br/><br/>   
    </body>
</html>