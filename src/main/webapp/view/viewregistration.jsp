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
 <nav>
        <a href="/logoff">Logoff</a>
    </nav>
</body><center>
<form method="get">

<table border="7">
<tr>
<td>FirstName</td>
<td>LastName</td>
<td>UserId</td>
<td>Role</td>
<td colspan="2" align="center">ACTION</td>
</tr>


<%
try
{
Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://localhost/supermarket";
String username="root";
String password="Naresh@404";
String query="select * from user where status=?";

Connection conn=DriverManager.getConnection(url,username,password);
PreparedStatement pstmt=conn.prepareStatement(query);
pstmt.setString(1,"inactive");
ResultSet rs=pstmt.executeQuery();
while(rs.next())
{

%>
    <tr>
    <td><%=rs.getString("firstname") %></td>
    <td><%=rs.getString("lastname") %></td>
    <td><%=rs.getString("userid") %></td>
    <td><%=rs.getString("usercategory") %></td>
    <td><a href="update?userid=<%=rs.getString("userid") %>">Accept</a></td>
    <td><a href="delete?userid='<%=rs.getString("userid") %>'">decline</a></td>
    
    </tr>
        <%

}
%>
    </table>
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
</center>
</html>