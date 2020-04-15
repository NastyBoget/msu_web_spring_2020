<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/jsp/styles.css"%></style>
<html>
<head>
    <title>Competition</title>
    <style>
        .status {
            display: none;
        }
    </style>
</head>
<body>
<a href="index">
    <div>
        home page
    </div>
</a>
<hr>
<div style="text-align: center;">
    <h1>
        ${competition.compName}
    </h1>
</div>
<br>
<table id="CompTable">
    <tr>
        <th>Kind of sport</th>
        <th>Time</th>
        <th>Location</th>
        <th>Participants-sportsmen</th>
        <th>Participants-teams</th>
        <th>Status</th>
        <th>Seats information</th>
    </tr>
    <tr>
        <td>${competition.sportKind}</td>
        <td>${competition.compTime}</td>
        <td>${competition.location}</td>
        <td>
            <c:forEach items="${sportsmanList}" var="sportsman">
                <a href="sportsman?id=${sportsman.sportsmanId}">
                    <div>
                            ${sportsman.sportsmanName}
                    </div>
                </a>
            </c:forEach>
        </td>
        <td>
            <c:forEach items="${teamList}" var="team">
                <a href="team?id=${team.teamId}">
                    <div>
                            ${team.teamName}
                    </div>
                </a>
            </c:forEach>
        </td>
        <td>
            ${competition.compStatus}
        </td>
        <td>
            <div id="status" class=status>${competition.isFreeSeatsStatus()} </div>
            <script>
                var status = document.getElementById("status");
                if (status) {
                    document.write("there are free seats");
                } else {
                    document.write("no free seats");
                }
            </script>
        </td>
        </tr>
</table>
<br>
<a href="seats_price">
    <div>
        More information about free seats and prices
    </div>
</a>
<a href="competition_delete">
    <div>
        Delete competition
    </div>
</a>
<a href="competition_update">
    <div>
        Edit competition
    </div>
</a>
</body>
</html>
