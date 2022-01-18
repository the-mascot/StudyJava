<%@page import="och12.MemberDao"%>
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
	String id=request.getParameter("id");
	String passwd=request.getParameter("passwd");
	MemberDao md=MemberDao.getInstance();
	int result=md.check(id, passwd);
	// id와 passwd가 맞으면 1
	// id 맞고 passwd 틀리면 0
	// 둘다 틀리면 -1
	if(result==1) {
		session.setAttribute("id", id);
		response.sendRedirect("main.jsp");
	} else if(result==0) {
%>	
	<script type="text/javascript">
		alert("암호 몰라 !!");
		history.go(-1);
	</script>	
<%	} else { %>
	<script type="text/javascript">
		alert("넌 누구냐 수상한데 !");
		history.go(-1);
	</script>
<%	}	%>
</body>
</html>