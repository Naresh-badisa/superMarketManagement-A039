<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
</head>
<body>
<form:form action="/registration" method="post" modelAttribute="user">
<table>
<tr><td>FirstName:</td><td><form:input path="firstName" id="firstname"/></td></tr>
<tr><td>LastName:</td><td><form:input path="lastName" id="lastname"/></td></tr>
<tr><td>DateOfBirth:</td><td><form:input path="DateOfBirth" id="dob"/></td></tr>
<tr><td>Gender:</td><td><form:radiobutton path="gender" value="male"/>Male
<form:radiobutton path="gender" value="female"/>Female</td></tr>
<tr><td>PhoneNumber:</td><td><form:input path="contactNumber" id="phonenumber"/></td></tr>
<tr><td>UserId:</td><td><form:input path="userId" id="userid"/></td></tr>
<tr><td>Password:</td><td><form:password path="password" id="password"/></td></tr>
<tr><td>Email:</td><td><form:input path="email" id="email"/></td></tr>
</table>
<tr><td><input type="submit" name="submit" value="Register"></td></tr>
</form:form>
</body>
</html>