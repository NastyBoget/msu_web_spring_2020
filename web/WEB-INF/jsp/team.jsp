<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/jsp/styles.css"%></style>
<html>
<head>
    <title>Team</title>
</head>
<body>
<a href="index">home page</a>
<br>
<hr>
<h1>${team.teamName}</h1>
<br>
<table>
    <tr>
        <th>Name</th>
        <th>Team members</th>
        <th>Competitions history</th>
        <th>Trainer</th>
    </tr>
    <tr>
        <td>${team.teamName}</td>
        <td>
            <c:forEach items="${sportsmanList}" var="sportsman">
                <a href="sportsman?id=${sportsman.sportsmanId}">
                        ${sportsman.sportsmanName}
                </a>
                <br>
            </c:forEach>
        </td>
        <td>
            <c:forEach items="${compList}" var="competition">
                <a href="competition?id=${competition.compId}">
                        ${competition.compName}
                </a>
                <br>
            </c:forEach>
        </td>
        <td>${team.trainerId.name}</td>
    </tr>
</table>
<br>
<a href="team_delete?id=${team.teamId}">Delete team</a>
<br>
<a href="team_update">Edit team</a>
</body>
</html>
