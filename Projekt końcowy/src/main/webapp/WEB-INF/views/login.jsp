<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Login</title>
</head>
<body>
    <a href="/register">Zarejestruj się</a><br/>
    <form action="/login" method="post">
        <input type="text" name="name" placeholder="name"/><br/>
        <input type="password" name="password" placeholder="password"/><br/>
        <input type="submit" value="Zaloguj się"/><br/>
    </form>
</body>
</html>
