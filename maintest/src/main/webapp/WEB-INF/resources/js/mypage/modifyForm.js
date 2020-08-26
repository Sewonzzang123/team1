/**
 * 
 */ 
 "use strict";
 window.onload = function() {
		const PWChangeBtn = document.getElementById('PWChangeBtn');
		PWChangeBtn.addEventListener('click', PWChangeBtn_f);
	}

	function PWChangeBtn_f() {
		var curpw = document.getElementById('curpw');
		var nextpw = document.getElementById('nextpw');
		var nextpwc = document.getElementById('nextpwc');

		var curpw_msg = document.getElementById('curpw_msg');
		var nextpw_msg = document.getElementById('nextpw_msg');

		curpw_msg.innerText = '';
		nextpw_msg.innerText = '';

		if (curpw.value.trim().length == 0) {
			curpw_msg.innerText = '*현재 비밀번호를 입력해주세요.';
			curpw.focus();
			return;
		};
		
	 if (curpw.value != pw) {
			curpw_msg.innerText = '*현재 비밀번호가 일치하지 않습니다.';
			curpw.focus();
			return;
		};

		if (nextpw.value.trim().length == 0) {
			nextpw_msg.innerText = '*새 비밀번호를 입력해주세요.';
			nextpw.focus();
			return;
		};

		if (nextpw.value.trim().length < 6) {
			nextpw_msg.innerText = '*비밀번호는 6자리 이상입니다.';
			nextpw.focus();
			return;
		};

		if (nextpw.value != nextpwc.value) {
			nextpw_msg.innerText = '*새 비밀번호를 확인해주세요.';
			nextpwc.focus();
			return;
		};
	

		
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
		ajax.open("POST", contextPath+"/mypage/changePW"); //
		ajax.send(nextpw.value);
		
		curpw.value = '';
		nextpw.value = '';
		nextpwc.value = '';
		alert('비밀번호가 변경되었습니다.');
	}