<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	width: inherit;
	height: 100%;
	min-height: 600px;
	overflow-y: scroll;
}

.b_sidebar button {
	width: 180px;
	height: 35px;
	text-align: left;
	padding-left: 20px;
	background-color: white;
	border: 0px white solid;
}

.b_listBtn input {
	width: 100px;
}

.b_container {
	width: 620px;
	border: 1px solid black;
	border-collapse: collapse;
	display: flex;
}

.b_sidebar {
	border-right: 1px solid black;
	width: 200px;
	display: flex;
	flex-direction: column;
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

.b_content th {
	text-align: left;
	vertical-align: top;
}

.btype_input_box {
	text-align: center;
}

.b_content tr {
	margin: 20px;
}

#caname_input, #bmemo_input {
	height: 25px;
	width: 250px;
}

#icate_name_input {
	height: 40px;
	width: 250px;
	text-align: center;
}

.b_subject_chd {
	margin-left: 5px;
	margin-top: 10px;
	width: 250px;
	border-bottom: 1px solid #979797;
}

.b_subject_chd>span {
	margin-bottom: 5px;
	display: inline-block;
	width: 200px;
}

.actionBtn {
	width: 600px;
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
}

.on {
	color: blue;
	font-weight: bold;
}

#btype_input {
	width: 250px;
	height: 30px;
}

.sub_input {
	width: 200px;
}

.sub_input_box {
	display: flex;
	justify-content: space-between;
}

.icategory_name {
	width: 700px;
}

#icategory_set_box {
	height: 100%;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}

#icategory_table td, #icategory_table tr {
	border: none;
}

#icategory_table tfoot {
	position: absolute;
	bottom: 0px;
	height: 50px;
}

#icategory_table .item_area {
	padding: 20px 10px;
}

#icategory_table .item_set {
	display: flex;
	justify-content: space-between;
	border-bottom: 1px solid #979797;
	padding-bottom: 5px;
	margin-bottom: 5px;
}

.icategory_add_btn_area {
	text-align: center;
	margin-bottom: 10px;
}
</style>
<script>
	//선택된 카테고리
	var idx = null;

	//삭제된 카테고리
	var del_icate_list = new Array();

	//삭제된 아이템
	var item_del_list = new Array();

	window.onload = function() {
		$('.icate_name_input').on('keyup', change_title);
		$('#saveBtn').on("click", save);
	}

	// 카테고리 선택
	function icate_name_select(obj) {
		$('.icate_name').removeClass('on');
		console.log(obj);
		obj.classList.add('on')

		idx = $('.icate_name').index(obj);
		console.log(idx);
		$('.set_box').hide();
		$('.set_box').eq(idx).show();
	}

	//카테고리 추가
	function addMenu() {
		console.info("클릭");
		var newMenu = document.createElement("div");
		var b_list = document.getElementById("b_list");
		;
		var newMenuTitle = document.createElement("li");
		var b_content = document.getElementById("b_content");
		newMenu.setAttribute('class', 'set_box');
		newMenu.setAttribute('style', 'display: none');
		newMenu.setAttribute('id', 'icategory_set_box');
		newMenuTitle.setAttribute('class', 'selectable');

		var str = '<table id="icategory_table">';
		str += '<caption><input id="icate_name_input" class="icate_name_input info" type=" text" value="새 카테고리"></caption>';
		str += '<input type="hidden" class="info" id="ca_num_input" value="new">';
		str += '<tr> <td class="item_area"></td> </tr>';
		str += '</table> <div class="icategory_add_btn_area"> <input type="button" value="아이템 추가" class="icategory_add_btn" onclick="item_add()"> </div>';

		newMenu.innerHTML = str;
		newMenuTitle.innerHTML = '<button class="icate_name" onclick="icate_name_select(this)" ca_num="new">새 카테고리</button>';
		b_content.appendChild(newMenu);
		b_list.appendChild(newMenuTitle);

		$('.icate_name_input').off('keyup', change_title);
		$('.icate_name_input').on('keyup', change_title);

	};

	//카테고리 삭제
	function delMenu() {
		var menu = $('.icate_name');

		if (idx == null) {
			alert('카테고리를 선택해주세요.');
			return;
		}

		if (menu[idx].getAttribute('ca_num') != 'new') {
			console.log('기존 게시판');
			del_icate_list.push(menu[idx].getAttribute('ca_num'));
			console.log(del_icate_list);
		}

		menu[idx].parentElement.remove();
		$('.set_box').eq(idx).remove();

		idx = null;
	};

	// 카테고리 이름 연동
	function change_title(e) {
		$(".icate_name")[idx].innerHTML = $(this).val();

		console.log('부름')
	}

	//아이템 추가-삭제
	function item_add() {
		var item_name = prompt("아이템 이름을 입력해주세요 (최대 17자)");

		if (item_name.length > 17) {
			alert("아이템 이름이 너무 깁니다.")
		}

		var target_area = $('.item_area')[idx];

		var new_item = document.createElement('li');
		new_item.innerHTML = '<div class="item_set"> <span class="item_name">'
				+ item_name
				+ '</span><input type="button" value="삭제" class="item_del_btn" onclick="item_del(this)"> </div>';

		target_area.appendChild(new_item);
		console.log(item_name);
	}

	function item_del(e) {

		if (e.parentElement.getAttribute('i_num') != null) {
			item_del_list.push(e.parentElement.getAttribute('i_num'));
			console.log(item_del_list);
		}
		e.parentElement.remove();
	}

	//저장
	function save() {

		var icate_data = new Array();

		const board_data = $('.set_box')
		console.log('작동');
		for (var i = 0; i < board_data.length; i++) {

			var icategory = new Object();

			const icate_info = board_data[i].getElementsByClassName('info');
			const item = board_data[i].getElementsByClassName('item_name');

			icategory.ca_num = icate_info[0].value;
			icategory.ca_name = icate_info[1].value;

			const item_list = new Array;

			for (var j = 0; j < item.length; j++) {
				console.log(item[j].parentElement.getAttribute("i_num"))
				if (item[j].parentElement.getAttribute("i_num") == null) {
					item_list.push(item[j].innerHTML)
				}
			}

			icategory.item_list = item_list;
			console.log(icategory);
			icate_data.push(icategory)
		}

		//ajax 등록	
		const xhttp = new XMLHttpRequest();

		xhttp.addEventListener("readystatechange", ajaxCall);
		function ajaxCall(event) {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				alert("수정이 완료되었습니다.");
				location.reload(true);
			}
		}

		const result = JSON.stringify({
			del_icate_list : del_icate_list,
			item_del_list : item_del_list,
			icate_data : icate_data
		});

		console.log(result)
		//서비스요청1
		xhttp.open("POST", "http://localhost:9080${contextPath}/admin/setItem");

		xhttp
				.setRequestHeader("Content-Type",
						"application/json;charset=utf-8");

		xhttp.send(result);

		console.log(icate_data);
	}
</script>
<style>
</style>
</head>

<body>

	<main class="content" id="adminBoard">

		<div class="b_container">

			<div class="b_sidebar">
				<div class="b_listSet">
					<ul id="b_list">
						<c:forEach var="icate" items="${requestScope.itemCategoryVO }">
							<li class="selectable"><button class="icate_name"
									onclick="icate_name_select(this)" ca_num="${icate.ca_num }">${icate.ca_name }</button></li>
						</c:forEach>

					</ul>
				</div>
				<div class="b_listBtn">
					<input type="button" onclick="addMenu()" value="추가"><input
						type="button" onclick="delMenu()" value="제거">
				</div>
			</div>



			<div class="b_content" id="b_content">
				<c:forEach var="icate" items="${requestScope.itemCategoryVO }">

					<div id="icategory_set_box" class="set_box" style="display: none;">

						<table id="icategory_table">
							<caption>
								<input id="icate_name_input" class="icate_name_input info"
									type=" text" value="${icate.ca_name }">
							</caption>
							<input type="hidden" class="info" id="ca_num_input"
								value="${icate.ca_num }">
							<!-- 카테고리 넘버 -->
							<tr>
								<td class="item_area">
									<li><c:forEach var="item" items="${requestScope.item }">
											<c:if test="${item.itemCategoryVO.ca_num == icate.ca_num }">
												<div class="item_set" i_num="${item.i_num }">
													<span class="item_name">${item.i_name }</span><input
														type="button" value="삭제" class="item_del_btn"
														onclick="item_del(this)">
												</div>
											</c:if>
										</c:forEach></li>
								</td>
							</tr>
						</table>

						<div class="icategory_add_btn_area">
							<input type="button" value="아이템 추가" class="icategory_add_btn"
								onclick="item_add()">
						</div>
					</div>
				</c:forEach>

			</div>
		</div>

		<div class="actionBtn">
			<input type="button" id="saveBtn" value="저장"> <input
				type="button" value="취소">
		</div>

	</main>

	<script>
		
	</script>
</body>
</html>