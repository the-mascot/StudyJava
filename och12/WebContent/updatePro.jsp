<%@page import="och12.MemberDao"%>
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
<jsp:useBean id="member" class="och12.Member"/>
<jsp:setProperty property="*" name="member"/>
<%
	MemberDao md=MemberDao.getInstance();
	int result=md.update(member);
	if(result>0) {		
%>
	<script type="text/javascript">
		alert("사용자 정보 수정 완료");
		location.href="main.jsp";
	</script>
<% 	} else { %>
	<script type="text/javascript">
		alert("뭐야 똑바로 하시오");
		history.back();
	</script>
<%	} %>
</body>
</html>