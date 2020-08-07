<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common_taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>





<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<style>
ul {
	padding: 0;
	margin: 0;
}

li {
	list-style: none;
}

.b_sidebar .b_listSet {
	width: 180px;
	height: 600px;
	overflow-y: scroll;
}

.b_sidebar button {
	width: 150px;
	height: 35px;
	text-align: left;
	padding-left: 20px;
	background-color: white;
	border: 0;
	outline: 0;
}

.b_listBtn input {
	width: 90px;
}

.b_container {
	width: 600px;
	border: 1px solid black;
	border-collapse: collapse;
	display: flex;
}

.b_sidebar {
	border-right: 1px solid black;
	width: 180px;
}

.b_content {
	/* border: 1px solid black; */
	width: 420px;
}

.b_content table {
	width: 380px;
	margin: 20px;
	border-collapse: collapse;
}

.b_content th, .b_content td {
	border-bottom: 1px black solid;
	padding: 15px 0 15px 15px;
}

.b_content td select {
	width: 200px;
	height: 20px;
}

.b_content th {
	text-align: left;
	vertical-align: top;
}

.b_type_value {
	text-align: center;
}

.b_content tr {
	margin: 20px;
}

#b_title_input {
	height: 25px;
	width: 250px;
}

#b_memo_input {
	height: 20px;
	width: 250px;
}

.b_subject_chd {
	margin-left: 5px;
	margin-top: 10px;
	width: 200px;
	border-bottom: 1px solid #979797;
}

.b_subject_chd>span {
	margin-bottom: 5px;
	display: inline-block;
	width: 150px;
	font-size: 12px;
	;
}

.actionBtn {
	width: 420px;
	margin-top: 20px;
	text-align: center;
}

.actionBtn input {
	display: inline-block;
	height: 32px;
	width: 80px;
	background: url(https://cafe.pstatic.net/img/manage/sp_btn_141125.gif)
		no-repeat 100% -86px;
	font-size: 12px;
	line-height: 34px;
	color: #fff;
	text-align: center;
	cursor: pointer;
	letter-spacing: -1px;
	font-weight: bold;
	font-family: '돋움', Dotum, Helvetica, sans-serif;
	border: 0;
	outline: 0;
}

.hide {
	display: none;
}

.selected {
	color: red;
	font-weight: bold;
}

#delBoard {
	display: flex;
}
</style>
</head>

<body>

	<main class="content" id="adminBoard">
		<div class="b_container">
			<div class="b_sidebar">
				<div class="b_listSet">
					<ul id="b_list">
						<c:forEach var="bcate" items="${requestScope.bcategoryVO }">
							<button value="${bcate.catnum }" class="b_title_btn"
								onclick="b_select(this)">${bcate.catname }
						</c:forEach>
					</ul>
				</div>
				<div class="b_listBtn">
					<form:form id='delBoard'>
						<input name="catnum" id='catnum' type="hidden">
						<input type="submit" onclick="addMenu()" value="추가"
							formaction=" 	${contextPath}/admin/createBoard">
						<input type="submit" onclick="delMenu()" value="제거"
							formaction=" 	${contextPath}/admin/delBoard">
					</form:form>
				</div>
			</div>

			<div class="b_content" id="b_content">
				<c:forEach var="bcate" items="${requestScope.bcategoryVO }">
					<form:form>
						<div class="set_box hide">
							<input name="catnum" type="hidden" value="${bcate.catnum }">
							<table>
								<tr>
									<th class="b_title">게시판명</th>
									<td class="b_title_value"><input id="b_title_input"
										type="text" value=${bcate.catname } name="catname"></td>
								</tr>
								<tr>
									<th class="b_type">게시판 유형</th>
									<td class="b_type_value"><select id="b_type_value"
										name="btype">
											<option value="blog">자유게시판</option>
											<option value="album">사진게시판</option>
									</select></td>
								</tr>
								<tr>
									<th class="b_subject">말머리</th>
									<td class="b_subject_value"><input class="subInput"
										id="subInput" type="text"> <input type="button"
										onclick="addSub()" value="추가">
										<ul id="b_subject_value" class="b_subject_positon">
											<li class="b_subject_chd"><span>질문</span> <input
												type="button" onclick="delSub(this)" value="제거"></li>
											<li class="b_subject_chd"><span>질문2</span> <input
												type="button" onclick="delSub(this)" value="제거"></li>
										</ul></td>
								</tr>
							</table>
							<div class="actionBtn">
								<input type="submit" value="저장"
									formaction=" 	${contextPath}/admin/setBoard">
							</div>
						</div>
					</form:form>
				</c:forEach>

				<c:if test="${empty sessionScope.member}">
					<div class="container container-upm">
						<%-- <p>
					<a href="${contextPath }"><i id="logo"
						class="fab fa-pied-piper-alt"></i></a>
				</p> --%>
						<p>
							<a href="${url_login }">로그인</a><span> | </span><a
								href="${contextPath }/member/joinForm">회원가입</a>
						</p>
					</div>
				</c:if>
				<!-- 로그인 후  -->
				<c:if test="${!empty sessionScope.member}">

					<div class="container container-upm">
						<%-- <p>
					<a href="${contextPath }"><i id="logo"
						class="fab fa-pied-piper-alt"></i></a>
				</p> --%>
						<p>
							<a href="${url_logout }">로그아웃</a><span> | </span> <a
								href="${url_myPage}">${sessionScope.member.nickname }</a>
						</p>
					</div>
				</c:if>
			</div>
		</div>
	</main>
</body>

<script>
	var idx = 1;

	function getIndex(ele) {
		var _i = 0;
		while ((ele = ele.previousSibling) != null) {
			_i++;
		}
		return _i;
	}

	function b_select(obj) {
		var btn = $('.b_title_btn');
		var cur = document.getElementById("catnum");
		cur.value = obj.value;
		console.log(cur.value);
		btn.removeClass('selected');
		obj.classList.add('selected');

		idx = getIndex(obj);
		$('.set_box').hide();
		$('.set_box').eq(idx - 1).show();

	};

	function delSub(obj) {
		console.log(obj.parentElement);
		obj.parentElement.remove();
	}

	function addSub(obj) {
		var sub_list = document.getElementsByClassName("b_subject_positon");
		console.log(sub_list);
		var subTxt = document.getElementsByClassName("subInput");
		var newList = document.createElement("li");
		newList.setAttribute('class', 'b_subject_chd');
		newList.innerHTML = '<span>'
				+ subTxt[idx - 1].value
				+ '</span> <input type="button" onclick="delSub(this)" value="제거">';
		sub_list[idx - 1].appendChild(newList);
	}
</script>
</html>