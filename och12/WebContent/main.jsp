 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="memberCheck.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=id %>님 환영합니다<p>
<table bgcolor="pink" border="1" width="80%">
	<tr><td><a href="logOut.jsp">로그아웃</a></td></tr>
	<tr><td><a href="joinForm.jsp">회원가입</a></td></tr>
	<tr><td><a href="memberList.jsp">회원명단</a></td></tr>
	<tr><td><a href="updateForm.jsp">회원정보수정</a></td></tr>
	<tr><td><a href="deleteForm.jsp">회원탈퇴</a></td></tr>
</table>
</body>
</html>