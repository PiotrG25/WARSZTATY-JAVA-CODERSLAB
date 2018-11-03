<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Game</title>
    <script src="../../js/jQuery3.3.1.js"></script>
    <script src="../../js/app.js"></script>
</head>
<body>

    <c:import url="header.jsp"/><br/>

    <c:forEach begin="1" var="i" end="${level}">
        <c:forEach begin="1" var="j" end="${level}">
            <button>${((i - 1) * level) + j}</button>
        </c:forEach>
        <br/>
    </c:forEach>

    <c:import url="footer.jsp"/><br/>
</body>
</html>
