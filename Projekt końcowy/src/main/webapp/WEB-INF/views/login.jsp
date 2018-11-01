<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Login</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css"/>
</head>
<body>
    <a href="/register">Zarejestruj się</a><br/>
    <form action="/login" method="post">
        <input type="text" name="name" placeholder="name"/><br/>
        <input type="password" name="password" placeholder="password"/><br/>
        <input type="submit" value="Zaloguj się"/><br/>
        <c:if test="${arguments}"><span class="error">Brakuje kilku argumentów</span><br/></c:if>
        <c:if test="${error}"><span class="error">Niewłaściwa nazwa użytkownika lub hasło</span><br/></c:if>
        <c:if test="${success}"><span class="success">Witam ${user.name}</span><br/></c:if>
    </form>
</body>
</html>
