<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <form:form method="post" modelAttribute="userEmailEditDto">

        <form:input path="email" placeholder="nowy email"/>
        <form:errors path="email" cssClass="error"/>
        <c:if test="${email}"><span class="error">Ten email jest już zajęty</span></c:if>

        <form:password path="password" placeholder="potwierdz haslo"/>
        <form:errors path="password" cssClass="error"/>
        <c:if test="${password}"><span class="error">Nie właściwe hasło</span></c:if>

        <input type="submit" value="Zapisz"/>

        <c:if test="${success}"><span class="success">Zmieniono email</span></c:if>
    </form:form>
</body>
</html>
