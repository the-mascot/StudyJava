<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<html>
<head>
	<%
		request.setCharacterEncoding("euc-kr"); 
	%>
	<title>로그인 화면</title>

	<style type="text/css">
		table{
			padding: 60px 0px;
			margin-left:auto; 
			margin-right:auto;
			border:3px solid skyblue;
		}
		
		td{
			border:1px solid skyblue
		}
		
		#title{
			background-color:skyblue
		}
	</style>

	<script type="text/javascript">

		function checkValue()
		{
			inputForm = eval("document.loginInfo");
			if(!inputForm.id.value)
			{
				alert("아이디를 입력하세요");	
				inputForm.id.focus();
				return false;
			}
			if(!inputForm.password.value)
			{
				alert("비밀번호를 입력하세요");	
				inputForm.password.focus();
				return false;
			}
		}
		
		function list() {
			
			location.href="comuBoardList.do"
		}
	
	</script>

</head>
<body>
	<div id="wrap">
	
		<br><br>
		<b><font size="6" color="gray">로그인</font></b>
		<br><br><br>
		<c:if test="${sessionScope.sessionId==null}">
		
		<form name="loginInfo" method="post" action="LoginAction.do" 
				onsubmit="return checkValue()">

			<table>
				<tr>
					<td bgcolor="skyblue">아이디</td>
					<td><input type="text" name="id" maxlength="50"></td>
				</tr>
				<tr>
					<td bgcolor="skyblue">비밀번호</td>
					<td><input type="password" name="password" maxlength="50"></td>
				</tr>
			</table>
			<br>${sessionScope.sessionId}
			<input type="submit" value="로그인"/>
			<input type="button" value="취소" onclick="goFirstForm()"/>
			<a href="comuBoardList.do">비회원</a>
		</form>
		</c:if>
		<c:if test="${sessionScope.sessionId!=null}">
		<br><br><br>
		<font size=6 color="skyblue">${sessionScope.sessionID}</font>
		<font size=6>님 환영합니다.</font><br>
		<a id="logout_button" onclick="logout_button()">로그아웃</a>
		<a href="comuBoardList.do">비회원</a>
		</c:if>
		<c:set var="failMessage" value="${requestScope.fail}"/>
		<c:if test="${failMessage!=null}">	
			<c:choose>
				<c:when test="${failMessage=='0'}">
					<br><font color='red' size='5'>비밀번호를 확인해 주세요.</font>
				</c:when>
				<c:otherwise>
					<br><font color='red' size='5'>아이디를 확인해 주세요.</font>
				</c:otherwise>
			</c:choose>
		</c:if>
	</div>	
</body>
</html>