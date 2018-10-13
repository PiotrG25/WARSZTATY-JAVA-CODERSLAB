<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>exercise</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="style.css" type="text/css"/>
</head>
<body>
    <a href="/main">Strona główna</a><br/>
    <ol>
        <li>
            dodanie zadania
            <form action="/exercise" method="post">
                <input type="text" name="type" value="add" style="display: none;"/>
                Podaj dane zadania<br/>
                <input type="text" name="title" placeholder="tytul"/><br/>
                <input type="textarea" name="description" placeholder="opis"/><br/>
                <input type="submit" value="Dodaj"/><br/>
            </form>
        </li>
        <li>
            edycja użytkowników
            <form action="/exercise" method="post">
                <input type="text" name="type" value="edit" style="display: none;"/>
                Podaj id zadania do edycji<br/>
                <input type="number" name="id" placeholder="id"/><br/><br/>
                <input type="text" name="title" placeholder="tytul"/><br/>
                <input type="textarea" name="description" placeholder="opis"/><br/>
                <input type="submit" value="Edytuj"/><br/>
            </form>
        </li>
        <li>
            usuniecie zadania
            <form action="/exercise" method="post">
                <input type="text" name="type" value="delete" style="display: none;"/>
                Podaj id zadania do usunięcia<br/>
                <input type="number" name="id" placeholder="id"/>
                <input type="submit" value="Usuń"/>
            </form>
        </li>
    </ol>

    <table>
        <tr>
            <td>id</td>
            <td>title</td>
            <td>description</td>
        </tr>
        <c:forEach items="${exercises}" var="e">
            <tr>
                <td>${e.id}</td>
                <td>${e.title}</td>
                <td>${e.description}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
