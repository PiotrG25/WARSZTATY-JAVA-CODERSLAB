<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Register</title>
</head>
<body>
    <%
        request.setAttribute("inRegister", true);
    %>
    <c:import url="header.jsp"/>

    <form:form method="post" modelAttribute="user">

        <form:input path="name" type="text" placeholder="name"/><br/>
        <form:errors path="name" cssStyle="display: block;"/>
        <c:if test="${nameNotUnique}"><span class="error">ta nazwa jest już zajęta</span><br/></c:if>

        <form:password path="password" placeholder="password"/><br/>
        <form:errors path="password" cssStyle="display: block;"/>

        <input type="password" name="password2" placeholder="confirm password"/><br/>
        <c:if test="${differentPassword}"><span class="error">różne hasła</span><br/></c:if>

        <form:input path="email" type="email" placeholder="email"/><br/>
        <form:errors path="email" cssStyle="display: block;"/>
        <c:if test="${emailNotUnique}"><span class="error">ten email jest już zajęty</span><br/></c:if>

        <input type="submit" value="Zarejestruj się"/><br/>
        <c:if test="${arguments}"><span class="error">Brakuje kilku argumentów</span><br/></c:if>
        <c:if test="${success}"><span class="success">Rejestracja się powiodła</span><br/></c:if>

    </form:form>

    <c:import url="footer.jsp"/>
</body>
</html>
