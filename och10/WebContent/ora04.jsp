<%@page import="och10.Dept"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="dBError.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	Class.forName(driver);
	Connection conn=DriverManager.getConnection(url, "scott", "tiger");
	
	String deptno=request.getParameter("deptno");
	String sql="select * from dept where deptno="+deptno;
	Statement stmt=conn.createStatement();
	ResultSet rs=stmt.executeQuery(sql);
	// DTO Data Transper object
	Dept dept=new Dept();
	if(rs.next()) {
		dept.setDeptno(rs.getInt(1));
		dept.setDname(rs.getString(2));
		dept.setLoc(rs.getString(3));
		request.setAttribute("dept", dept);
		rs.close();
		stmt.close();
		conn.close();
		RequestDispatcher rd=request.getRequestDispatcher("ora04Result.jsp");
		rd.forward(request, response);
	} else{
		/* out.println("그게 무슨 부서야 없는데"); */
		out.println("없는 부서입니다");
		stmt.close();
		conn.close();
	}

%>
</body>
</html>