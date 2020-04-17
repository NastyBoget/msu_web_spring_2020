<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/jsp/styles.css"%></style>
<html>
<title>Sportsman</title>
<body>
<a href="index">home page</a>
<a href="competitions">competitions</a>
<a href="sportsmen">sportsmen</a>
<a href="teams">teams</a>
<br>
<hr>
<h1>
    ${sportsman.sportsmanName}
</h1>
<br>
<table>
    <tr>
        <th>Name</th>
        <th>Birthday</th>
        <th>Competitions history</th>
        <th>Team participating history</th>
        <th>Trainer</th>
    </tr>
    <tr>
        <td>${sportsman.sportsmanName}</td>
        <td>${sportsman.birthday}</td>
        <td>
            <c:forEach items="${compList}" var="competition">
                <a href="competition?id=${competition.compId}">
                    ${competition.compName}
                </a>
                <br>
            </c:forEach>
        </td>
        <td>
            <c:forEach items="${teamList}" var="team">
                <a href="team?id=${team.teamId}">
                        ${team.teamName}
                </a>
                <br>
            </c:forEach>
        </td>
        <td>${sportsman.trainerId.name}</td>
    </tr>
</table>
<br>
<a href="sportsman_delete?id=${sportsman.sportsmanId}">Delete sportsman</a>
<br>
<a href="sportsman_update?id=${sportsman.sportsmanId}">Edit sportsman</a>
</body>
</html>
