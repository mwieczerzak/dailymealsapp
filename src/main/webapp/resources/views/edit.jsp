
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Daily Meals Application</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">
    <style>
    .error {
        color: #ff0000;
    }
    </style>
</head>
<body>
<jsp:include page="menu.jsp" />
Dodaj/edytuj posiłek:
<form:form action="add" modelAttribute="newMeal" method="post">
    <form:input type="hidden" path="id"/><br>

    <form:label path="name">Nazwa posiłku:</form:label>
    <form:input path="name"/>
    <form:errors path="name" cssClass="error" /><br>

    <form:label path="mealDate">Data posiłku:</form:label>
    <form:input path="mealDate"/>
    <form:errors path="mealDate" cssClass="error" /><br>

    <form:label path="proteins">Ilość białka:</form:label>
    <form:input path="proteins"/>
    <form:errors path="proteins" cssClass="error" /><br>

    <form:label path="carbs">Ilość węglowodanów:</form:label>
    <form:input path="carbs"/>
    <form:errors path="carbs" cssClass="error" /><br>

    <form:label path="fats">Ilość tłuszczy:</form:label>
    <form:input path="fats"/>
    <form:errors path="fats" cssClass="error" /><br>

    <input type="submit" name="apply" value="Zatwierdz"/>
</form:form>

</body>
</html>
