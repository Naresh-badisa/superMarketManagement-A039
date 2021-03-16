<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*" %>    

 

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
<td>Cost</td>
<td>Discount</td>
<td>Action</td>
</tr>

 


<%
try
{
Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://localhost/supermarket";
String username="root";
String password="Naresh@404";
String query="select * from cart";

 

Connection conn=DriverManager.getConnection(url,username,password);
PreparedStatement pstmt=conn.prepareStatement(query);
ResultSet rs=pstmt.executeQuery();
while(rs.next())
{

 

%>
    <tr>
    <td><%=rs.getString("name") %></td>
    <td><%=rs.getString("category") %></td>
     <td><%=rs.getString("manufacturer") %></td>
    <td><%=rs.getString("quantity") %></td>
    <td><%=rs.getString("rate") %></td>
     <td><%=rs.getString("discount") %></td>
     <td><a href="deleteFromCart?name=<%=rs.getString("name") %>">Delete</a></td>
    </tr>
        <%
}
%>
    </table>
    <a href="calculatebill">Calculate-Bill</a>
    <%
    rs.close();
    conn.close();
    }
    catch(Exception e)
    {
        e.printStackTrace();
        }
%>

 

</form>
</html>