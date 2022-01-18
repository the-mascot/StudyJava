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
	String name=request.getParameter("name");
	String gender=request.getParameter("gender");
	String genderName="";
	if(gender.equals("1"))
		genderName="남자";
	else
		genderName="여자";
	
	int age=Integer.parseInt(request.getParameter("age"));
	age++;
%>
<jsp:useBean id="person" class="och08.Person" scope="request"/>

<jsp:setProperty property="name" name="person" value="<%=name %>"/>
<jsp:setProperty property="gender" name="person" value="<%=genderName %>"/>
<jsp:setProperty property="age" name="person" value="<%=age %>"/>

<jsp:forward page="personResult.jsp"/>
</body>
</html>