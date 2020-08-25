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
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(document).ready(function() {
		$("#allselect").change(allselect);
		$("#mypost_delete_btn").click(post_delete)
	});

	function allselect(e) {
		if (e.target.checked == true) {
			$(".check").prop("checked", true);
			console.log("체크됨");
		} else {
			$(".check").prop("checked", false);
			console.log('해제됨');
			;
		}
	}

	function post_delete(e) {
		var del_list = '';

		for (var i = 0; i < $(".check").length; i++) {

			if ($(".check")[i].checked == true) {
				del_list = del_list + $(".check")[i].getAttribute('bnum')
						+ ' / ';
			}
		}

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
		ajax.open("POST",
				"/${pageContext.request.contextPath}/mypage/del_post", false); //
		ajax.send(del_list);

		location.reload(true)

	}
</script>
<style type="text/css">
#mypost_delete {
	display: inline;
	float: right;
}

.actionPost {
	margin-top: 10px;
	display: flex;
	align-items: center;
	justify-content: space-between;
}

.checkbox {
	display: flex;
	align-items: center;
}

#mypost_delete>input {
	width: 120px;
	text-align: center;
	padding: 0;
}
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
					<input type="checkbox" name="" id="allselect"> 전체선택
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
	<!-- footer -->
	<footer>footer</footer>
</body>
</html>