<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">
</head>
<body>
<jsp:include page="menu.jsp"/>

Użytkownicy:<br>

<table>
    <thead>
    <tr>
        <td>Login</td>
        <td>Hasło</td>
        <td>Imię</td>
        <td>Nazwisko</td>
        <td></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td><a href="${pageContext.servletContext.contextPath}/users/edit?id=${user.id}">Edytuj</a></td>
            <td><a href="${pageContext.servletContext.contextPath}/users/${user.id}/meals">Posiłki użytkownika</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>

</body>
</html>
