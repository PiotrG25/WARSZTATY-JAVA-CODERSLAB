<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>exercise</title>
    <style>
        table td{
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <a href='/main'>Strona główna</a><br/>
    <a href='/addExercise'>dodanie zadania</a><br/>
    <a href='/editExercise'>edycja zadania</a><br/>
    <a href='/deleteExercise'>usuniecie zadania</a><br/>

    <table>
        <tr>
            <td>id</td>
            <td>title</td>
            <td>description</td>
        </tr>
        <c:forEach items="${ex}" var="e">
            <tr>
                <td>${e.id}</td>
                <td>${e.title}</td>
                <td>${e.description}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
