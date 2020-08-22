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
	href="${contextPath}/css/board/boardWriteFrm.css?ver=0">
<%-- <script defer type="text/javascript" src="${contextPath }/js/board/boardWriteFrm.js?ver=0"></script> --%>

<script type="text/javascript">
window.addEventListener("load", init)


function init() {

    const bCateTag = document.getElementById("bcategory.catnum")

    bCateTag.addEventListener("change", gethid);

    function gethid(e) {

        let bCateTagVal = bCateTag.value;

        console.log("bCateTagVal == " + bCateTagVal);

        //ajax 

        const xhttp = new XMLHttpRequest();


//xhttp의 상태 변화를 감지하는 readystatechange 이벤트 리스너를 걸어준다.
// 변화를 감지할려면 최소 요청이 한번은 들어가야 로직을 탄다. 

		xhttp.addEventListener("readystatechange" , ajaxCall);

		function ajaxCall(){
		
        // 응답유형 부터 
        const resObj = XMLHttpRequest.responseText;
        console.log("요청으로 가져오는 텍스트 ==" +  resObj);

        
		if(XMLHttpRequest.readyState == 4 && XMLHttpRequest.status == 200){

			console.log(XMLHttpRequest.responseText);

			const jsonToJsobj = JSON.parse(XMLHttpRequest.responseText);

			switch(jsonToJsobj.rtcode){
			case "00":
				console.log("rtcode = 00")
				break; 
			case "01":
				console.log("rtcode = 01");
				break;			
			}


			}
    }//ajaxCall


		//요청하기
			//1. 파라미터 부터 만들기 Jsobj to Json 포맷
			//배열형식을 만들어준다. 
			const jsobj = {};
			jsobj.catnum = bCateTagVal;
			console.log("jsobj.catnum = " + jsobj);	

			const jsobjToJson = JSON.stringify(jsobj);
			console.log("jsobjToJson = json 형태로 보낼거임" + jsobjToJson);	
				
		 xhttp.open("post", "http://localhost:9080/pfpkg/board/headid");
		//데이터 타입을 선언 
		//post: Form 요청시 
		//xhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

		//get: queryString 형식으로 전송 필요시 !!!!
		//xhttp.send("jsobjToJson = " + jsobjToJson);
		
		//post: json 요청시 
		xhttp.setRequestHeader("Content-Type", "application/json;charset-8");		
		xhttp.send(jsobjToJson);

		

		
    } //gethid

} //init



	</script>

</head>
<body>


	<main>
		<div class="container">
			<div class="content">
				<section>
					<h2>
						<a href=""> 게시글 제목 </a>
					</h2>
					<hr>
					<form id=writeFrm name="writeFrm" method="POST"
						enctype="multipart/form-data" action="${contextPath}/board/write">
						<input type="hidden" id="returnPage" name="returnPage"
							value="${returnPage}" />

						<ul>
							<li class="selectGrp"><label for="bcategory.catnum">분류</label>
								<select name="bcategory.catnum" id="bcategory.catnum">
									<option value="">게시판분류</option>
									<c:forEach var="bcate" items="${bcategory }">
										<option value="${bcate.catnum }">${bcate.catname }</option>
									</c:forEach>

							</select> <select name="hidcategory.hidnum">
									<option value="">말머리분류</option>
									<c:forEach var="hcate" items="${hidcategory }">
										<option value="${hcate.hidnum }">${hcate.hidname }</option>
									</c:forEach>
							</select></li>
							<li><label for="btitle">제목</label> <input name="btitle"
								type="text" />
							<li><label for="ucode">작성자</label> <input type="text"
								name="ucode" />
							<li><label for="bcontent">내용</label> <textarea
									name="bcontent" rows="10" placeholder="내용 입력"></textarea></li>
							<li>
							<li>
								<div class="files" id="filesDropArea">

									<button type="button">파일첨부</button>

								</div> <input type="file" id="files" name="files" multiple="multiple" />

							</li>
							<li>
								<div class="btnGrp">
									<button id="tmpWriteBtn" type="button">임시저장</button>
									<button id="writeBtn" type="button">등록</button>
									<button id="listBtn" type="button">목록으로</button>
								</div>
							</li>
						</ul>
					</form>
				</section>
			</div>
		</div>
	</main>


</body>
</html>