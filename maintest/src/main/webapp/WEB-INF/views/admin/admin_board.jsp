<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
</style>
</head>

<body>

	<main class="content" id="adminBoard">
		<div class="b_container">

			<div class="b_sidebar">
				<div class="b_listSet">
					<ul id="b_list">
						<c:forEach var="bcate" items="${requestScope.bcategoryVO }">
							<button class="b_title_btn" onclick="b_select(this)">${bcate.catname }
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
					<table class="set_box hide">
						<tr>
							<th class="b_title">게시판명</th>
							<td class="b_title_value"><input id="b_title_input"
								type=" text" value=${bcate.catname }></td>
						</tr>
						<tr>
							<th class="b_memo">게시판 설명</th>
							<td class="b_memo_value"><input id="b_memo_input"
								type=" text"></td>
						</tr>
						<tr>
							<th class="b_type">게시판 유형</th>
							<td class="b_type_value"><select name="b_type_value"
								id="b_type_value" value="album">
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
				</c:forEach>
			</div>
		</div>
		<div class="actionBtn">
			<input type="button" value="저장"> <input type="button"
				value="취소">
		</div>

	</main>



</body>

<script>
	window.onload = function setPage() {
		console.log("123");
		console.log()

	}

	var idx = 1;

	function getIndex(ele) {
		var _i = 0;
		while ((ele = ele.previousSibling) != null) {
			_i++;
		}
		return _i;
	}

	function b_select(obj) {
		// var table = document.getElementsByClassName('set_box');   
		var btn = $('.b_title_btn');
		btn.removeClass('selected');
		obj.classList.add('selected')

		idx = getIndex(obj);
		console.log(idx);
		$('.set_box').hide();
		$('.set_box').eq(idx - 1).show();

	};
	// <li id="n" class="selectable">
	var cnt = 0;
	function addMenu() {
		console.info("클릭");
		var newMenu = document.createElement("table");
		var b_list = document.getElementById("b_list");
		;
		var newMenuTitle = document.createElement("button");
		var b_content = document.getElementById("b_content");
		newMenu.setAttribute('class', 'set_box hide');
		newMenuTitle.setAttribute('class', 'b_title_btn');
		newMenuTitle.setAttribute('onclick', 'b_select(this)');
		// newMenuTitle.setAttribute('id', 'n');
		var str = '<tr><th class="b_title">게시판명</th><td class="b_title_value"><input id="b_title_input" type=" text" value="새로운 게시판"></td></tr>';
		str += '<tr><th class="b_memo">게시판 설명</th><td class="b_memo_value"><input id="b_memo_input" type=" text" value="새로운 게시판"></td></tr>';
		str += '<tr><th class="b_type">게시판 유형</th><td class="b_type_value"><select name="b_type_value" id="b_type_value">';
		str += '<option value="blog">자유게시판</option><option value="balbumlog">사진게시판</option></select></td></tr>';
		str += '<tr><th class="b_subject">말머리</th><td class="b_subject_value"><input class="subInput" id="subInput" type="text">';
		str += ' <input type="button" onclick="addSub()" value="추가"><ul id="b_subject_value" class="b_subject_positon"></ul></td></tr>';
		newMenu.innerHTML = str;
		newMenuTitle.innerHTML = '게시판 n'
		b_content.appendChild(newMenu);
		b_list.appendChild(newMenuTitle);
	};

	function delMenu(obj) {
		var b_title_btn = document.getElementsByClassName('b_title_btn');
		var set_box = document.getElementsByClassName('set_box');
		b_title_btn[idx - 1].remove();
		set_box[idx - 1].remove();
	};

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

	function delSub(obj) {
		console.log(obj.parentElement);
		obj.parentElement.remove();
	}
</script>
</html>