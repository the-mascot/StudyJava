<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>headNav</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
</head>
<body>
	<nav class="navbar navbar-expand-lg">
		<div class="container-fluid">
			<div class="collapse navbar-collapse justify-content-md-center">
				<ul class="navbar-nav">
					<!-- href 설정 -->
					<li class="nav-item px-5"><a class="head_nav nav-link"	
						href="#">공지사항</a></li>
					<li class="nav-item px-5"><a class="head_nav nav-link"
						href="../sh/comuBoardList.do">커뮤니티</a></li>
					<li class="nav-item px-5"><a class="head_nav nav-link"
						href="../gm/newsindex.do">취업기사</a></li>
				<c:choose>
				<c:when test="${sessionScope.sessionID=='admin' }">
						<li class="nav-item px-5"><a class="head_nav nav-link"
						href="../sw/mngMemList.do">관리자페이지</a></li>
				</c:when>
				<c:when test="${sessionScope.sessionID==null }">
						<li class="nav-item px-5"><a class="head_nav nav-link"
						href="#">마이페이지</a></li>
				</c:when>
				<c:otherwise>
						<li class="nav-item px-5"><a class="head_nav nav-link"
						href="../di/mypageCheck.jsp">마이페이지</a></li>
				</c:otherwise>
				</c:choose>
					<li class="nav-item px-5"><a class="head_nav nav-link"
						href="#">문의사항</a></li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>