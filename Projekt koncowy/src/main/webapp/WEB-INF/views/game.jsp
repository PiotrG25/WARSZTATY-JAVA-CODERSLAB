<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Game</title>
    <script src="../../js/jQuery3.3.1.js"></script>
    <script src="../../js/app.js"></script>
    <style>
        #game, #enemyGame, ul, h2, body{
            text-align: center;
        }
        #game, #enemyGame{
            display: inline-block;
        }
        button, .pseudoButton{
            width: 50px;
            height: 50px;
            display: inline-block;
            border: 1px solid black;
        }
    </style>
</head>
<body>

    <c:import url="header.jsp"/>

    <h2>Twoim zadaniem jest wciśnięcie wszystkich przycisków</h2>
    <ul>
        <li>Zielony oznacza wciśnięty</li>
        <li>jeden przycisk może wpływać na kilka przycisków</li>
        <li>Po prawej masz małpę która sprubuje cię prześcignąć</li>
        <li>Powodzenia! :)</li>

        <div id="counter">Kliknięcia: 0</div>
        <div id="timer">Czas gry: 00:00</div>
    </ul>

    <div id="game">
        Twoja gra:
        <div class="gameField">
            <c:forEach begin="1" var="i" end="${length}">
                <c:forEach begin="1" var="j" end="${length}">
                    <button class="buttonInGame">${((i - 1) * length) + j}</button>
                </c:forEach>
                <br/>
            </c:forEach>
        </div>
    </div>

    <div id="enemyGame">
        Gra przeciwnika:
        <div class="gameField">
            <c:forEach begin="1" var="i" end="${length}">
                <c:forEach begin="1" var="j" end="${length}">
                    <div class="buttonInGame pseudoButton">${((i - 1) * length) + j}</div>
                </c:forEach>
                <br/>
            </c:forEach>
        </div>
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
