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

    <form:form method="post" modelAttribute="userEditPasswordDto">

        <form:password path="newPassword" placeholder="new password"/>
        <form:errors path="newPassword" cssClass="error"/>

        <form:password path="confirmNewPassword" placeholder="repeat new password"/>
        <form:errors path="confirmNewPassword" cssClass="error"/>
        <c:if test="${differentPassword}"><span class="error">Różne hasła</span></c:if>

        <form:password path="oldPassword" placeholder="confirm with old password"/>
        <form:errors path="oldPassword" cssClass="error"/>

        <input type="submit" value="zmień hasło"/>

    </form:form>

</body>
</html>
