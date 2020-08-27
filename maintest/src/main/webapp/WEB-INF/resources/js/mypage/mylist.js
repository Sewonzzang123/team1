/**
 * 
 */
 "use strict";
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
						if (ajax.responseText) {
					alert("삭제되었습니다.");
					
				}
					} 
				}
			};
			ajax.open("POST",
					contextPath+"/mypage/del_list"); //
			ajax.send(ele);

			location.reload(true)
		}
	}