<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <a href="/login">Zaloguj się</a><br/>
    <form action="/register" method="post">
        <input type="text" name="name" placeholder="name"/><br/>
        <input type="password" name="password" placeholder="password"/><br/>
        <input type="password" name="password2" placeholder="confirm password"/><br/>
        <input type="email" name="email" placeholder="email"/><br/>
        <input type="submit" value="Zarejestruj się"/><br/>
    </form>
</body>
</html>
