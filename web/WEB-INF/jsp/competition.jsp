<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/jsp/styles.css"%></style>
<html>
<head>
    <title>Competition</title>
</head>
<body>
<a href="index">home page</a>
<br>
<hr>
<h1>${competition.compName}</h1>
<br>
<table>
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
                    <div>${sportsman.sportsmanName}</div>
                </a>
            </c:forEach>
        </td>
        <td>
            <c:forEach items="${teamList}" var="team">
                <a href="team?id=${team.teamId}">
                    <div>${team.teamName}</div>
                </a>
            </c:forEach>
        </td>
        <td>${competition.compStatus}</td>
        <td>
            <div style="${competition.isFreeSeatsStatus() ? 'display:block' : 'display:none'}">
                there are free seats
            </div>
            <div style="${competition.isFreeSeatsStatus() ? 'display:none' : 'display:block'}">
                no free seats
            </div>
        </td>
        </tr>
</table>
<br>
<table style="${sportsmanList.size() == 0 ? 'display:none' : 'display:block'}">
    <caption> Results for sportsmen </caption>
    <tr>
        <th>Participant</th>
        <th>Points</th>
        <th>Place</th>
    </tr>
    <c:forEach items="${sportsmanResults}" var="results">
        <tr>
            <td>
                <a href="sportsman?id=${results.sportsmanId.sportsmanId}">
                    ${results.sportsmanId.sportsmanName}
                </a>
            </td>
            <td>${results.points}</td>
            <td>${results.place}</td>
        </tr>
    </c:forEach>
</table>
<br>
<table style="${teamList.size() == 0 ? 'display:none' : 'display:block'}">
    <caption> Results for teams </caption>
    <tr>
        <th>Participant</th>
        <th>Points</th>
        <th>Place</th>
    </tr>
    <c:forEach items="${teamsResults}" var="results">
        <tr>
            <td>
                <a href="team?id=${results.teamId.teamId}">
                        ${results.teamId.teamName}
                </a>
            </td>
            <td>${results.points}</td>
            <td>${results.place}</td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="seats?id=${competition.compId}">More information about free seats and prices</a>
<br>
<a href="competition_delete">Delete competition</a>
<br>
<a href="competition_update">Edit competition</a>
</body>
</html>
