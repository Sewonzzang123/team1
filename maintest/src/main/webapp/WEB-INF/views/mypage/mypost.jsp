<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mypage/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mypage/common2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mypage/mypost.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script defer
	src="${pageContext.request.contextPath}/js/mypage/mypost.js">
	
</script>
<style type="text/css">
</style>
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/layout/uppermost.jsp"%>

	<div class="box-container">
		<!-- aside -->
		<%@ include file="/WEB-INF/views/mypage/mypage_sidebar.jsp"%>

		<!-- main -->
		<main id="content" class="mypost">
			<div class="content_sub-title">내가 쓴 글</div>

			<table id="member_table">
				<colgroup>
					<col style="width: 23px;">
					<col style="width: 100px;">
					<col style="width: 300px;">
					<col style="width: 100px;">
					<col style="width: 50px;">
				</colgroup>
				<thead id="member_table_header">
					<tr>
						<th></th>
						<th>게시판</th>
						<th>제목</th>
						<th>작성일</th>
						<th>조회</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="post" items="${requestScope.mypost }">

						<tr>
							<td><input type="checkbox" class="check"
								bnum="${post.bnum }"></td>
							<td class="catname">${post.bcategory.catname }</td>
							<td class="title">${post.btitle }</td>
							<td><fmt:formatDate value="${post.bcdate }"
									pattern="yyyy/MM/dd" /></td>
							<td>${post.bhits }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div class="actionPost">
				<div class="checkbox">
					<input type="checkbox" name="" id="allselect"><span>
						전체선택</span>
				</div>
				<div id="mypost_delete">
					<input id="mypost_delete_btn" type="button" value="삭제">
				</div>
			</div>

			<div id="page_num">
				<c:set var="page" value="${requestScope.paging.startPage }" />
				<c:if test="${requestScope.paging.prev==true }">
					<a
						href="${pageContext.request.contextPath}/mypage/mylist/${requestScope.paging.prevpage }">이전</a> | </c:if>
				<c:forEach begin="${requestScope.paging.startPage }"
					end="${requestScope.paging.endPage}">
					<span><a
						href="${pageContext.request.contextPath}/mypage/mypost/${page }">${page }</a></span>
					<c:set var="page" value="${page+1}" />
				</c:forEach>
				<c:if test="${requestScope.paging.next==true }"> | <a
						href="${pageContext.request.contextPath}/mypage/mypost/${requestScope.paging.nextpage }">다음</a>
				</c:if>
			</div>

		</main>
	</div>
</body>
</html>