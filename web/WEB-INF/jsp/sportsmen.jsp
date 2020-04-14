<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<title> Sportsmen </title>
<body>
<a href="index">
    <div>
        home page
    </div>
</a>
<hr>
<div style="text-align: center;">
<h1>
    Sportsmen
</h1>
</div>
<br>
<c:forEach items="${sportsmenList}" var="sportsman">
    <a href="sportsman?id=${sportsman.sportsmanId}">
        <div>
                ${sportsman.sportsmanName}
        </div>
    </a>
</c:forEach>
<br>
<a href="sportsman_add">
    <div>
        Add sportsman
    </div>
</a>
</body>
</html>