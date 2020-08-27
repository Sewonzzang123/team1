/**
 * 
 */
 "use strict";
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
						if (ajax.responseText) {
					alert("삭제되었습니다.");
					location.reload(true);
				}
				} else {
					console.error(ajax.responseText);
				}
			}
		};
		ajax.open("POST",
				contextPath+"/mypage/del_post", false); //
		ajax.send(del_list);


	}