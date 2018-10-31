<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <style>
        .success{
            color: green;
        }
        .error{
            color: red;
        }
    </style>
</head>
<body>
    <a href="/login">Zaloguj się</a><br/>
    <form action="/register" method="post">

        <input type="text" name="name" placeholder="name"/><br/>
        <c:if test="${name}"><span class="error">nazwa powinna zawierać 8-20 znaków, od a-z, A-Z lub 0-9</span><br/></c:if>
        <c:if test="${nameNotUnique}"><span class="error">ta nazwa jest już zajęta</span><br/></c:if>

        <input type="password" name="password" placeholder="password"/><br/>
        <c:if test="${password}"><span class="error">Hasło powinno zawierać 8-20 znaków, małą literę, dużą literę i cyfrę</span><br/></c:if>

        <input type="password" name="password2" placeholder="confirm password"/><br/>
        <c:if test="${diferentPassword}"><span class="error">różne hasła</span><br/></c:if>

        <input type="email" name="email" placeholder="email"/><br/>
        <c:if test="${email}"><span class="error">niewłaściwy email</span><br/></c:if>
        <c:if test="${emailNotUnique}"><span class="error">ten email jest już zajęty</span><br/></c:if>

        <input type="submit" value="Zarejestruj się"/><br/>
        <c:if test="${arguments}"><span class="error">Brakuje kilku argumentów</span><br/></c:if>
        <c:if test="${success}"><span class="success">Rejestracja się powiodła</span><br/></c:if>

    </form>
</body>
</html>
