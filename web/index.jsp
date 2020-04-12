<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Sportsmen</title>
  </head>

  <body>
  <c:forEach items="${sportsmen}" var="sportsman">
    <a href="sportsman?id=${sportsman.sportsmanId}">
      <div class="sportsman_entry" id="sportsman_entry_${sportsman.sportsmanId}">
          ${sportsman.sportsmanName}
      </div>
    </a>
  </c:forEach>

  </body>

</html>
