<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login page</title>
</head>
<body>
${error}
<form:form action="postLogin" method="post" modelAttribute="user">
UserId:<form:input path="userId"/><br>
Password:<form:password path="password" /><br>
<input type="submit" name="submit" value="Login"><br><br>
New User:<a href="register">sign up</a>
</form:form>
</body>
</html>