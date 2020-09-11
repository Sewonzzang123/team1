<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 작성</title>
<link rel="stylesheet"
	href="${contextPath}/css/board/boardWriteFrm.css?ver=1">

</head>
<!-- <script defer type="text/javascript">
const writeBtn = document.getElementById("writeBtn");
const writeFrm = document.getElementById("writeFrm");
const listBtn = document.getElementById("listBtn");

const filesBoxTag = document.getElementById("filesBox");      
const filesTag = document.getElementById("files");
		
	  const bCateTag = document.getElementById("bcategory")
		const hidCateTag = document.getElementById("hidcategory")
		        //게시판 분류 카테고리 별 말머리 연동 ajax
	bCateTag.addEventListener("change", getHeadid_f);

writeBtn.addEventListener("click", writeBtn_f);
listBtn.addEventListener("click", listBtn_f);       
filesBoxTag.addEventListener("click", filesBoxTag_f);


//게시글 등록버튼
function writeBtn_f(e){ 
e.preventDefault();
    console.log("글등록 버튼 클릭");
writeFrm.submit();
	 }
	 
	 
/* 	 //목록으로 버튼     
function   listBtn_f(e){
const returnPage = document.getElementById("returnPage").value;
const url = `/pfpkg/board/boardListFrm/${returnPage}`;
window.location.href=url;     
		} */
		
  		
/* 		//첨부파일 버튼 클릭   
		function filesBoxTag_f(e){

		let clickedTag  = e.target;    
		console.log( "clickedTag" +clickedTag.tagName);   		
		
		
		
		
		    filesTag.addEventListener("change", (e) => {

        console.log(e.target.files);
        const imgContentTag = document.querySelector(".img_content");
        imgContentTag.innerHTML = "";
        Array.from(e.target.files).forEach(function (file) {

            // if(file.type.match("")){
            //     alert("이미지 파일만 첨부 가능합니다.");
            //     return;
            // }

            const newDiv1 = document.createElement("div");
            const newDiv2 = document.createElement("div");
            const newDiv3 = document.createElement("div");
            const newImg = document.createElement("img");
            const url = URL.createObjectURL(file);
            newDiv1.setAttribute("class", "thumbnail_wrapper");
            newDiv2.setAttribute("class", "thumbnail");
            newDiv3.setAttribute("class", "centered");
            newImg.src = url;
            newDiv3.appendChild(newImg);
            newDiv2.appendChild(newDiv3);
            newDiv1.appendChild(newDiv2);
            imgContentTag.appendChild(newDiv1);


        })
    })



} */
   
   
		
		
		
//보드 카테고리에 맞는 말머리 불러오기 
//ajax
function	getHeadid_f(e){   	 	
const bCateTagVal = bCateTag.value;

//ajax 
const xhttp = new XMLHttpRequest();

xhttp.addEventListener("readystatechange", ajaxCall);


function ajaxCall(e){
//처리완료 시 응답처리 

if(this.readyState == 4 && this.status == 200){

const responseObj = this.responseText;
const jsonToJsObj = JSON.parse(responseObj);
switch(jsonToJsObj.rtcode){
case "00": 
const hidcategory = jsonToJsObj.hidcategory;
hidCateTag.innerHTML = "";
Array.from(hidcategory).forEach(e=>{
hidCateTag.innerHTML += "<option value='" +e.hidnum + "' >" +e.hidname + "</option>"
})   
break;

case"01" :
console.log("말머리 불러오기 실패" )
break;   
}
}
}

const jsObj = {};
jsObj.catnum = bCateTagVal;
const jsObjToJson = JSON.stringify(jsObj);
xhttp.open("POST", "http://localhost:9080/pfpkg/board/headid");
xhttp.setRequestHeader("Content-Type", "application/json;charset=utf-8");
xhttp.send(jsObjToJson);     	
	
	}

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
}</script> -->

<body>


	<main>
		<div class="container">
			<div class="content">
				<section>
					<h2>
						<a href=""> 게시글 제목 </a>
					</h2>
					<hr>
					<form:form id="writeFrm" name="writeFrm" method="POST"
						enctype="multipart/form-data" action="${contextPath}/board/write"
						modelAttribute="boardVO">



						<input type="hidden" id="returnPage" name="returnPage"
							value="${returnPage}" />
						<input type="hidden" id="ucode" name="ucode"
							value="${sessionScope.member.ucode  }" />
						<ul>
							<li class="selectGrp"><label for="bcategory.catnum">분류</label>
								<select name="bcategory.catnum" id="bcategory">
									<option value="0">게시판분류</option>
									<c:if test="${!boardVO.bcategory.btitle != '' }">
										<option value="${boardVO.bcategory.catnum }" selected>${boardVO.bcategory.catname }</option>
									</c:if>

									<c:forEach var="bcate" items="${bcategoryList }">
										<option value="${bcate.catnum }">${bcate.catname }</option>
									</c:forEach>

							</select> <select name="hidcategory.hidnum" id="hidcategory">
									<option value="">말머리분류</option>
									<%-- 		<c:forEach var="hcate" items="${hidcategoryList }">
										<option value="${hcate.hidnum }">${hcate.hidname }</option>
									</c:forEach> --%>
							</select> <form:errors cssClass="bound_error" path="bcategory.catnum"></form:errors>
							</li>
							<li><label for="btitle">제목</label> <input name="btitle"
								type="text" value="${boardVO.btitle }" /> <form:errors
									cssClass="bound_error" path="btitle"></form:errors></li>

							<li><label for="bnickname">작성자</label> <input type="text"
								name="bnickname" readonly
								value="${sessionScope.member.nickname }" /></li>

							<div class="btn_area">
								<input type="hidden" name="thumbnail" class="thumbnail"
									value="null"> <input type="file" name=files id="gdsimg"
									multiple>
							</div>
							<input type="hidden" name="bcontent_area" class="bcontent_area">
							<div class="content_area" contenteditable="true"
								name="bcontent_area">
								<div class="line">첫번째 텍스트</div>
							</div>

							<button type="button"
								onclick="location.href='${pageContext.request.contextPath}/coretest' ">회원가입</button>
			</div>

			<!-- <div class="inputArea"></div>
			<li>
				<div class="attachment" id="filesBox">
					<label for="files">파일첨부</label> <input type="file" id="files"
						name="files" multiple="multiple" />
				</div>
				<div class="img_wrap">
					<div class="img_content"> -->

			<!-- 보여줄 이미지 들어오는 공간 -->

		</div>
		</div>
		</li>


		<li>
			<div class="btnGrp">
				<button id="tmpWriteBtn" type="button">임시저장</button>
				<button id="writeBtn" type="button">등록</button>
				<button id="listBtn" type="button">목록으로</button>
			</div>
		</li>
		</ul>
		</form:form>
		</section>
		</div>
		</div>
	</main>

	<script defer>
		const writeBtn = document.getElementById("writeBtn");
		const writeFrm = document.getElementById("writeFrm");
		const listBtn = document.getElementById("listBtn");

		writeBtn.addEventListener("click", writeBtn_f);
		listBtn.addEventListener("click", ()=>{
			});


		const filesBoxTag = document.getElementById("filesBox");      
		const filesTag = document.getElementById("files");
				
			  const bCateTag = document.getElementById("bcategory")
				const hidCateTag = document.getElementById("hidcategory")
				        //게시판 분류 카테고리 별 말머리 연동 ajax
			bCateTag.addEventListener("change", getHeadid_f);
		
		function writeBtn_f(e) {
			e.preventDefault();
			const content_area = document.querySelector('.content_area');
			const bcontent_area = document.querySelector('.bcontent_area');
			const thumbnail_name = document.querySelector('.img_file')

			if (thumbnail_name != null) {
				const thumbnail = document.querySelector('.thumbnail');
				thumbnail.value = thumbnail_name.getAttribute('name');
				console.log("2번" + thumbnail);
				alert("not null");
			}
			bcontent_area.value = content_area.innerHTML;
			writeFrm.submit();
		}

		window.addEventListener("load", init);
		 function init() {
		        //케이스 2 
		        document
		            .getElementById("gdsimg")
		            .addEventListener(
		                "change",
		                function () {

		                    if (this.files && this.files[0]) {

		                        var formData = new FormData();

		                        formData.append("file", this.files[0]);

		                        console.log("업로드");

		                        var ajax = new XMLHttpRequest();
		                        ajax.onreadystatechange = function () {
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
		                                    img.setAttribute("class",
		                                        "img_file");

		                                    content_area.append(img);
		                                } else {
		                                    console
		                                        .error(ajax.responseText);

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

//보드 카테고리에 맞는 말머리 불러오기 
	//ajax
    function getHeadid_f(e) {
        const bCateTagVal = bCateTag.value;

        //ajax 
        const xhttp = new XMLHttpRequest();

        xhttp.addEventListener("readystatechange", ajaxCall);


        function ajaxCall(e) {
            //처리완료 시 응답처리 

            if (this.readyState == 4 && this.status == 200) {

                const responseObj = this.responseText;
                const jsonToJsObj = JSON.parse(responseObj);
                switch (jsonToJsObj.rtcode) {
                    case "00":
                        const hidcategory = jsonToJsObj.hidcategory;
                        hidCateTag.innerHTML = "";
                        Array.from(hidcategory).forEach(e => {
                            hidCateTag.innerHTML += "<option value='" + e.hidnum + "' >" + e.hidname + "</option>"
                        })
                        break;

                    case "01":
                        console.log("말머리 불러오기 실패")
                        break;
                }
            }
        }

        const jsObj = {};
        jsObj.catnum = bCateTagVal;
        const jsObjToJson = JSON.stringify(jsObj);
        xhttp.open("POST", "http://localhost:9080/pfpkg/board/headid");
        xhttp.setRequestHeader("Content-Type", "application/json;charset=utf-8");
        xhttp.send(jsObjToJson);
    }
		
	</script>
</body>
</html>