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
<h4 style="color:red">${error}</h4>
<center><img src="https://i.pinimg.com/originals/5b/5b/68/5b5b68ddf9de812ffc941238e66049ac.png" width=270 height=210 >
<h1><center style='color:green'>Welcome to Super Market</h1>
<h2 style='color:green'><center>Different Roles to Register:</center></h2>
<h2><form:form action="abc" modelAttribute="user" method="get">
<center><form:select path="userCategory" style='padding: 10px 15px;font-size:20px;background-color:lightblue'>
<form:option value="" label="Select" style='background-color:white' />
<option value="manager" label="Manager" style='background-color:white' />
<option value="cashier" label="Cashier" style='background-color:white'/>
<option value="admin" label="Admin" style='background-color:white'/></h2>
</form:select><br><br>
<input type="submit" name="submit" value="Submit" style='background-color:blue;border: 2px solid #008CBA;padding: 10px 24px;font-size: 16px;margin: 4px 2px;color:white'></center>
</form:form>
</body>
</html>