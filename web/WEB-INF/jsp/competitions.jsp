<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Competitions</title>
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
        Competitions
    </h1>
</div>
<br>
<c:forEach items="${competitionsList}" var="competition">
    <a href="competition?id=${competition.compId}">
            ${competition.compName}
    </a>
    <ul>
        <li> Time: ${competition.compTime}
        <li> Location: ${competition.location}
        <li> Kind of sport: ${competition.sportKind}
    </ul>
</c:forEach>
<br>
<a href="competition_add">
    <div>
        Add competition
    </div>
</a>
</body>
</html>
