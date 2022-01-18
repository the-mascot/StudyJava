<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>

<h2>게시판</h2>
<table>
	<thead>
		<tr>
			<th>글 번호</th>
			<th>제목</th>
			<th>ID</th>
			<th>작성일</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${totCnt > 0}">
			<c:forEach var="board" items="${boardList}">
				<tr>
					<td>${board.rn}</td>
					<td><a href="/Content.do?b_num=${board.b_num}">${board.title}</a></td>
					<td>${board.nickname}</td>
					<td>${board.date}</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${totCnt <= 0}">
			<tr>
				<td colspan="4">데이터가 없습니다.</td>
			</tr>
		</c:if>
	</tbody>
</table>	
	
</body>
</html>