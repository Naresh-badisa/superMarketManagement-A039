<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
<form method="post">

	<table border="7">
		<tr>
			<td>Name</td>
			<td>Category</td>
			<td>Manufacturer</td>
			<td>Quantity</td>
			<td>Rate</td>
			<td>Discount</td>
			<td colspan="2" align="center">ACTION</td>
		</tr>


		<%
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/supermarket";
			String username = "root";
			String password = "Naresh@404";
			String query = "select * from product where category=?";

			Connection conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "dairy");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
		%>
		<tr>
			<td><%=rs.getString("name")%></td>
			<td><%=rs.getString("category")%></td>
			<td><%=rs.getString("manufacturer")%></td>
			<td><%=rs.getString("quantity")%></td>
			<td><%=rs.getString("rate")%></td>
			<td><%=rs.getString("discount")%></td>
			<td><a href="edit?name=<%=rs.getString("name")%>">Edit</a></td>
			<td><a href="deleteproduct?name=<%=rs.getString("name")%>">Delete</a></td>
		</tr>
		<%
		}
		%>
	</table>
	 <a href="addproduct">Add Item</a>
	<%
	rs.close();
	conn.close();
	} catch (Exception e) {
	e.printStackTrace();
	}
	%>

</form>
</html>