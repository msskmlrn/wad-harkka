<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="/app/header.jsp"%>
    </head>

    <body>

        <%@include file="/app/navigation.jsp"%>

        <h1>Add new album to the database</h1>
        <div class="clearfix">
            <form:form class="form-horizontal" commandName="album" action="${pageContext.request.contextPath}/app/album/new" method="POST">

                <div class="control-group">  
                    <label class="control-label" for="input01">Album artist</label>  
                    <div class="controls">  
                        <form:input path="albumArtist" /><form:errors path="albumArtist" />
                    </div>  
                </div>  
                <div class="control-group">  
                    <label class="control-label" for="input01">Album name</label>  
                    <div class="controls">  
                        <form:input path="albumName" /><form:errors path="albumName" />
                    </div>  
                </div>
                <div class="control-group">  
                    <label class="control-label" for="input01">Genre</label>  
                    <div class="controls">  
                        <form:input path="genre" /><form:errors path="genre" />
                    </div>  
                </div>
                <div class="control-group">  
                    <label class="control-label" for="input01">Description</label>  
                    <div class="controls">  
                        <form:textarea path="description" /><form:errors path="description" />
                    </div>  
                </div>
                <div class="control-group">  
                    <label class="control-label" for="input01">Release year</label>  
                    <div class="controls">  
                        <form:input path="releaseYear" /><form:errors path="releaseYear" />
                    </div>  
                </div>
                <div class="form-actions">  
                    <button type="submit" class="btn btn-primary">Add album</button>  
                </div>  

            </form:form>  
        </div>
        <!-- Le javascript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/app/assets/javascript/jquery-1.7.2.js"></script>
        <script src="${pageContext.request.contextPath}/app/assets/bootstrap/js/bootstrap.js"></script>

    </body>
</html>
