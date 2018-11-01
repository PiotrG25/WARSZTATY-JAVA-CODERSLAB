<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(session.getAttribute("user") == null){
        response.sendRedirect("/main");
    }
%>
<html>
<head>
    <title>${user.name}</title>
</head>
<body>
    <c:import url="header.jsp"/>
</body>
</html>
