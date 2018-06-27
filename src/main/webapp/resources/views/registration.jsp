<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">

    <style>
        .error {
            color: #ff0000;
        }
    </style>
</head>
<body>

Zarejestruj się:
<form:form action="add" modelAttribute="newUser" method="post">
    <form:input type="hidden" path="id"/><br>

    Login: <form:input path="login"/>
    <form:errors path="login" cssClass="error" /><br>

    Hasło: <form:input path="password"/>
    <form:errors path="password" cssClass="error" /><br>

    Imię: <form:input path="firstName"/>
    <form:errors path="firstName" cssClass="error" /><br>

    Nazwisko:<form:input path="lastName"/>
    <form:errors path="lastName" cssClass="error" /><br>

    <input type="submit" name="apply" value="Zatwierdz"/>
</form:form>


</body>
</html>
