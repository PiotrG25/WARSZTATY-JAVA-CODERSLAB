<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Register</title>
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
        request.setAttribute("inRegister", true);
    %>
    <c:import url="header.jsp"/>

    <form:form method="post" modelAttribute="userDto">

        <form:input path="name" type="text" placeholder="name"/><br/>
        <form:errors path="name"/>
        <c:if test="${nameNotUnique}"><span class="error">Istnieje użytkownik o takiej nazwie</span></c:if>

        <form:password path="password" placeholder="password"/><br/>
        <form:errors path="password"/>

        <form:password path="confirmPassword" placeholder="confirm password"/><br/>
        <form:errors path="confirmPassword"/>
        <c:if test="${differentPassword}"><span class="error">Hasła się nie zgadzają</span></c:if>

        <form:input path="email" type="email" placeholder="email"/><br/>
        <form:errors path="email"/>
        <c:if test="${emailNotUnique}"><span class="error">Istnieje użytkownik o takim emailu</span></c:if>

        <input type="submit" value="Zarejestruj się"/><br/>
        <c:if test="${success}"><span class="success">Rejestracja się powiodła</span><br/></c:if>

    </form:form>

</body>
</html>
