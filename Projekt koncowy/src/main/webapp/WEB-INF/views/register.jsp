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

    <form:form method="post" modelAttribute="user">

        <form:input path="name" type="text" placeholder="name"/><br/>
        <c:if test="${nameNotUnique}"><span class="error">Istnieje użytkownik o takiej nazwie</span></c:if>

        <form:password path="password" placeholder="password"/><br/>
        <input type="password" name="confirmPassword" placeholder="confirm password"/><br/>
        <form:input path="email" type="email" placeholder="email"/><br/>
        <c:if test="${emailNotUnique}"><span class="error">Istnieje użytkownik o takim emailu</span></c:if>

        <input type="submit" value="Zarejestruj się"/><br/>
        <c:if test="${arguments}"><span class="error">Brakuje kilku argumentów</span><br/></c:if>
        <c:if test="${pattern}">
            <span class="error">Nazwa powinna zawierać 8-20 znaków a-z, A-Z, 0-9<br/>
                Hasło powinno zawierać 8-20 znaków małą literę, dużą literę i cyfrę<br/></span>
        </c:if>
        <c:if test="${emailPattern}"><span class="error">Nie właściwy email</span></c:if>
        <c:if test="${differentPassword}"><span class="error">Hasła się nie zgadzają</span></c:if>
        <c:if test="${success}"><span class="success">Rejestracja się powiodła</span><br/></c:if>

    </form:form>

    <c:import url="footer.jsp"/>
</body>
</html>
