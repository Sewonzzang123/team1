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
<title>게시판 관리</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/admin.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/admin_main.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script>
	//선택된 게시판
	var idx = null;

	//삭제된 게시판
	var del_board_list = '';

	window.onload = function() {
		$('.delSubBtn').on('click', delSub);
		$('.caname_input').on('keyup', change_title);
		$('#saveBtn').on("click", save);
	}

	//게시판
	function b_select(obj) {
		$('.b_title_btn').removeClass('on');
		console.log(obj);
		obj.classList.add('on')

		idx = $('.b_title_btn').index(obj);
		console.log(idx);
		$('.set_box').hide();
		$('.set_box').eq(idx).show();
	}

	function addMenu() {
		console.info("클릭");
		var newMenu = document.createElement("table");
		var b_list = document.getElementById("b_list");
		;
		var newMenuTitle = document.createElement("li");
		var b_content = document.getElementById("b_content");
		newMenu.setAttribute('class', 'set_box hide');
		newMenuTitle.setAttribute('class', 'selectable');
		newMenuTitle.setAttribute('id', 'n');

		var str = '<input type="hidden" class="info" id="canum_input" value="new">';
		str += '<tr> <th class="caname">게시판명</th> <td class="caname_value"><input id="caname_input" class="caname_input info" type=" text" value="새로운게시판"></td> </tr>';
		str += '<tr> <th class="bmemo">게시판 설명</th> <td class="bmemo_value"><input id="bmemo_input" class="info" type=" text" value=""></td> </tr>';
		str += '<tr> <th class="btype">게시판 유형</th> <td class="btype_input_box"> <select id="btype_input" class="info"> <option value="blog">자유게시판</option> <option value="album">사진게시판</option> </select> </td> </tr>';
		str += '<tr> <th class="b_subject">말머리</th> <td class="b_subject_value"> <input type="hidden" class="del_annum_list info" id="del_annum_list"> <div class="sub_input_box"><input type="text" class="sub_input"><input type="button" onclick="addSub(this)" value="추가"></div> <div class="sub_box"> </div> </td> </tr>';
		str += '';

		newMenu.innerHTML = str;
		newMenuTitle.innerHTML = '<button class="b_title_btn" onclick="b_select(this)" canum="new">새로운게시판</button>';
		b_content.appendChild(newMenu);
		b_list.appendChild(newMenuTitle);

		$('.caname_input').off('keyup', change_title);
		$('.caname_input').on('keyup', change_title);

	};

	function delMenu() {
		var menu = $('.b_title_btn');

		if (idx == null) {
			alert('게시판을 선택해주세요.');
			return;
		}

		if (menu[idx].getAttribute('canum') != 'new') {
			console.log('기존 게시판');
			del_board_list = del_board_list + menu[idx].getAttribute('canum')
					+ '/';
			console.log(del_board_list);
		}

		menu[idx].parentElement.remove();
		$('.set_box').eq(idx).remove();

		idx = null;
	};

	function change_title(e) {
		$(".b_title_btn")[idx].innerHTML = $(this).val();

		console.log('부름')
	}

	// 말머리
	function delSub(e) {
		var sub_parent = e.target.parentElement;

		if (e.target.previousSibling.getAttribute("annum") != 'new') {
			console.log('호출');
			$('.del_annum_list')[idx].value += (e.target.previousSibling
					.getAttribute("annum") + '/');
		}

		console.log($('.del_annum_list')[idx].value);
		e.target.parentElement.remove();
	};

	function addSub(obj) {

		if (obj.previousSibling.value.trim().length == 0) {
			alert("말머리를 입력해주세요.")
			return;
		}

		var parent = obj.parentElement.parentElement;
		var newSub = document.createElement("li");
		newSub.classList.add('b_subject_chd');
		console.log(obj.previousSibling.value);
		var text = '<span class="aname" annum="new">'
				+ obj.previousSibling.value
				+ '</span><input type="button" class="delSubBtn" value="제거">'
		newSub.innerHTML = text;
		parent.appendChild(newSub);
		obj.previousSibling.value = '';
		$('.delSubBtn').off('click', delSub);
		$('.delSubBtn').on('click', delSub);

	};

	//저장
	function save() {

		var boardList = new Array();

		const board_data = $('.set_box')
		for (var i = 0; i < board_data.length; i++) {

			var board = new Object()

			const b_info = board_data[i].getElementsByClassName('info');
			const sub = board_data[i].getElementsByClassName('aname');

			board.canum = b_info[0].value;
			board.caname = b_info[1].value;
			board.bmemo = b_info[2].value;
			board.btype = b_info[3].value;
			board.del_sub_list = $('.del_annum_list')[i].value;

			const sub_list = new Array;

			for (var j = 0; j < sub.length; j++) {

				if (sub[j].getAttribute("annum") == 'new') {
					sub_list.push(sub[j].innerHTML)

				}
			}

			board.sub_list = sub_list;

			boardList.push(board)
		}

		//ajax 등록
		if (del_board_list != '') {
			const xhttp1 = new XMLHttpRequest();

			xhttp1.addEventListener("readystatechange", ajaxCall);
			function ajaxCall(event) {
				if (xhttp1.readyState == 4 && xhttp.status == 200) {
					console.log(xhttp1.responseText);
				}
			}

			//서비스요청1
			xhttp1.open("POST",
					"http://localhost:9080${contextPath}/admin/delBoard");

			xhttp1.send(del_board_list);
		}

		const xhttp2 = new XMLHttpRequest();

		xhttp2.addEventListener("readystatechange", ajaxCall);
		function ajaxCall(event) {
			if (xhttp2.readyState == 4 && xhttp2.status == 200) {
				console.log(xhttp2.responseText);
				if (xhttp2.responseText) {
					alert("수정이 완료되었습니다.");
					location.reload(true);
				}
			}
		}

		//json 생성
		const result = JSON.stringify(boardList);
		console.log(result);
		//서비스요청2
		xhttp2.open("POST",
				"http://localhost:9080${contextPath}/admin/setBoard");
		xhttp2.setRequestHeader("Content-Type",
				"application/json;charset=utf-8");

		xhttp2.send(result);

		/* location.reload(true); */

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

		<main id="content" class="admin_board">
			<div class="content_sub-title">게시판 관리</div>
			<div class="b_container">
				<div class="b_sidebar">
					<div class="b_listSet">
						<ul id="b_list">
							<c:forEach var="bcate" items="${requestScope.bcategoryVO }">
								<li class="selectable"><button class="b_title_btn"
										onclick="b_select(this)" canum="${bcate.catnum }">${bcate.catname }</button></li>
							</c:forEach>
						</ul>
					</div>
					<div class="b_listBtn">
						<input type="button" onclick="addMenu()" value="추가"><input
							type="button" onclick="delMenu()" value="제거">
					</div>
				</div>


				<div class="b_content" id="b_content">
					<c:forEach var="bcate" items="${requestScope.bcategoryVO }">
						<table id="" class="set_box hide">
							<input type="hidden" class="info" id="canum_input"
								value="${bcate.catnum }" />
							<!-- 카테고리 넘버 -->

							<tr>
								<th class="caname">게시판명</th>
								<td class="caname_value"><input id="caname_input"
									class="caname_input info" type="text" value="${bcate.catname }"></td>
							</tr>

							<tr>
								<th class="bmemo">게시판 설명</th>
								<td class="bmemo_value"><input id="bmemo_input"
									class="info" type=" text" value="${bcate.bmemo }"></td>
							</tr>


							<tr>
								<th class="btype">게시판 유형</th>
								<td class="btype_input_box"><select id="btype_input"
									class="info">
										<option value="blog"
											<c:if test="${bcate.btype == 'blog' }">selected</c:if>>자유게시판</option>
										<option value="album"
											<c:if test="${bcate.btype == 'album' }">selected</c:if>>사진게시판</option>
								</select></td>
							</tr>

							<tr>
								<th class="b_subject">말머리</th>
								<td class="b_subject_value"><input type="hidden"
									class="del_annum_list info" id="del_annum_list"> <!-- 삭제된 말머리 넘버 -->
									<div class="sub_input_box">
										<input type="text" class="sub_input"><input
											type="button" onclick="addSub(this)" value="추가">
									</div>
									<div class="sub_box">
										<c:forEach var="headId"
											items="${requestScope.headIdCategoryVO }">
											<c:if test="${bcate.catnum==headId.catnum}">
												<li class="b_subject_chd"><span class="aname"
													annum="${headId.hidnum }">${headId.hidname }</span><input
													type="button" class="delSubBtn" value="제거"></li>
											</c:if>
										</c:forEach>
									</div></td>
							</tr>
						</table>
					</c:forEach>
				</div>
			</div>
			<div class="actionBtn">
				<input type="button" id="saveBtn" value="저장"> <input
					type="button" value="취소">
			</div>

		</main>
	</div>
	<!-- footer -->
	<footer>footer</footer>
</body>
</html>