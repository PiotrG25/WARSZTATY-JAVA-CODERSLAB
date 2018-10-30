<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        #logoutLink{
            float: right;
            font-size: 20px;
        }
    </style>
</head>
<body>
    <h2>Go to level:</h2>
    <c:forEach var="i" begin="1" end="5">
        <a href="/main?level=${i}">${i}</a>
    </c:forEach>

    <a id="logoutLink" href="/logout">Logout!</a>
</body>
</html>
