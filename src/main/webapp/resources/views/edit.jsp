<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add/edit meal</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">
    <style>
        .error {
            color: #ff0000;
        }
    </style>
</head>
<body>
<jsp:include page="menu.jsp"/>
Dodaj/edytuj posiłek:
<form:form action="add" modelAttribute="newMeal" method="post">
    <form:input type="hidden" path="id"/><br>

    Nazwa posiłku: <form:input path="name"/>
    <form:errors path="name" cssClass="error"/><br>

    Data posiłku: <form:input path="mealDate"/>
    <form:errors path="mealDate" cssClass="error"/><br>

    Ilość białka: <form:input path="proteins"/>
    <form:errors path="proteins" cssClass="error"/><br>

    Ilość węglowodanów:<form:input path="carbs"/>
    <form:errors path="carbs" cssClass="error"/><br>

    Ilość tłuszczy: <form:input path="fats"/>
    <form:errors path="fats" cssClass="error"/><br>

    <input type="submit" name="apply" value="Zatwierdz"/>
</form:form>

</body>
</html>
