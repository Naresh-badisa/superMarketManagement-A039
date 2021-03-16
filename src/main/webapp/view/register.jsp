<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
<style>
body{
   font:bold;
   color:green;
   font-size:20px;
   }
</style>
<script  src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>  
function validate()
    {
      var txtval = $("#firstname").val();
      //Check if value is empty or not
      if (txtval == "") {
           //if empty then assign the border
          $("#firstname").css("border", "2px solid red");
      }
       var txtval = $("#lastname").val();
      //Check if value is empty or not
      if (txtval == "") {
           //if empty then assign the border
          $("#lastname").css("border", "2px solid red");
      }
       var txtval = $("#dob").val();
      if (txtval == "") {
          $("#dob").css("border", "2px solid red");
      }
       var txtval = $("#phonenumber").val();
      if (txtval == "") {
          $("#phonenumber").css("border", "2px solid red");
      }
       var txtval = $("#userid").val();
      if (txtval == "") {
          $("#userid").css("border", "2px solid red");
      }
       var txtval = $("#password").val();
      if (txtval == "") {
          $("#password").css("border", "2px solid red");
      }
       var txtval = $("#email").val();
      if (txtval == "") {
          $("#email").css("border", "2px solid red");
      }
      
    }

 

</script>
</head>
<body>
<center><img src="https://i.pinimg.com/originals/5b/5b/68/5b5b68ddf9de812ffc941238e66049ac.png" width=150 height=150>
<form:form action="/registration" method="post" modelAttribute="user">
<table>
<h4 style="color:red">${error}</h4>
<tr><td>FirstName*:</td><td><form:input type="text" path="firstName" id="firstname" placeholder="Enter First Name" pattern="[A-za-z]+" /></td></tr>
<tr><td>LastName*:</td><td><form:input path="lastName" id="lastname" placeholder="Enter Last Name" pattern="[A-Za-z]+"/></td></tr>
<tr><td>DateOfBirth*:</td><td><form:input path="DateOfBirth" id="dob" placeholder="Enter Your DOB"/></td></tr>
<tr><td>Gender*:</td><td><form:radiobutton path="gender" id="male" value="male"/>Male
<form:radiobutton path="gender" id="female" value="female"/>Female</td></tr>
<tr><td>PhoneNumber*:</td><td><form:input path="contactNumber" id="phonenumber"  placeholder="Enter your 10 Digit number" pattern="[9-6]{1}[0-9]{9}" /></td></tr>
<tr><td>UserId*:</td><td><form:input path="userId" id="userid" placeholder="Enter User id"/></td></tr>
<tr><td>Password*:</td><td><form:password path="password" id="password" placeholder="Enter password"/></td></tr>
<tr><td>Email*:</td><td><form:input path="email" id="email" placeholder="Enter E-mail" pattern="[^@\s]+@[^@\s]+\.[^@\s]+"/></td></tr>
<tr><td>User Category*:</td><td><form:select path="userCategory" style='padding: 5px 5px;font-size:15px'>
<form:option value="" label="Select"/>
<form:option value="manager" label="Manager"/>
<form:option value="cashier" label="Cashier"/>
</form:select></td></tr>
<tr><td>Status:</td><td><form:radiobutton path="status" id="status" value="inactive"/>Inactive
<center><tr><td><input type="submit" name="submit" value="Register" style='background-color:green;align:center;border-radius: 12px solid #008CBA;padding: 10px 20px;font-size: 16px;margin: 4px 2px;color:white' onclick="validate()"></td></tr>
</table>
</form:form>
</body>
</html>