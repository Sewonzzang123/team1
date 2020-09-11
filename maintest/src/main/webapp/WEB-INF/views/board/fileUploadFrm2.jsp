<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%
	response.setContentType("text/html; charset=utf-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 공통모듈 -->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>첨부파일</title>



<script defer>
	window.addEventListener("load", init);
	function init() {
		//케이스 2 
		document
				.getElementById("gdsimg")
				.addEventListener(
						"change",
						function() {

							if (this.files && this.files[0]) {

								var formData = new FormData();

								formData.append("file", this.files[0]);

								console.log("업로드");

								var ajax = new XMLHttpRequest();
								ajax.onreadystatechange = function() {
									if (ajax.readyState === ajax.DONE) {
										if (ajax.status === 200
												|| ajax.status === 201) {
											const url = ajax.responseText;
											console.log(url);
											const content_area = document
													.querySelector('.content_area');
											const img = document
													.createElement('img');
											img.setAttribute("src",
													'${pageContext.request.contextPath}/photo/'
															+ url);
											img.setAttribute("name", url);

											content_area.append(img);
										} else {
											console.error(ajax.responseText);

										}
									}
								};
								ajax
										.open(
												"POST",
												"${pageContext.request.contextPath}/setphoto",
												true); //
								ajax.send(formData);

							}
						})
	}

	function asb() {
		var content = document.getElementById('inputArea').innerHTML;
		console.log(content);

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
		ajax.open("POST", "${pageContext.request.contextPath}/setpost", true); //
		ajax.send(content);
	}
</script>
</head>
<body>

	<h2>케이스 2</h2>
	<div class="inputArea" id="inputArea">
		<!-- <form action="/pfpkg/upload" method="POST" id="content"
			enctype="multipart/form-data"></form>
		<label for="gdsmimg">이미지</label>
		<div class="select_img">
			<img src="" alt="">
		</div>
		<input type="button" value="버튼" onclick="asb()"> <input
			type="submit" /> <img alt=""
			src="https://cdn.pixabay.com/photo/2020/01/31/07/10/billboards-4807268_960_720.jpg"> -->


		<div class="btn_area">
			<input type="file" name=files id="gdsimg" multiple>
		</div>

		<div class="content_area" contenteditable="true">
			<div class="line">첫번째 텍스트</div>
		</div>

		<button type="button"
			onclick="location.href='${pageContext.request.contextPath}/coretest' ">회원가입</button>
	</div>

	<c:set var="core" value="Korea" scope="request" />

	<div class="inputArea"></div>


</body>
</html>