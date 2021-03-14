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
<form:option value="beverages" label="Beverages"/>
<form:option value="dairy" label="Dairy"/>
<form:option value="produce" label="Produce"/>
<form:option value="bakery" label="Bakery"/>
<form:option value="meat" label="Meat"></form:option>
<form:option value="groceries" label="Groceries"></form:option>
<form:option value="frozenfood" label="Frozen-Food"/>
<form:option value="cleaners" label="Cleaners"></form:option>
<form:option value="papergoods" label="PaperGoods"></form:option>
<form:option value="personalcare" label="PersonalCare"></form:option>
<form:option value="others" label="Others"></form:option>
</form:select>
<input type="submit" value="Go">
</form:form>
</body>
</html>