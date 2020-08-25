/**
 * 
 */
 "use strict";
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
		var item_name = prompt("아이템 이름을 입력해주세요 (최대 20자)");

		if (item_name.length > 20) {
			alert("아이템 이름이 너무 깁니다.")
			return;
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

			icategory.ca_name = icate_info[0].value;
			icategory.ca_num = icate_info[1].value;

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
		xhttp.open("POST", contextPath + "/admin/setItem");

		xhttp
				.setRequestHeader("Content-Type",
						"application/json;charset=utf-8");

		xhttp.send(result);

		console.log(icate_data);
	}