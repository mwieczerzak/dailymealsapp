<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Search</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">
    <style>
        .error {
            color: #ff0000;
        }
    </style>
</head>
<body>
<jsp:include page="menu.jsp"/>

<form:form modelAttribute="criterias" action="search" method="post">
    Kalorie od: <form:input path="from"/>
    <form:errors path="from" cssClass="error" /><br>
    Kalorie do: <form:input path="to"/>
    <form:errors path="to" cssClass="error" /><br>
    <input type="submit" value="Szukaj">
</form:form>
</body>
</html>
