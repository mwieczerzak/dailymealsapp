<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Menu</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">

</head>
<body>

<ul>
    <li><a href="${pageContext.servletContext.contextPath}/main">Strona główna</a></li>
    <sec:authorize access="hasRole('ADMIN')">
        <li><a href="${pageContext.servletContext.contextPath}/users">Użytkownicy</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/meals">Posiłki</a></li>
    </sec:authorize>
    <sec:authorize access="hasRole('USER')">
        <li><a href="${pageContext.servletContext.contextPath}/users/meals">Moje posiłki</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/meals/search/date">Posiłki według daty</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/meals/search">Wyszukiwarka</a></li>
    </sec:authorize>
    <sec:authorize access="isAnonymous()">
        <li><a href="${pageContext.servletContext.contextPath}/login">Login</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/users/add">Zarejestruj się</a></li>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <li><a href="${pageContext.servletContext.contextPath}/users/user">Dane użytkownika</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/logout">Wyloguj</a></li>
    </sec:authorize>
</ul>

</body>
</html>




