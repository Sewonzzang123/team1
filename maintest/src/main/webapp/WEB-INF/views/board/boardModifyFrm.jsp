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
	href="${contextPath }/css/board/boardMainFrm.css?ver=${today}">
<link rel="stylesheet"
	href="${contextPath}/css/board/boardWriteFrm.css?ver=${today}">
<script defer type="text/javascript"
	src="${contextPath }/js/board/boardWriteFrm.js?ver=${today}"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
</head>

<body>
	<main>
		<div class="main_wrap">
			<!-- 게시판 카테고리 메뉴  -->
			<%@ include file="/WEB-INF/views/board/included/boardAsideMenu.jsp"%>

			<div class="container">
				<div class="content">
					<section>
						<div class="section_wrap">
							<h2>
								<a href="">게시글 작성 </a>
							</h2>
							<form:form id="writeFrm" name="writeFrm" method="POST"
								enctype="multipart/form-data"
								action="${contextPath}/board/modify" modelAttribute="boardVO">

								<input type="hidden" id="bnum" name="bnum"
									value="${requestScope.boardVO.bnum}" />
								<input type="hidden" id="returnPage" name="returnPage"
									value="${requestScope.returnPage}" />
								<input type="hidden" id="ucode" name="ucode"
									value="${sessionScope.member.ucode  }" />
								<input type="hidden" id="catnum" name="catnum"
									value="${requestScope.boardVO.bcategory.catnum}" />
								<input type="hidden" id="hidnum" name="hidnum"
									value="${requestScope.boardVO.hidcategory.hidnum}" />

								<ul>
									<li class="selectGrp"><label for="bcategory.catnum">분류</label>
										<select name="bcategory.catnum" id="bcategory">
											<option value="0">게시판분류</option>
											<c:forEach var="bcate" items="${bcategoryList }">
												<c:if test="${bcate.catnum != 0}">
													<option value="${bcate.catnum }"
														<c:if test="${bcate.catnum == requestScope.boardVO.bcategory.catnum}">selected</c:if>>${bcate.catname }</option>
												</c:if>
											</c:forEach>

									</select> <select name="hidcategory.hidnum" id="hidcategory">
											<option value="">말머리분류</option>

									</select> <form:errors cssClass="bound_error" path="bcategory.catnum"></form:errors>
									</li>
									<li><label for="btitle">제목</label> <input name="btitle"
										type="text" value="${boardVO.btitle }" /> <form:errors
											cssClass="bound_error" path="btitle"></form:errors></li>

									<li class="toolbar_box_li"><label for="btitle"></label>
										<div class="toolbar_box">
											<ul>
												<li>
													<button class="toolbar_btn add_img_btn">
														<i class="far fa-image"></i><br> <span>사진</span>
													</button>
												</li>
											</ul>
										</div>
										<div class="hidden_toolbar_menu">
											<input multiple type="file" class="add_img"
												style="display: none;"> <input type="hidden"
												name="thumbnail" class="thumbnail" value="null">
										</div></li>

									<li class="bcontent_li"><label for="bcontent"></label> <input
										type="hidden" class="bcontent" name="bcontent_area">
										<div class="bcontent_area" contenteditable="true">${boardVO.tcontent }</div>
										<form:errors cssClass="bound_error" path="bcontent"></form:errors></li>

									<li>
										<div class="attachment" id="filesBox">
											<input type="file" id="files" name="files"
												multiple="multiple" />
										</div>
									</li>

									<li>
										<div class="btnGrp">
											<button id="modifyBtn" class="btn" type="button">등록</button>
										</div>
									</li>
								</ul>
							</form:form>
						</div>
					</section>
				</div>
			</div>
		</div>
	</main>
</body>

<script type="text/javascript">
	/* 사진 추가 */
	const add_img_btn = document.querySelector('.add_img_btn');
	const add_img = document.querySelector('.add_img');
	const bcontent_area = document.querySelector('.bcontent_area');
	add_img_btn.addEventListener('click', (e) => {
		e.preventDefault();
		add_img.click();
		console.log('클릭됨');
		}
	)
	
	add_img.addEventListener("change", (e) => {
	    if (e.target.files && e.target.files[0]) {		
		  console.log('이벤트');
	      var formData = new FormData();	
	      formData.append("file", e.target.files[0]);	
	      console.log(formData);
	
	      var ajax = new XMLHttpRequest();
	      ajax.onreadystatechange = function () {
	        if (ajax.readyState === ajax.DONE) {
	          if (ajax.status === 200
	            || ajax.status === 201) {
	            const url = ajax.responseText;
	            console.log(url);
	            
	            const img = document
	              .createElement('img');
	            img.classList.add('added_img')
	            img.setAttribute("src",
	              '${pageContext.request.contextPath}/photo/'
	              + url);
	            img.setAttribute("name", url);
	
	            bcontent_area.append(img);
	          } else {
	            console.error(ajax.responseText);	
	          }
	        }
	      };
	      ajax
	        .open(
	          "POST",
	          "${pageContext.request.contextPath}/board/setphoto",
	          true); //
	      ajax.send(formData);
	    }
	  })
	  
	  
	/* 등록 */
	const writeFrm = document.querySelector('#writeFrm');
	const modifyBtn = document.querySelector('#modifyBtn');
	const bcontent = document.querySelector('.bcontent');
	modifyBtn.addEventListener("click", (e) => {
		console.log('등록');
		bcontent.value = bcontent_area.innerHTML;
		
		const thumbnail_name = document.querySelector('.added_img')

		if (thumbnail_name != null) {
			const thumbnail = document.querySelector('.thumbnail');
			thumbnail.value = thumbnail_name.getAttribute('name');
		}
		
		writeFrm.submit();				
		})
</script>
</html>