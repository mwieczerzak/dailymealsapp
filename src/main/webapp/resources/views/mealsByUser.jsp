<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">
</head>
<body>
<jsp:include page="menu.jsp"/>
<br>

<form action="${pageContext.servletContext.contextPath}/meals/add" method="get">
    <input type="submit" value="Dodaj posiłek"/>
</form>

Moje posiłki:<br/>

<table>
    <thead>
    <tr>
        <td>Nazwa</td>
        <td>Kiedy</td>
        <td>Kalorie</td>
        <td>Edycja</td>
        <td>Szczegóły</td>
        <td></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${findMealsByUserId}" var="meal">
        <tr>
            <td>${meal.name}</td>
            <td>${meal.mealDate}</td>
            <td>${meal.calories}</td>
            <td><a href="${pageContext.servletContext.contextPath}/meals/edit?id=${meal.id}">Edytuj</a></td>
            <td><a href="${pageContext.servletContext.contextPath}/meals/${meal.id}">Przejdź</a></td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/meals/delete/${meal.id}" method="get"
                      onclick="return confirm('Operacja jest nieodwracalna, czy na pewno chcesz usunąć posiłek?')">
                    <input type="submit" value="Usuń"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>

<table>
    <tr>
        <td>Kalorie łącznie</td>
        <td>${sumCalories}</td>
    </tr>
</table>

</body>