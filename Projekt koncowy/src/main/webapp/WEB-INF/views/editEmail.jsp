<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .error{
            displey: block;
            color: red;
        }
        .success{
            color: green;
        }
    </style>
</head>
<body>

    <c:import url="header.jsp"/>

    <form method="post">
        <input type="email" name="newEmail" placeholder="nowy email" value="${newEmail}"/>
        <input type="password" name="confirmPassword" placeholder="potwierdz haslo"/>
        <input type="submit" value="Zapisz"/>

        <c:if test="${arguments}"><span class="error">Wszystkie pola wymagane</span></c:if>
        <c:if test="${emailPattern}"><span class="error">Nie właściwy email</span></c:if>
        <c:if test="${password}"><span class="error">Nie właściwe hasło</span></c:if>
        <c:if test="${email}"><span class="error">Ten email jest już zajęty</span></c:if>

        <c:if test="${success}"><span class="success">Zmieniono email</span></c:if>
    </form>
</body>
</html>
