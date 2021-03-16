<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center><img src="https://i.pinimg.com/originals/5b/5b/68/5b5b68ddf9de812ffc941238e66049ac.png" width=150 height=150>
<form:form action="/customerRegistration" method="post" modelAttribute="customer">
<table>
<h4 style="color:red">${error}</h4>
<tr><td>FirstName*:</td><td><form:input type="text" path="firstName" id="firstname" placeholder="Enter First Name" pattern="[A-za-z]+" /></td></tr>
<tr><td>LastName*:</td><td><form:input path="lastName" id="lastname" placeholder="Enter Last Name" pattern="[A-Za-z]+"/></td></tr>
<tr><td>DateOfBirth*:</td><td><form:input path="DateOfBirth" id="dob" placeholder="Enter Your DOB"/></td></tr>
<tr><td>Gender*:</td><td><form:radiobutton path="gender" id="male" value="male"/>Male
<form:radiobutton path="gender" id="female" value="female"/>Female</td></tr>
<tr><td>PhoneNumber*:</td><td><form:input path="contactNumber" id="phonenumber"  placeholder="Enter your 10 Digit number" pattern="[9-6]{1}[0-9]{9}" /></td></tr>
<tr><td>Email*:</td><td><form:input path="email" id="email" placeholder="Enter E-mail" pattern="[^@\s]+@[^@\s]+\.[^@\s]+"/></td></tr>
<tr><td><input type="submit" name="submit" value="Submit"></td></tr>
</table>
</form:form>
</body>
</html>