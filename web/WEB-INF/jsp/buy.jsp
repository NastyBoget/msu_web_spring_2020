<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style><%@include file="/WEB-INF/jsp/styles.css"%></style>
<html>
<head>
    <title>Buy ticket</title>
</head>
<body>
<a href="index">home page</a>
<a href="competitions">competitions</a>
<a href="sportsmen">sportsmen</a>
<a href="teams">teams</a>
<br>
<hr>
<h1>Buy ticket</h1>
<br>
<div style="font-size: 20px">
    Seats type: ${buyForm.seatsType}
</div>
<form:form modelAttribute = "buyForm" action="/buy" method="POST">
    <form:input type="hidden" path="compId" value="${buyForm.compId}"/>
    <form:input type="hidden" path="seatsType" value="${buyForm.seatsType}"/>
    <label for="email">Type e-mail</label>
    <form:input type="text" path="email" id="email" placeholder="e-mail"/>
    <br>
    <label for="card">Card details</label>
    <form:input type="text" path="card" id="card" placeholder="card number"/>
    <br>
    <input type="submit" value="Buy ticket">
</form:form>
</body>
</html>
