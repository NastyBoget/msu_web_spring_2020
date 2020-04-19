<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style><%@include file="/WEB-INF/jsp/styles.css"%></style>
<html>
<head>
    <c:if test="${empty sportsmanForm.sportsmanId}">
        <title>Add sportsman</title>
    </c:if>
    <c:if test="${!empty sportsmanForm.sportsmanId}">
        <title>Edit sportsman</title>
    </c:if>
</head>
<body>
<a href="index">home page</a>
<a href="competitions">competitions</a>
<a href="sportsmen">sportsmen</a>
<a href="teams">teams</a>
<br>
<hr>
<c:if test="${empty sportsmanForm.sportsmanId}">
    <h1>Add sportsman</h1>
</c:if>
<c:if test="${!empty sportsmanForm.sportsmanId}">
    <h1>Edit sportsman</h1>
</c:if>
<br>
<c:if test="${empty sportsmanForm.sportsmanId}">
    <c:url value="/sportsman_add" var="var"/>
</c:if>
<c:if test="${!empty sportsmanForm.sportsmanId}">
    <c:url value="/sportsman_update" var="var"/>
</c:if>
<form:form modelAttribute = "sportsmanForm" action="${var}" method="POST">
    <c:if test="${!empty sportsmanForm.sportsmanId}">
        <form:input type="hidden" path="sportsmanId" value="${sportsmanForm.sportsmanId}"/>
    </c:if>
    <label for="name">Name</label>
    <form:input type="text" path="name" id="name" placeholder="Name"/>
    <br>
    <label for="birthday">Birthday</label>
    <form:input type="text" path="birthday" id="birthday" placeholder="yyyy-mm-dd"/>
    <br>
    <label for="trainer">Trainer</label>
    <form:select path="trainerId" id="trainer">
        <c:forEach items="${trainers}" var="trainer">
            <option value="${trainer.trainerId}">${trainer.name}</option>
        </c:forEach>
        <option value="">None</option>
    </form:select>
    <br>
    <label for="team">Team</label>
    <form:select path="teamId" id="team">
        <c:forEach items="${teams}" var="team">
            <option value="${team.teamId}">${team.teamName}</option>
        </c:forEach>
        <option value="">None</option>
    </form:select>
    <br>
    <c:if test="${empty sportsmanForm.sportsmanId}">
        <input type="submit" value="Add sportsman">
    </c:if>
    <c:if test="${!empty sportsmanForm.sportsmanId}">
        <input type="submit" value="Edit sportsman">
    </c:if>
</form:form>
</body>
</html>
