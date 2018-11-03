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

    <c:forEach begin="1" var="i" end="${length}">
        <c:forEach begin="1" var="j" end="${length}">
            <button>${((i - 1) * length) + j}</button>
        </c:forEach>
        <br/>
    </c:forEach>

    <table>
        <c:forEach var="i" items="${tab}">
            <tr>
                <c:forEach var="j" items="${i}">
                    <td>
                        ${j}
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>

    <c:import url="footer.jsp"/><br/>
</body>
</html>
