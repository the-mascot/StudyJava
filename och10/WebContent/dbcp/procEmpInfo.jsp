<%@page import="java.sql.Types"%>
<%@page import="java.sql.CallableStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar"%>
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
	int empno=Integer.parseInt(request.getParameter("empno"));
	System.out.println("empno->"+empno);
	Context ctx=new InitialContext();
	DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
	Connection conn=ds.getConnection();
	String sql="{call EMP_INFO3(?, ?)";
	CallableStatement cs=conn.prepareCall(sql);
	cs.registerOutParameter(2, Types.DOUBLE);
	cs.setInt(1, empno);
	cs.execute();
	out.println("급여 : "+cs.getDouble(2));
	cs.close();
	conn.close();
%>
</body>
</html>