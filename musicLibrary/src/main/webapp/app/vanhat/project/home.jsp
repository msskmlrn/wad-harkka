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
        <h1>Studentdevs</h1>
                
        <h2>Projects</h2>
        <c:forEach var="project" items="${projects}">
            <a href="${pageContext.request.contextPath}/project/${project.id}">${project.name}</a><br />
            ${project.description} <br />
            Number of likes: ${fn:length(project.likers)}<sec:authorize access="hasRole('student')"><a href="${pageContext.request.contextPath}/project/like/${project.id}"> +</a></sec:authorize><br/>
            <sec:authorize access="hasRole('customer')"><c:if test="${project.owner.username == username}"><a href="${pageContext.request.contextPath}/project/edit/${project.id}">Edit project</a></c:if></sec:authorize><br/><br/>
        </c:forEach>    
        <sec:authorize access="hasRole('customer')"><a href="${pageContext.request.contextPath}/project/newProject">Create new project</a></sec:authorize><br/>
        <p><sec:authorize access="isAuthenticated()"><a href="<c:url value="/j_spring_security_logout" />">Logout</a></sec:authorize></p>
    </body>
</html>
