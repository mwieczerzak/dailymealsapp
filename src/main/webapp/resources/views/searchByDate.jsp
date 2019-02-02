<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Meals By Date</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/app.css" rel="stylesheet">
</head>
<body>
<jsp:include page="menu.jsp"/>

<form:form modelAttribute="mealDates" action="date" method="post">
    <p>Wybierz dzień</p>
    <form:select path="date">
        <c:forEach items="${dates}" var="date" >
            <option>${date}</option>
        </c:forEach>
    </form:select>
    <input type="submit" value="Szukaj">
</form:form>

</body>
</html>
