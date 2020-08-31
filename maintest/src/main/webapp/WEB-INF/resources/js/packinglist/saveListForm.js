window.addEventListener("load", init);
	function init() {
		const listBtn = document.getElementById("listBtn");
		const cancelBtn = document.getElementById("cancelBtn");
		listBtn.addEventListener("click", listBtn_f);
		cancelBtn.addEventListener("click", cancelBtn_f);
		const saveListBtn = document.getElementById("saveListBtn");
		saveListBtn.addEventListener("click", saveListBtn_f);
		
	}
	function listBtn_f(e) {
		e.preventDefault();
		
		let nameTag = document.getElementById('estimate_box_txt').value;

		console.log(nameTag);
		//이름 입력
		if (!nameTag.trim()) {
			window.alert("이름을 입력해주세요.");
			return;
		}

		//AJAX 사용
		//1)XMLHTTPRequest 객체 생성
		const xhttp = new XMLHttpRequest();
		//2)서버응답 처리
		//readyState
		// 0 : open()가 호출되지 않은 상태
		// 1 : open()가 실행된 상태 server 연결됨
		// 2 : send()가 실행된 상태,  서버가 클라이언트 요청을 받았음.
		// 3 : 서버가 클라이언트 요청 처리중. 응답헤더는 수신했으나 바디가 수신중인 상태
		// 4 : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된상태
		xhttp.addEventListener("readystatechange", ajaxCall);
		function ajaxCall(event) {
			if (this.readyState == 4 && this.status == 200) {
				console.log(this.responseText);
				//json포맷 문자열 => 자바스크립트 ojb
				const jsonObj = JSON.parse(this.responseText);

				switch (jsonObj.rtcode) {
				case "00"://success 리스트 이름 추가해주기
					const lnum = jsonObj.lnum;
					const lname = jsonObj.lname;
					let newlist = document.createElement('p');
					let inputTag = "<input type='radio' id='lnum_"+lnum+"'";
            	inputTag += " name='lnum' value='"+lnum+"' /> <label";
            	inputTag +=	" for='lnum_"+lnum+"'>"
							+ lname + "</label>";
					newlist.innerHTML = inputTag;
					/*newlist.innerHTML += "<input type='radio' id='lnum_'"+lnum+"'";
            	newlist.innerHTML +=	"name="+lnum+" value='lnum"+lname+"' /> <label";
            	newlist.innerHTML +=	"for='lnum_"+lnum+"'>"+lname+"</label>";*/
					document.getElementsByClassName('estimate_list_area')[0]
							.appendChild(newlist);

					break;
				case "01"://fail 실패했다고 alert해주기
					window.alert("리스트 추가에 실패했습니다. 관리자에게 문의하세요.");
					break;
				}
			}
		}
		//3)요청 파라미터만들기(json포맷) {"ucode":"0", "lname":"리스트이름"}
		const reqParam = {};
		//session의 ucode를 가져와야한다.
		reqParam.ucode = "0";
		reqParam.lname = nameTag;
		//js객체를 json포맷 문자열로 변환JSON.stringify()
		//json포맷 문자열을 js객체로 변환JSON.parse()
		const result = JSON.stringify(reqParam);
		//4)서비스요청
		xhttp
				.open("POST",
						"http://localhost:9080/pfpkg/itemlist/lname");
		//post form 요청시 필요
		/*         xhttp.setRequestHeader(
		 "Content-Type",
		 "application/x-www-form-urlencoded"
		 ); */

		//post json 요청시 필요
		xhttp
				.setRequestHeader("Content-Type",
						"application/json;charset=utf-8");
		//querystring 전송 필요시
		//xhttp.send("result=" + result);
		//queryString 불필요시
		xhttp.send(result);

	}

	function cancelBtn_f() {
		window.close();
	}
	function saveListBtn_f(){		

		window.opener.name = "parentPage";
		let frm = document.folderList;	
		frm.target="parentPage";	
		frm.action="http://localhost:9080/pfpkg/packingList/saveList";	
		frm.submit();
		window.close();

		
		
		}