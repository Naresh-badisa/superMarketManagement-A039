<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<h1><center>Welcome to Super Market</center></h1>
<h4>Different Roles to Register:</h4>
<form action="login">
<center><select name="user">
<option>select</option>
<option value="manager">Manager</option>
<option value="cashier">Cashier</option>
</select><br><br>
<input type="submit" name="submit" value="Submit"></center>
</form>
</body>
</html>