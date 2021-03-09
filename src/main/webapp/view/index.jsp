<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<h1><center>Welcome to Super Market</center></h1>
<h4>Different Roles to Register:</h4>
<form:form action="abc" modelAttribute="user" method="get">
<center><form:select path="userCategory">
<form:option value="" label="select"/>
<option value="manager" label="Manager"/>
<option value="cashier" label="Cashier"/>
<option value="admin" label="Admin"/>
</form:select><br><br>
<input type="submit" name="submit" value="Submit"></center>
</form:form>
</body>
</html>