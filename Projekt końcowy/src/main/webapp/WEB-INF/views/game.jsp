<%@ page import="spring.beans.FindSession" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Game</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css"/>
    <script src="../../js/jQuery3.3.1.js"></script>
    <script src="../../js/app.js"></script>
</head>
<body>
    <% FindSession.orRedirect(request, response); %>

    <c:import url="header.jsp"/><br/>
    <c:forEach begin="1" var="i" end="${level}">
        <button>${i}</button>
    </c:forEach>
    <table>
        <c:forEach begin="1" end="${level}">
            <tr>
                <c:forEach begin="1" end="${level}">
                    <td></td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
