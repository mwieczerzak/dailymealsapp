<%--
  Created by IntelliJ IDEA.
  User: matt
  Date: 2018-03-05
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Daily Meals App</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet"></head>
</head>
<body>
<jsp:include page="menu.jsp" />


<%--<form:form modelAttribute="criteria" action="byAmount"--%>
           <%--method="post">--%>
    <%--<form:label path="from">Stawka od:</form:label>--%>
    <%--<form:input path="from"></form:input><br>--%>
    <%--<form:label path="to">Stawka do:</form:label>--%>
    <%--<form:input path="to"></form:input><br>--%>
    <%--<form:label path="name">Nazwa:</form:label>--%>
    <%--<form:input path="name"></form:input><br>--%>
    <%--<input type="submit" value="Szukaj">--%>
<%--</form:form>--%>

Moje posiłki:<br>

<table>
    <thead>
    <tr>
        <td>Nazwa</td>
        <td>Kiedy</td>
        <td>Białko</td>
        <td>Węglowodany</td>
        <td>Tłuszcze</td>
        <td>Kalorie</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="meal">
        <tr>
            <td>${meal.name}</td>
            <td>${meal.mealDate}</td>
            <td>${meal.proteins}</td>
            <td>${meal.carbs}</td>
            <td>${meal.fats}</td>
            <td>${meal.calories}</td>
            <td><a href="${pageContext.servletContext.contextPath}//meal/edit?id=${meal.id}">Edytuj</a></td>
            <td><a href="${pageContext.servletContext.contextPath}/meal/${meal.id}">Przejdz</a></td>
            <td>
                <form action="meal/delete" method="post">
                    <input type="submit" name="deleteMeal" value="Usun"/>
                    <input type="hidden" name="mealId" value="${meal.id}"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="${pageContext.servletContext.contextPath}/meal/add">Dodaj posiłek</a>

</body>
</html>
