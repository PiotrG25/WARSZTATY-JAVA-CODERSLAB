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
            <table style="margin-left: auto; margin-right: auto">
                <tr>
                    <td></td>
                    <td>poziom</td>
                    <td>ruchy</td>
                    <td>czas</td>
                </tr>
                <c:forEach begin="1" var="i" end="10">
                    <tr>
                        <td>${i}</td>
                        <td>${i}</td>
                        <td>${i}</td>
                        <td>${i}</td>
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
