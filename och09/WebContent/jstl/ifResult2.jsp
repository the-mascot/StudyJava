<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
첫번쨰 수 : ${param.num1 }<p>
두번쨰 수 : ${param.num2 }<p>
	<!-- 	문자열을 비교함 -->
큰수는 : <c:if test="${param.num1>=param.num2 }">
			${param.num1 }
		</c:if>
		<c:if test="${param.num1<param.num2 }">
			${param.num2 }
		</c:if>

</body>
</html>