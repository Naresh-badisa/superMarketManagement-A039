<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
body{
   
   font:bold;
   color:green;
   font-size:20px;
}
</style>
<body>
<center><img src="https://i.pinimg.com/originals/5b/5b/68/5b5b68ddf9de812ffc941238e66049ac.png" width=270 height=210><br>
<h4 style="color:red">${error}</h4>
<center><form:form action="adminLogin" method="post" modelAttribute="user">
User-Id  :   <form:input path="userId" name="name" style='font-size:16px'/><br><br>
Password:<form:password path="password" name="password" style='font-size:16px'/><br><br>
<input type="submit" name="submit" value="Login" style='background-color:green;border-radius: 12px solid #008CBA;padding: 10px 24px;font-size: 16px;margin: 4px 2px;color:white'><br><br>
</form:form>
</body>
</html>