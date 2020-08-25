<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<style>
html {
	font-family: Tahoma, Gulim, '굴림';
	font-size: 12px;
	line-height: 1.3;
	color: #333333;
	-webkit-text-size-adjust: 100%;
	-ms-text-size-adjust: 100%;
}

thead {
	background: #EFEFEF;
	text-align: center;
	font-size: 15px;
}

thead>tr>th {
	border-bottom: 2px solid #cbcbcb;
	border-top: 2px solid #cbcbcb;
}

#member_table {
	width: 700px;
	border-collapse: collapse;
}

#content {
	width: 700px;
}

#box1 {
	display: flex;
	align-items: center;
	justify-content: space-between;
}

#box2 {
	width: 370px;
	display: flex;
	align-items: center;
	justify-content: space-around;
}

#searchType {
	width: 85px;
	height: 22px;
}

#member_exit {
	width: 100px;
	display: inline-block;
}

#member_exit>input {
	width: 100px;
}

#page_num {
	margin-top: 20px;
	text-align: center;
}

#page_num>span {
	border: 1px solid black;
}

.checkbox {
	text-align: left;
}

.id, .nickname {
	text-align: left;
}

tbody td {
	text-align: center;
}

tbody td, tbody th {
	border-bottom: 1px #E0E0E0 solid;
}
</style>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/admin_main.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(document).ready(function() {
		$("#allselect").change(allselect);
		$("#exitBtn").click(member_exit)
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

	function member_exit(e) {	
		
		var delete_list = new Array;

		for (var i = 0; i < $(".check").length; i++) {
			if ($(".check")[i].checked == true) {
				delete_list.push($(".check")[i].getAttribute('ucode'));
			}
		}

		console.log(delete_list);

		if (delete_list.length == 0) {
			alert("탈퇴시킬 회원을 선택해주세요.")
			return;
		}

		if(confirm("선택한 회원을 탈퇴시키겠습니까?") == false){
			return;}

		//ajax 등록	
		const xhttp = new XMLHttpRequest();

		xhttp.addEventListener("readystatechange", ajaxCall);
		function ajaxCall(event) {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				alert("선택한 회원을 탈퇴시켰습니다..");
				location.reload(true);
			}
		}

		const result = JSON.stringify(delete_list);

		console.log(result)
		//서비스요청1
		xhttp.open("POST", "${pageContext.request.contextPath}/admin/exit", );

		xhttp
				.setRequestHeader("Content-Type",
						"application/json;charset=utf-8");

		xhttp.send(result);

	}
</script>
<script type="text/javascript">
	window.onload = function() {
		document.getElementById('serchBtn').addEventListener('click', serch)
	}

	function serch() {
		var searchType = document.getElementById('searchType').value;
		var keyword = document.getElementById('keyword').value;

		if (keyword.trim().length != 0) {

			location.href = "${pageContext.request.contextPath}/admin/member/1/" + searchType + "/"
					+ keyword;

		} else {
			alert('검색어를 입력해주세요.')
		}
	}
</script>
</head>

<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/layout/haeder.jsp"%>

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
					<col width="23">
					<col width="100">
					<col width="150">
					<col width="60">
					<col width="100">
					<col width="120">
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
						href="/${pageContext.request.contextPath}/admin/member/${requestScope.paging.prevpage }<c:if test="${not empty requestScope.searchType}">/${requestScope.searchType }/${requestScope.keyword }</c:if>">이전</a> | </c:if>
				<c:forEach begin="${requestScope.paging.startPage }"
					end="${requestScope.paging.endPage}">
					<span><a
						href="/${pageContext.request.contextPath}/admin/member/${page }<c:if test="${not empty requestScope.searchType}">/${requestScope.searchType }/${requestScope.keyword }</c:if>
				">${page }</a></span>
					<c:set var="page" value="${page+1}" />
				</c:forEach>
				<c:if test="${requestScope.paging.next==true }"> | <a
						href="/${pageContext.request.contextPath}/mypage/mylist/${requestScope.paging.nextpage }<c:if test="${not empty requestScope.searchType}">/${requestScope.searchType }/${requestScope.keyword }</c:if>">다음</a>
				</c:if>
			</div>
		</main>
	</div>
</body>
</html>