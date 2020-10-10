<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>--%>

<html>
<head>
    <title>Meals</title>
    <style>
        table, tr,th{
            border: 1px solid black;
            border-collapse: collapse;
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Meals</h2>
    <p><a href="mealList?action=create">Add meal</a></p>
    <table>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>

        <c:forEach var="meal" items="${mealList}">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealTo"/>
            <c:if test="${meal.excess}">
                <tr style="color: red">
<%--                    <javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd HH-mm" var="parsedDate"/>--%>
                    <th><%=TimeUtil.dateToString(meal.getDateTime())%></th>
                    <th>${meal.description}</th>
                    <th>${meal.calories}</th>
                    <th><a href="mealList?action=delete&mealId=${meal.id}">Delete</a></th>
                    <th><a href="mealList?action=update&mealId=${meal.id}">Update</a></th>
                </tr>
            </c:if>

            <c:if test="${!meal.excess}">
                <tr style="color: green">
                    <th><%=TimeUtil.dateToString(meal.getDateTime())%></th>
                    <th>${meal.description}</th>
                    <th>${meal.calories}</th>
                    <th><a href="mealList?action=delete&mealId=${meal.id}">Delete</a></th>
                    <th><a href="mealList?action=update&mealId=${meal.id}">Update</a></th>
                </tr>
            </c:if>
        </c:forEach>
    </table>

</body>
</html>
