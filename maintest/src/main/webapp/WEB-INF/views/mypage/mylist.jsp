<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script defer
	src="${pageContext.request.contextPath}/js/mypage/mylist.js">
	
</script>
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/layout/uppermost.jsp"%>

	<div class="box-container">
		<!-- aside -->
		<%@ include file="/WEB-INF/views/mypage/mypage_sidebar.jsp"%>

		<!-- main -->
		<main id="content" class="mylist">
			<div class="content_sub-title">내 리스트</div>
			<table id="">
				<colgroup>
					<col width="50">
					<col width="200">
					<col width="150">
					<col width="100">
					<col width="100">
				</colgroup>
				<thead id="">
					<tr>
						<th></th>
						<th>리스트 이름</th>
						<th>진행률</th>
						<th>작성일</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${requestScope.mylist }">
						<tr>
							<td>${list.num}</td>
							<td class="title"><a
								href="${pageContext.request.contextPath}/mypage/listview/${list.lnum}"
								onclick="window.open(this.href,'','width=1000, height=620, scrollbars=yes'); return false;">${list.lname }</a></td>
							<td>
							<c:if test="${list.total_item eq 0 }">0/0[ 0% ]</c:if>
							<c:if test="${list.total_item ne 0 }">
							${list.checked_item }/${list.total_item }[							
								<fmt:formatNumber value="${list.checked_item/list.total_item}"
									type="percent" maxFractionDigits="1"/>
								]</c:if>
								</td>
								
							<td><fmt:formatDate value="${list.cdate }"
									pattern="yyyy/MM/dd" /></td>
							<td><input type="button" class="mylist_btn"
								id="mylist_delete_btn" lnum="${list.lnum}" value="삭제"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div id="page_num">
				<c:set var="page" value="${requestScope.paging.startPage }" />
				<c:if test="${requestScope.paging.prev==true }">
					<a
						href="${pageContext.request.contextPath}/mypage/mylist/${requestScope.paging.prevpage }">이전</a> | </c:if>
				<c:forEach begin="${requestScope.paging.startPage }"
					end="${requestScope.paging.endPage}">
					<span><a
						href="${pageContext.request.contextPath}/mypage/mylist/${page }">${page }</a></span>
					<c:set var="page" value="${page+1}" />
				</c:forEach>
				<c:if test="${requestScope.paging.next==true }"> | <a
						href="${pageContext.request.contextPath}/mypage/mylist/${requestScope.paging.nextpage }">다음</a>
				</c:if>
			</div>
		</main>
	</div>
</body>

</html>

