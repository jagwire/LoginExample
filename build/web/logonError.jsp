<%-- 
    Document   : logonError
    Created on : Jan 15, 2014, 12:20:23 PM
    Author     : Ryan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <c:url var="url" value="/index.jsp"/>
    <h2>Invalid user name or password!</h2>
    Click here to <a href="${url}">Try Again</a>
</body>
</html>
