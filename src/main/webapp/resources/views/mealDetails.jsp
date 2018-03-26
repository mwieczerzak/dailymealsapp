<%--
  Created by IntelliJ IDEA.
  User: matt
  Date: 2018-03-05
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Daily Meals App</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet"></head>
<body>
<jsp:include page="menu.jsp" />

Posiłek : ${meal.name} <br>
Data: ${meal.mealDate} <br>
Białko : ${meal.proteins} <br>
Węglowodany : ${meal.carbs} <br>
Tłuszcze : ${meal.fats} <br>
Kalorie : ${meal.calories} <br>

</body>
</html>
