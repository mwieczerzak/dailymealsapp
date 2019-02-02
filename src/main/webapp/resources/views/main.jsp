
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Daily Meals Application</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">
</head>

<body>
<jsp:include page="menu.jsp" />

Dziennik posiłków<br>

<sec:authorize access="hasRole('USER')">
    <h1>Jestes zalogowany</h1>
</sec:authorize>

</body>
</html>
