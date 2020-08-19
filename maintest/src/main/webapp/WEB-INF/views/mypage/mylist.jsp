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
	href="${contextPath }/maintest/css/mypage/main.css">
<link rel="stylesheet"
	href="${contextPath }/maintest/css/mypage/common2.css">
<script>
	window.onload = function on() {
		const del_btn = document.querySelectorAll('.mylist_btn');

		for (var i = 0; i < del_btn.length; i++) {
			del_btn[i].addEventListener('click', del_btn_f);
		}
	}

	function del_btn_f(e) {

		if (confirm("정말 삭제하시겠습니까?") == true) {

			var ele = e.target.getAttribute('lnum');
			/* window.location.href = "http://localhost:9080/myweb/member/out.do?id=" */
			var ajax = new XMLHttpRequest();
			ajax.onreadystatechange = function() {
				if (ajax.readyState === ajax.DONE) {
					if (ajax.status === 200 || ajax.status === 201) {
						console.log(ajax.responseText);
					} else {
						console.error(ajax.responseText);
					}
				}
			};
			ajax.open("POST", "/maintest/mypage/del_list"); //
			ajax.send(ele);

			location.reload(true)
		}
	}
</script>
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/layout/haeder.jsp"%>

	<div class="box-container">
		<!-- aside -->
		<%@ include file="/WEB-INF/views/mypage/mypage_sidebar.jsp"%>

		<!-- main -->
		<main id="content" class="mylist">
			<div class="content_sub-title">내 리스트</div>
			<table id="">
				<colgroup>
					<col width="40">
					<col width="200">
					<col width="200">
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
								href="/maintest/mypage/listview/${list.lnum}"
								onclick="window.open(this.href,'','width=1000, height=620, scrollbars=yes'); return false;">${list.lname }</a></td>
							<td>${list.checked_item }/${list.total_item }[
								${list.checked_item/list.total_item*100}% ]</td>
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
					<a href="/maintest/mypage/mylist/${requestScope.paging.prevpage }">이전</a> | </c:if>
				<c:forEach begin="${requestScope.paging.startPage }"
					end="${requestScope.paging.endPage}">
					<span><a href="/maintest/mypage/mylist/${page }">${page }</a></span>
					<c:set var="page" value="${page+1}" />
				</c:forEach>
				<c:if test="${requestScope.paging.next==true }"> | <a
						href="/maintest/mypage/mylist/${requestScope.paging.nextpage }">다음</a>
				</c:if>
			</div>
		</main>
	</div>
	<!-- footer -->
	<footer>footer</footer>
</body>

</html>

