<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>welcomeUser</title>
<style >
h1{
color:#fc03a1;
font:bold;
}

</style>
</head>
<body>
<h1>Welcome to SuperMarket Portal ${name }</h1>

<form action="/logoff">
<div style='align:right'>
<input type="submit" name="submit" value="LOGOFF">
</div>
</form>
</body>
</html>