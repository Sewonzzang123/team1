/**
 * 
 */
 "use strict";
 
	$(document).ready(function() {
		$("#allselect").change(allselect);
		$("#exitBtn").click(member_exit)
		document.getElementById('serchBtn').addEventListener('click', serch)
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
		xhttp.open("POST", contextPath+"/admin/exit" );

		xhttp
				.setRequestHeader("Content-Type",
						"application/json;charset=utf-8");

		xhttp.send(result);

	}

	function serch() {
		var searchType = document.getElementById('searchType').value;
		var keyword = document.getElementById('keyword').value;

		if (keyword.trim().length != 0) {

			location.href = contextPath+"/admin/member/1/" + searchType + "/"
					+ keyword;

		} else {
			alert('검색어를 입력해주세요.')
		}
	}