<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="date" value="<%=new Date() %>"/>
날짜 : <fmt:formatDate value="${date }" type="date" pattern="yyyy-MM-dd (E)" /><p>
시간 :  <fmt:formatDate value="${date }" type="time" pattern="hh:mm:ss (a)"/><p>
</body>
</html>