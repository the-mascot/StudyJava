<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	int deptno=Integer.parseInt(request.getParameter("deptno"));
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	Class.forName(driver);
	Connection conn=DriverManager.getConnection(url, "scott", "tiger");
	
	
	String sql=String.format("select * from dept where deptno="+deptno);
	Statement stmt=conn.createStatement();
	ResultSet rs=stmt.executeQuery(sql);
	if(rs.next()) {
		request.setAttribute("deptno", deptno);
		request.setAttribute("dname", rs.getString(2));
		request.setAttribute("loc", rs.getString(3));
		rs.close();
		stmt.close();
		conn.close();
		RequestDispatcher rd=request.getRequestDispatcher("oraResult.jsp");
		rd.forward(request, response);
	}
	rs.close();
	stmt.close();
	conn.close();
%>
	<script type="text/javascript">
		alert("없는 부서입니다");
		location.href="oraUpdate.html";
	</script>
</body>
</html>