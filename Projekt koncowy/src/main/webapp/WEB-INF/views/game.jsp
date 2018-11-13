<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Game</title>
    <script src="../../js/jQuery3.3.1.js"></script>
    <script src="../../js/app.js"></script>
    <style>
        #game, ul, h2{
            text-align: center;
        }
        button{
            width: 50px;
            height: 50px;
        }
    </style>
</head>
<body>

    <c:import url="header.jsp"/><br/>

    <h2>Twoim zadaniem jest wciśnięcie wszystkich przycisków</h2>
    <ul>
        <li>Zielony oznacza wciśnięty</li>
        <li>jeden przycisk może wpływać na kilka przycisków</li>
        <li>Powodzenia! :)</li>
    </ul>

    <div id="game">
        <div id="counter">Kliknięcia: 0</div>
        <div id="timer">Czas gry: 00:00</div>
        <c:forEach begin="1" var="i" end="${length}">
            <c:forEach begin="1" var="j" end="${length}">
                <button>${((i - 1) * length) + j}</button>
            </c:forEach>
            <br/>
        </c:forEach>
    </div>

    <table style="display: none;">
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

</body>
</html>
