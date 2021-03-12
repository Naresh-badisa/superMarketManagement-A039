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
<td colspan="2" align="center">ACTION</td>
</tr>


<%
try
{
Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://localhost/supermarket";
String username="root";
String password="Naresh@404";
String query="select * from product";

Connection conn=DriverManager.getConnection(url,username,password);
Statement stmt=conn.createStatement();


ResultSet rs=stmt.executeQuery(query);
while(rs.next())
{

%>
    <tr>
    <td><%=rs.getString("category") %></td>
    <td><input type="text" value="<%=rs.getString("name") %>"></td>
    <td><input type="text" value="<%=rs.getString("manufacturer") %>"></td>
    <td><input type="button" name="UPDATE" value="UPDATE" onclick="
    <% 
    String qmod="update product set name=?,category=? where category=? ";
    PreparedStatement pstmt=conn.prepareStatement(qmod);
    String one=request.getParameter("name");
    String two=request.getParameter("manufacturer");
    String three=request.getParameter("category");
    pstmt.setString(1,one);
    pstmt.setString(2,two);
    pstmt.setString(3,three);
    pstmt.executeUpdate(); 
    %>"></td>
    <td> <input type="button" name="DELETE" value="DELETE"></td>
    </tr>
        <%

}
%>
    </table>
    <%
    rs.close();
    stmt.close();
    conn.close();
    }
    catch(Exception e)
    {
        e.printStackTrace();
        }






%>

</form>
</html>