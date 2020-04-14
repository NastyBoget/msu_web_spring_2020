<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Teams</title>
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
        Teams
    </h1>
</div>
<br>
<c:forEach items="${teamsList}" var="team">
    <a href="team?id=${team.teamId}">
        <div>
                ${team.teamName}
        </div>
    </a>
</c:forEach>
<br>
<a href="team_add">
    <div>
        Add team
    </div>
</a>
</body>
</html>
