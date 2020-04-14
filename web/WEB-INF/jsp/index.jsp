<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>HelloPage</title>
</head>
<body>
<c:forEach items="${sportsmen}" var="sportsman">
  <td>${sportsman.sportsmanName}</td>
</c:forEach>
</body>
</html>