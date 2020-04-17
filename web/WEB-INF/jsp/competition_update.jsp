<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style><%@include file="/WEB-INF/jsp/styles.css"%></style>
<html>
<head>
    <c:if test="${empty competitionForm.compId}">
        <title>Add competition</title>
    </c:if>
    <c:if test="${!empty competitionForm.compId}">
        <title>Edit competition</title>
    </c:if>
</head>
<body>
<a href="index">home page</a>
<a href="competitions">competitions</a>
<a href="sportsmen">sportsmen</a>
<a href="teams">teams</a>
<br>
<hr>
<c:if test="${empty competitionForm.compId}">
    <h1>Add competition</h1>
</c:if>
<c:if test="${!empty competitionForm.compId}">
    <h1>Edit competition</h1>
</c:if>
<br>
<c:if test="${empty competitionForm.compId}">
    <c:url value="/competition_add" var="var"/>
</c:if>
<c:if test="${!empty competitionForm.compId}">
    <c:url value="/competition_update" var="var"/>
</c:if>
<form:form modelAttribute = "competitionForm" action="${var}" method="POST">
    <c:if test="${!empty competitionForm.compId}">
        <form:input type="hidden" path="compId" value="${competitionForm.compId}"/>
    </c:if>
    <label for="name">Name</label>
    <form:input placeholder="Name" type="text" path="compName" id="name"/>
    <br>
    <label for="location">Location</label>
    <form:input placeholder="Location" type="text" path="location" id="location"/>
    <br>
    <label for="time">Time</label>
    <form:input placeholder="yyyy-mm-dd hh:mm:ss" type="datetime-local" path="compTime" id="time"/>
    <br>
    <label for="status">Status</label>
    <form:select path="status" id="status">
        <option value="soon">soon</option>
        <option value="now">now</option>
        <option value="passed">passed</option>
    </form:select>
    <br>
    <label for="sportKind">Kind of sport</label>
    <form:select path="sportKind" id="sportKind">
        <option value="football">football</option>
        <option value="synchronized_swimming">synchronized_swimming</option>
        <option value="gymnastics">gymnastics</option>
        <option value="biathlon">biathlon</option>
        <option value="hockey">hockey</option>
    </form:select>
    <br>
    <c:if test="${empty competitionForm.compId}">
        <label for="sportsmen">Sportsmen</label>
        <form:select multiple="true" path="sportsmanList" id="sportsmen">
            <c:forEach items="${sportsmen}" var="sportsman">
                <option value="${sportsman.sportsmanId}">${sportsman.sportsmanName}</option>
            </c:forEach>
        </form:select>
        <br>
        <label for="teams">Teams</label>
        <form:select multiple="true" path="teamList" id="teams">
            <c:forEach items="${teams}" var="team">
                <option value="${team.teamId}">${team.teamName}</option>
            </c:forEach>
        </form:select>
        <br>
        <c:forEach items="${seats}" var="seat">
            <label for="price">Price for ${seat}</label>
            <form:input placeholder="Price" type="text" path="prices" id="price"/>
            <br>
            <label for="numSeats">Seats number for ${seat}</label>
            <form:input placeholder="Seats number" type="text" path="numSeats" id="numSeats"/>
            <br>
            <label for="numFreeSeats">Free seats number for ${seat}</label>
            <form:input placeholder="Free seats number" type="text" path="numFreeSeats" id="numFreeSeats"/>
            <br>
        </c:forEach>
    </c:if>
    <c:if test="${!empty competitionForm.compId}">
        <c:forEach items="${sportsmen}" var="sportsman">
            <label for="sPoints">Points for sportsman ${sportsman.sportsmanName}</label>
            <form:input placeholder="Points" type="text" path="sportsmenPoints" id="sPoints"/>
            <br>
            <label for="sPlace">Place for sportsman ${sportsman.sportsmanName}</label>
            <form:input placeholder="Place" type="text" path="sportsmenPlaces" id="sPlace"/>
            <br>
        </c:forEach>
        <c:forEach items="${teams}" var="team">
            <label for="tPoints">Points for team ${team.teamName}</label>
            <form:input placeholder="Points" type="text" path="teamsPoints" id="tPoints"/>
            <br>
            <label for="tPlace">Place for team ${team.teamName}</label>
            <form:input placeholder="Place" type="text" path="teamsPlaces" id="tPlace"/>
            <br>
        </c:forEach>
    </c:if>
    <c:if test="${empty competitionForm.compId}">
        <input type="submit" value="Add competition">
    </c:if>
    <c:if test="${!empty competitionForm.compId}">
        <input type="submit" value="Edit competition">
    </c:if>
</form:form>
</body>
</html>
