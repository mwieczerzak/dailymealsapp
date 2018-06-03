
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal Details</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet"></head>
<body>
<jsp:include page="menu.jsp" />

Posiłek : ${meal.name} <br>
Data: ${meal.mealDate} <br>
Białko : ${meal.proteins} <br>
Węglowodany : ${meal.carbs} <br>
Tłuszcze : ${meal.fats} <br>
Kalorie : ${meal.calories} <br>

<a href="${pageContext.servletContext.contextPath}/meals">Wróć</a>

</body>
</html>
