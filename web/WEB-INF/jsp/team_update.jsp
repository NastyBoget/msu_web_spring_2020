<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style><%@include file="/WEB-INF/jsp/styles.css"%></style>
<html>
<head>
    <c:if test="${empty teamForm.teamId}">
        <title>Add team</title>
    </c:if>
    <c:if test="${!empty teamForm.teamId}">
        <title>Edit team</title>
    </c:if>
</head>
<body>
<a href="index">home page</a>
<a href="competitions">competitions</a>
<a href="sportsmen">sportsmen</a>
<a href="teams">teams</a>
<br>
<hr>
<c:if test="${empty teamForm.teamId}">
    <h1>Add team</h1>
</c:if>
<c:if test="${!empty teamForm.teamId}">
    <h1>Edit team</h1>
</c:if>
<br>
<c:if test="${empty teamForm.teamId}">
    <c:url value="/team_add" var="var"/>
</c:if>
<c:if test="${!empty teamForm.teamId}">
    <c:url value="/team_update" var="var"/>
</c:if>
<form:form modelAttribute = "teamForm" action="${var}" method="POST">
    <c:if test="${!empty teamForm.teamId}">
        <form:input type="hidden" path="teamId" value="${teamForm.teamId}"/>
    </c:if>
    <label for="name">Name</label>
    <form:input placeholder="Name" type="text" path="name" id="name"/>
    <br>
    <label for="trainer">Trainer</label>
    <form:select path="trainerId" id="trainer">
        <c:forEach items="${trainers}" var="trainer">
            <option value="${trainer.trainerId}">${trainer.name}</option>
        </c:forEach>
        <option value="">None</option>
    </form:select>
    <br>
    <label for="sportsmen">Sportsmen</label>
    <form:select multiple="true" path="sportsmenList" id="sportsmen">
        <c:forEach items="${sportsmen}" var="sportsman">
            <option value="${sportsman.sportsmanId}">${sportsman.sportsmanName}</option>
        </c:forEach>
        <option value="">None</option>
    </form:select>
    <br>
    <c:if test="${empty teamForm.teamId}">
        <input type="submit" value="Add team">
    </c:if>
    <c:if test="${!empty teamForm.teamId}">
        <input type="submit" value="Edit team">
    </c:if>
</form:form>
</body>
</html>
