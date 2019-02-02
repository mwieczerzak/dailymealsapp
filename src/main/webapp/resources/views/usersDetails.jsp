<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User Details</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">
</head>

<body>
<jsp:include page="menu.jsp"/>
<br>

<form action="edit" method="get">
    <input type="submit" value="Edytuj"/>
    <input type="hidden" name="id" value="${user.id}"/>
</form>

<h2>Login: ${user.login}</h2>
<h2>Password: ${user.password}</h2>
<h2>First name: ${user.firstName}</h2>
<h2>Last name: ${user.lastName}</h2>

</body>
</html>
