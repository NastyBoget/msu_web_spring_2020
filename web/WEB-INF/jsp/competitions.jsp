<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/jsp/styles.css"%></style>
<%--https://www.w3schools.com/howto/howto_js_filter_table.asp--%>
<html>
<head>
    <title>Competitions</title>
</head>
<body>
<a href="index">home page</a>
<br>
<hr>
<h1>Competitions</h1>
<br>
<table id="CompTable">
    <tr>
        <th>Name</th>
        <th>Time</th>
        <th>Location</th>
        <th>Kind of sport</th>
    </tr>
    <tr>
        <td><input type="text" id="Name" class="search_icon" onkeyup="FilterFunction(0, id)" placeholder="Search by name.."></td>
        <td><input type="text" id="Time" class="search_icon" onkeyup="FilterFunction(1, id)" placeholder="Search by time.."></td>
        <td><input type="text" id="Location" class="search_icon" onkeyup="FilterFunction(2, id)" placeholder="Search location .."></td>
        <td><input type="text" id="SportKind" class="search_icon" onkeyup="FilterFunction(3, id)" placeholder="Search by kind of sport.."></td>
    </tr>
<c:forEach items="${competitionsList}" var="competition">
    <tr>
        <td>
            <a href="competition?id=${competition.compId}">
                ${competition.compName}
            </a>
        </td>
        <td> ${competition.compTime}</td>
        <td> ${competition.location}</td>
        <td> ${competition.sportKind}</td>
    </tr>
</c:forEach>
</table>
<br>
<script>
    function FilterFunction(id, input) {
        // Declare variables
        var real_input, filter, table, tr, td, i, txtValue;
        real_input = document.getElementById(input);
        filter = real_input.value.toUpperCase();
        table = document.getElementById("CompTable");
        tr = table.getElementsByTagName("tr");

        // Loop through all table rows, and hide those who don't match the search query
        for (i = 2; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[id];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>
<a href="competition_add">Add competition</a>
</body>
</html>
