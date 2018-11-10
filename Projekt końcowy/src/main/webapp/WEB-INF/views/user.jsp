<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${user.name}</title>
</head>
<body>
    <c:import url="header.jsp"/>
        Witaj ${user.name}!<br/>
        <div>
            <h3>Statysyki</h3>
            <ol>
                <li>Wygrane: ${count}</li>
            </ol>
            <table style="margin-left: auto; margin-right: auto">
                <tr>
                    <td></td>
                    <td>poziom</td>
                    <td>ruchy</td>
                    <td>czas</td>
                </tr>
                <c:forEach items="${gamesByMoves}" var="game">
                    <tr>
                        <td><%%></td>
                        <td>${game.level}</td>
                        <td>${game.moves}</td>
                        <td>${game.time}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div id="redZone">
            form
        </div>
    <c:import url="footer.jsp"/>
</body>
</html>
