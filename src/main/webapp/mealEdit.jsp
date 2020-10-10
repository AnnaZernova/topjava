
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>Meal</title>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Edit meal</h2>
    <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
    <form method="post" action="mealList">
        <input type="hidden" name="mealId" value="${meal.id}">
        DateTime: <input type="datetime-local" name="datetime-local" value="${meal.dateTime}"/>
        Description: <input type="text" name="description" value="${meal.description}"/>
        Calories: <input type="number" name="calories" value="${meal.calories}"/>
        <button type="submit">Save</button>
        <input type="reset" value="Cancel">
    </form>
</body>
</html>