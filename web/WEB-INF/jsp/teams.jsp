<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/jsp/styles.css"%></style>
<html>
<head>
    <title>Teams</title>
</head>
<body>
<a href="index">home page</a>
<br>
<hr>
<h1>Teams</h1>
<br>
<c:forEach items="${teamsList}" var="team">
    <a href="team?id=${team.teamId}">${team.teamName}</a>
    <br>
</c:forEach>
<br>
<a href="team_add">Add team</a>
</body>
</html>
