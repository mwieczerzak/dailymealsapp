<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">
</head>
<body>
<jsp:include page="menu.jsp"/>

Moje posiłki:<br>

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
    <c:forEach items="${meals}" var="meal">
        <tr>
            <td>${meal.name}</td>
            <td>${meal.mealDate}</td>
            <td>${meal.calories}</td>
            <td><a href="${pageContext.servletContext.contextPath}/meals/edit?id=${meal.id}">Edytuj</a></td>
            <td><a href="${pageContext.servletContext.contextPath}/meals/${meal.id}">Przejdz</a></td>
            <td>
                <form action="meals/delete" method="post">
                    <input type="submit" name="deleteMeal" value="Usun"/>
                    <input type="hidden" name="mealId" value="${meal.id}"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table><br/>

<table>
    <tr>
        <td>Kalorie łącznie</td>
        <td>${sumCalories}</td>
        <td><a href="${pageContext.servletContext.contextPath}/meals">Szczególy</a></td>
    </tr>
</table>

</body>
</html>
