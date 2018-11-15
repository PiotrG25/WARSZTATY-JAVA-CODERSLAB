<%@ page import="pl.coderslab.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${user.name}</title>

    <style>
        .tabDiv{
            display: inline-block;
        }
        table{
            border-collapse: collapse;
        }
        table, td, tr{
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <c:import url="header.jsp"/>
    Witaj ${user.name}!<br/>

    <h3>Statysyki</h3>
    <ul>
        <li>Wygrane: ${winCount}</li>
        <li>Łącznie ruchów: ${movesCount}</li>
        <li>Łączny czas gry: ${timeCount}</li>
    </ul>

    <div id="redZone">
        <a href="/editEmail">Edytuj email</a><br/>
        <a href="/editPassword">Edytuj hasło</a><br/>
        <a href="/deleteUser">Usuń konto</a><br/>
    </div>



    <div class="tabDiv">
        <table>
            <tr><td colspan="3"><h4>Najlepsze wg ruchów</h4></td></tr>
            <tr>
                <td>Tab: 2x2</td>
                <td>ruchy</td>
                <td>czas</td>
            </tr>
            <c:forEach items="${gamesByMoves2}" var="game" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${game.moves}</td>
                    <td>${game.time}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="tabDiv">
        <table>
            <tr><td colspan="3"><h4>Najlepsze wg czasu</h4></td></tr>
            <tr>
                <td>tab: 2x2</td>
                <td>ruchy</td>
                <td>czas</td>
            </tr>
            <c:forEach items="${gamesByTime2}" var="game" varStatus="i">
            <tr>
                    <td>${i.count}</td>
                    <td>${game.moves}</td>
                    <td>${game.time}</td>
                </tr>
            </c:forEach>
        </table>
    </div>


    <div class="tabDiv">
        <table>
            <tr><td colspan="3"><h4>Najlepsze wg ruchów</h4></td></tr>
            <tr>
                <td>Tab: 3x3</td>
                <td>ruchy</td>
                <td>czas</td>
            </tr>
            <c:forEach items="${gamesByMoves3}" var="game" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${game.moves}</td>
                    <td>${game.time}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="tabDiv">
        <table>
            <tr><td colspan="3"><h4>Najlepsze wg czasu</h4></td></tr>
            <tr>
                <td>tab: 3x3</td>
                <td>ruchy</td>
                <td>czas</td>
            </tr>
            <c:forEach items="${gamesByTime3}" var="game" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${game.moves}</td>
                    <td>${game.time}</td>
                </tr>
            </c:forEach>
        </table>
    </div>


    <div class="tabDiv">
        <table>
            <tr><td colspan="3"><h4>Najlepsze wg ruchów</h4></td></tr>
            <tr>
                <td>Tab: 4x4</td>
                <td>ruchy</td>
                <td>czas</td>
            </tr>
            <c:forEach items="${gamesByMoves4}" var="game" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${game.moves}</td>
                    <td>${game.time}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="tabDiv">
        <table>
            <tr><td colspan="3"><h4>Najlepsze wg czasu</h4></td></tr>
            <tr>
                <td>tab: 4x4</td>
                <td>ruchy</td>
                <td>czas</td>
            </tr>
            <c:forEach items="${gamesByTime4}" var="game" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${game.moves}</td>
                    <td>${game.time}</td>
                </tr>
            </c:forEach>
        </table>
    </div>


    <div class="tabDiv">
        <table>
            <tr><td colspan="3"><h4>Najlepsze wg ruchów</h4></td></tr>
            <tr>
                <td>Tab: 5x5</td>
                <td>ruchy</td>
                <td>czas</td>
            </tr>
            <c:forEach items="${gamesByMoves5}" var="game" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${game.moves}</td>
                    <td>${game.time}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="tabDiv">
        <table>
            <tr><td colspan="3"><h4>Najlepsze wg czasu</h4></td></tr>
            <tr>
                <td>tab: 5x5</td>
                <td>ruchy</td>
                <td>czas</td>
            </tr>
            <c:forEach items="${gamesByTime5}" var="game" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${game.moves}</td>
                    <td>${game.time}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>
