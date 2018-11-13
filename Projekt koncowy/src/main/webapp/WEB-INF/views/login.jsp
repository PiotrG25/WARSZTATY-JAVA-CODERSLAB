<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Login</title>
    <style>
        .error{
            display: block;
            color: red;
        }
        .success{
            color: green;
        }
    </style>
</head>
<body>
    <%
        request.setAttribute("inLogin", true);
    %>
    <c:import url="header.jsp"/>

    <form:form method="post" modelAttribute="user">
        <form:input path="name" placeholder="name"/><br/>
        <form:password path="password" placeholder="password"/><br/>

        <input type="submit" value="Zaloguj się"/><br/>
        <c:if test="${arguments}"><span class="error">Wszystkie pola wymagane</span><br/></c:if>
        <c:if test="${error}"><span class="error">Niewłaściwa nazwa użytkownika lub hasło</span><br/></c:if>
        <c:if test="${success}"><span class="success">Witam ${user.name}</span><br/></c:if>
    </form:form>

</body>
</html>
