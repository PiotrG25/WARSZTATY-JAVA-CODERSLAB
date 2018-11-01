<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="../../css/style.css" type="text/css"/>
    <link rel="stylesheet" href="../../css/fontello-f5aae3d8/css/fontello.css" type="text/css"/>
</head>
<body>
    <header>
        <h3>Go to level:</h3>
        <c:forEach var="i" begin="1" end="5">
            <a href="/main?level=${i}">${i}</a>
        </c:forEach>

        <a id="logoutLink" href="/logout">Logout!</a>
        <a id="user" href="/user"><i class="icon-user-5"></i></a>
    </header>
</body>
</html>
