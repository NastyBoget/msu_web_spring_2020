<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/jsp/styles.css"%></style>
<html>
<head>
    <title>Seats information</title>
</head>
<body>
<a href="index">home page</a>
<a href="competitions">competitions</a>
<a href="sportsmen">sportsmen</a>
<a href="teams">teams</a>
<br>
<hr>
<h1>Information about free seats</h1>
<br>
<table>
    <tr>
        <th>Type</th>
        <th>Number of free seats</th>
        <th>Price</th>
        <th>Buy ticket</th>
    </tr>
    <c:forEach items="${seats}" var="seat">
        <tr>
            <td>${seat.type}</td>
            <td>${seat.numFreeSeats}</td>
            <td>${seat.price}</td>
            <td style="${seat.numFreeSeats > 0 ? 'display:block' : 'display:none'}">
                <a href="buy?id=${seat.compId.compId}&type=${seat.type}">
                    Buy ticket
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
