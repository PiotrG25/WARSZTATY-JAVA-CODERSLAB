<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .error{
            color: red;
        }
    </style>
</head>
<body>

    <c:import url="header.jsp"/>

    <form:form method="post" modelAttribute="userDeleteDto">

        <form:password path="password" placeholder="wpisz haslo by usunac konto"/>
        <form:errors path="password" cssClass="error"/>
        <c:if test="${password}"><span class="error">Błędna hasło</span></c:if>

        <input type="submit" value="Usun konto"/>

    </form:form>

</body>
</html>
