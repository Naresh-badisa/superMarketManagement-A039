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
<form:form action="updateEditedForm" method="post" modelAttribute="product">
${error}
<table>
<tr><td>Name</td><td><form:input path="name" id="name" pattern="[A-za-z]{}"/></td>
<tr><td>Category</td><td><form:select path="category">
<form:option value="cooldrinks"></form:option>
<form:option value="spreads"></form:option>
<form:option value="dairy"></form:option>
<form:option value="fruit"></form:option>
<form:option value="groceries"></form:option></form:select></td></tr>
<tr><td>Manufacturer</td><td><form:input path="manufacturer" id="manufacturer" pattern="[A-Za-z]{}"/></td></tr>
<tr><td>Quantity</td><td><form:input path="quantity" id="quantity" pattern="[0-9]{}"/></td></tr>
<tr><td>Rate</td><td><form:input path="rate" id="rate" pattern="[0-9]{}"/></td></tr>
<tr><td>Discount</td><td><form:input path="discount" id="discount" pattern="[0-9]{}"/></td></tr>
<tr><td><input type="submit" name="submit" value="Update"/>
</table>

</form:form>
</body>
</html>