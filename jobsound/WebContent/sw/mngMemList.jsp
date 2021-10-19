<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html>
<html>

<%
	String context = request.getContextPath();
%>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
        $("#viewCount").change(function() {
           var pageNum="${pageNum}";
           var pageSize=$(this).val();
           location.href="<%=context%>/sw/mngMemList.do?pageSize="+pageSize+"&pageNum="+pageNum;
       });
   });
</script>

<script type="text/javascript">
function chk() {
    if(!frm.keyWord.value) {
       alert("검색어를 입력하세요.");
       frm.keyWord.focus();
       return false;
    }
    var blank_pattern = /^\s+|\s+$/g;
    if(frm.keyWord.value.replace( blank_pattern, '' ) == "" ){
        alert("공백만 입력되었습니다.");
        return false;
    }
    return true;  
}
</script>

</head>
<body>

<!-- header 부분 -->
<%@include file ="../common/header.jsp"%>

<!-- 상단 navbar 영역 -->
<%@include file ="../common/headNav.jsp" %>

<!-- main 영역 : main 안에 left_side - section - right_side로 3등분 됨 2:6:2 비율 -->
<main>
<!-- left side 영역 -->
<%@include file ="../common/mngleftSide.jsp" %>


<!-- section 영역 -->
<section>

<!-- 게시판 설명 -->
	<div>
		<div>
			<h2 class="mt-5 mb-3 mx-5 fw-bold" style="font-family: pretend; color: #5798D7">회원 관리 페이지</h2>
		</div>
		<div>
			<p class="mt-2 mx-5 fs-6 text-muted fst-italic" style="font-family: pretend">- 관리자는 '회원 목록'을 조회, 검색 할 수 있습니다.</p>
		</div>
	</div>
	
	
<!-- 	<div class="board_search_parent">
		<div class="board_search d-flex">
	
		<div>
			<input type="text" class="form-control" placeholder="Search" maxlength="255">
		</div>
		<div>
			<input type="button" class="board_search_button btn" value="검 색" style="background-color: #5798D7; margin-left: 5px;">
		</div>
		
	</div>
</div> -->

<!--    게시판 내 검색 -->
   <div class="board_search_parent">
   <div class="board_search d-flex">
      <form action="<%=context%>/sw/mngMemSearch.do?pageSize=${pageSize}" id="frm" onsubmit="return chk()">
         <div class="board_search d-flex">
            <div>
            <input type="text" class="form-control" name="keyWord" placeholder="Search" maxlength="255">
            </div>
            <div>
            <input type="submit" class="board_search_button btn" value="검 색" style="margin-left: 5px;">
            </div>
            </div>
      </form>
   </div>
</div>



<!-- 10개씩 보기 -->
<div class="view_count_parent">
    <select class="view_count form-select" id="viewCount" name="viewCount">
        <option value="10" <c:if test="${pageSize=='10'}">selected='selected'</c:if>>10개씩</option>
        <option value="15" <c:if test="${pageSize=='15'}">selected='selected'</c:if>>15개씩</option>
        <option value="20" <c:if test="${pageSize=='20'}">selected='selected'</c:if>>20개씩</option>
        <option value="30" <c:if test="${pageSize=='30'}">selected='selected'</c:if>>30개씩</option>
     </select>
</div>   

	<div class="mx-auto my-5">
	
	<table class="board_table table mx-auto table-hover table-responsive text-center">
		<!-- <thead style="background-color: rgba(13, 110, 253, 0.1)"> -->
		<tr style="background-color: rgba(13, 110, 253, 0.1)">
			<th>아이디</th>
			<th>이름</th>
			<th>가입일자</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>회원정보</th>
		</tr>

		<c:if test="${totCnt>0}">
			<c:forEach var="MemberInfo" items="${list}">
			
			    <c:set var="tel" value="${MemberInfo.tel}"/>
				<tr>
					<td>${MemberInfo.id}</td>
					<td>${MemberInfo.name}</td>
					<td>${MemberInfo.regdate}</td>
					<td>${fn:substring(tel,0,3)}-${fn:substring(tel,3,7)}-${fn:substring(tel,7,11)}</td>
					<td>${MemberInfo.email}</td>
					<td><input type="button" value="자세히 보기" onclick="location.href='<%=context%>/sw/mngMemListChk.do?id=${MemberInfo.id}'"></td>
				</tr>
				<c:set var="startNum" value="${startNum-1}"/>
			</c:forEach>
		</c:if>
		
		<c:if test="${totCnt==0}">
			<tr>
				<td colspan=7>데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>
		
	</table>
	</div>
	
	<div style="text-align: center;">
		<c:if test='${startPage > blockSize}'>
			<a href='<%=context%>/sw/mngMemList.do?pageNum=${startPage-blockSize}'>[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<a href='<%=context%>/sw/mngMemList.do?pageNum=${i}'>[${i}]</a>
		</c:forEach>
		<c:if test="${endPage < pageCnt }">
			<a href='<%=context%>/sw/mngMemList.do?pageNum=${startPage+blockSize}'>[다음]</a>
		</c:if>
	</div>
</section>

<!-- right side 영역 -->
<c:choose>
   <c:when test="${sessionScope.sessionID==null }">
      <%@include file ="../common/guestForm.jsp" %>
   </c:when>
   <c:otherwise>
      <%@include file ="../common/loginForm.jsp" %>
   </c:otherwise>
</c:choose>
</main>

<!-- footer 영역 -->
<%@include file ="../common/footer.jsp" %>

</body>
</html>