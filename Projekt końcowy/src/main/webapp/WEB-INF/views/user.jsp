<%@ page import="spring.beans.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${user.name}</title>
</head>
<body>
    <c:import url="header.jsp"/>
    Witaj ${user.name}!<br/>

    <h3>Statysyki</h3>
    <ol>
        <li>Wygrane: ${winCount}</li>
        <li>Łącznie ruchów: ${movesCount}</li>
        <li>Łączny czas gry: ${timeCount}</li>
    </ol>

    <h3 id="table">tablica 2x2</h3>

    <div class="tabDiv">
        <h3>Najlepsze wg ruchów</h3>
        <table>
            <tr>
                <td></td>
                <td>ruchy</td>
                <td>czas</td>
            </tr>
            <c:forEach items="${gamesByMoves}" var="game" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${game.moves}</td>
                    <td>${game.time}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="tabDiv">
        <h3>Najlepsze wg czasu</h3>
        <table>
            <tr>
                <td></td>
                <td>ruchy</td>
                <td>czas</td>
            </tr>
            <c:forEach items="${gamesByTime}" var="game" varStatus="i">
            <tr>
                    <td>${i.count}</td>
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
