<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/included/common_taglib.jsp"%>
<link rel="stylesheet"
	href="${contextPath }/css/packinglist/saveListForm.css">
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>리스트 저장 페이지</title>
<script>
	window.addEventListener("load", init);
	function init() {
		const listBtn = document.getElementById("listBtn");
		const cancelBtn = document.getElementById("cancelBtn");
		listBtn.addEventListener("click", listBtn_f);
		cancelBtn.addEventListener("click", cancelBtn_f);
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
						"http://localhost:9080${contextPath}/itemlist/lname");
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

</script>
</head>
<body>
	<div id="contentsArea">
		<form name="folderList" method="post" action="${contextPath }/packingList/saveList">
			<div class="pop_wrapper"
				style="width: 440px; height: 530px; padding: 0px;">
				<div class="pop_header">
					<div class="pop_title_area">
						<h1 class="pop_title_txt">내 리스트</h1>
					</div>
				</div>
				<div id="content" class="pop_content_wrap estimate_list_wrap">
					<div class="estimate_list_area">
						<c:forEach items="#{list }" var="listVO">
							<p>
								<input type="radio" id="lnum_${listVO.lnum }" name="lnum"
									value="${listVO.lnum }"/> 
								<label for="lnum_${listVO.lnum }">${listVO.lname }</label>
							</p>
						</c:forEach>
					</div>
					<div class="folder_add_area">
						<label class="tit_sub" for="estimate_box_txt">리스트 생성</label> <input
							type="text" title="리스트 생성" id="estimate_box_txt"
							placeholder="리스트 명을 입력하세요." maxlength="30" />
						<input type="button" class="folder_add_button" type="button" id="listBtn"
							value="생성하기"/>
					</div>
					<div class="folder_info_wrap">
						<div class="info_title">리스트 저장 안내</div>
						<ul class="info_contents">
							<li>1</li>
							<li>2</li>
							<li>3</li>
							<li>4</li>
							<li>5</li>
							<li>6</li>
						</ul>
					</div>
					<div class="estimate_button_area">
						<button class="btn_pop_save" type="submit">저장</button>
						<input class="btn_pop_cancel" type="button" id="cancelBtn" value="취소"/>
					</div>
				</div>
			</div>

			<!-- 리스트에 넣을 아이템 정렬 -->
			<c:if test="${not empty listing}">
				<c:forEach items="#{listing }" var="item">
					<input type="hidden" name="iname" value="${item.iname }" />
					<input type="hidden" name="icount" value="${item.icount }" />
					<input type="hidden" name="inum" value="${item.inum }" />
					<input type="hidden" name="icategory" value="${item.icategory }" />
					<input type="hidden" name="checked" value="${item.checked }" />

				</c:forEach>
			</c:if>
		</form>
	</div>



</body>
</html>