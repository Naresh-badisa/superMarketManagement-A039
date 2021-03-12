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
<h1>welcome to product update page</h1>
<h2>select any one of Category:</h2>
<form:form action="/categoryselection" modelAttribute="product" method="get">
<form:select path="category">
<form:option value="cooldrinks" label="CoolDrinks"/>
<form:option value="dairy" label="Dairy"/>
<form:option value="fruit" label="Fruit"/>
<form:option value="spreads" label="Spreads"/>
</form:select>
<input type="submit" value="Go">
</form:form>
</body>
</html>