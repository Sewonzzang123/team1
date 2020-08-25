<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원 관리</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/admin_main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/admin_member.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script defer
	src="${pageContext.request.contextPath}/js/admin/admin_member.js"></script>
</head>

<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/layout/uppermost.jsp"%>

	<div class="box-container">
		<!-- aside -->
		<%@ include file="/WEB-INF/views/admin/admin_sidebar.jsp"%>

		<!-- main -->

		<main id="content" class="admin_item">
			<div class="content_sub-title">회원 관리</div>
			<div id="adminMember_action">
				<div id="box1">
					<div id="box2">
						회원 검색 <select name="searchType" id="searchType">
							<option value="id">아이디</option>
							<option value="name">이름</option>
							<option value="nickname">닉네임</option>
						</select> <input type="text" name="keyword" id="keyword"> <input
							type="button" id="serchBtn" value="검색">
					</div>
					<div id="member_exit">
						<input type="button" value="강제 탈퇴" id="exitBtn">
					</div>
				</div>
			</div>
			<br>
			<table id="member_table">
				<colgroup>
					<col style="width: 23px;">
					<col style="width: 100px;">
					<col style="width: 150px;">
					<col style="width: 60px;">
					<col style="width: 100px;">
					<col style="width: 120px;">
				</colgroup>
				<thead id="member_table_header">
					<tr>
						<th class="checkbox"><input id="allselect" type="checkbox"></th>
						<th>닉네임</th>
						<th>아이디</th>
						<th>이름</th>
						<th>전화번호</th>
						<th>가입일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="member" items="${requestScope.memberlist }">
						<tr>
							<td><input type="checkbox" class="check"
								ucode="${member.ucode }"></td>
							<td class="nickname">${member.nickname }</td>
							<td class="id">${member.id }</td>
							<td class="id">${member.name }</td>
							<td class="tel">${member.tel }</td>
							<td class="cdate"><fmt:formatDate value="${member.cdate }"
									pattern="yyyy/MM/dd" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div id="page_num">


				<c:set var="page" value="${requestScope.paging.startPage }" />
				<c:if test="${requestScope.paging.prev==true }">
					<a
						href="${pageContext.request.contextPath}/admin/member/${requestScope.paging.prevpage }<c:if test="${not empty requestScope.searchType}">/${requestScope.searchType }/${requestScope.keyword }</c:if>">이전</a> | </c:if>
				<c:forEach begin="${requestScope.paging.startPage }"
					end="${requestScope.paging.endPage}">
					<span><a
						href="${pageContext.request.contextPath}/admin/member/${page }<c:if test="${not empty requestScope.searchType}">/${requestScope.searchType }/${requestScope.keyword }</c:if>
				">${page }</a></span>
					<c:set var="page" value="${page+1}" />
				</c:forEach>
				<c:if test="${requestScope.paging.next==true }"> | <a
						href="${pageContext.request.contextPath}/mypage/mylist/${requestScope.paging.nextpage }<c:if test="${not empty requestScope.searchType}">/${requestScope.searchType }/${requestScope.keyword }</c:if>">다음</a>
				</c:if>
			</div>
		</main>
	</div>
</body>
</html>