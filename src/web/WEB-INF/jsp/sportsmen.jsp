<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/jsp/styles.css"%></style>
<html>
<title> Sportsmen </title>
<body>
<a href="index">home page</a>
<a href="competitions">competitions</a>
<a href="sportsmen">sportsmen</a>
<a href="teams">teams</a>
<br>
<hr>
<h1>Sportsmen</h1>
<br>
<c:forEach items="${sportsmenList}" var="sportsman">
    <a href="sportsman?id=${sportsman.sportsmanId}">
            ${sportsman.sportsmanName}
    </a>
    <br>
</c:forEach>
<br>
<a href="sportsman_add">Add sportsman</a>
</body>
</html>