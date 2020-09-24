<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common.jsp"%>

<title>게시글 작성</title>
<link rel="stylesheet"
	href="${contextPath }/css/board/boardMainFrm.css?ver=1">
<link rel="stylesheet"
	href="${contextPath}/css/board/boardWriteModifyFrm.css?ver=2">
<link rel="stylesheet" href="${contextPath}/css/board/button.css?ver=2">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

<script defer type="text/javascript"
	src="${contextPath }/js/board/boardWriteFrm.js"></script>
</head>
<body>
	<main>
		<main>
			<!-- uppermost  메뉴  -->
			<%@ include file="/WEB-INF/views/layout/header.jsp"%>
			<!-- 메인 베너 _ 이미지  + hidden 요소 catnum, returnPage-->
			<%@ include file="/WEB-INF/views/layout/mainbanner.jsp"%>
			<div class="main_wrap">
				<!-- 게시판 카테고리 메뉴  -->
				<%@ include file="/WEB-INF/views/board/included/boardAsideMenu.jsp"%>
				<section>
					<div class="section_wrap">
							<form:form id="Frm" name="Frm" method="POST" 	enctype="multipart/form-data"
							action="${contextPath}/board/write/" modelAttribute="boardVO">
							
							<input type="hidden" id="returnPage" name="returnPage"			value="${requestScope.returnPage}" />
							<input type="hidden" id="ucode" name="ucode"				value="${sessionScope.member.ucode  }" />
							<input type="hidden" id="catnum" name="catnum"					value="${requestScope.bcategoryVO.catnum}" />
							<input type="hidden" id="hidnum" name="hidnum"				value="${requestScope.boardVO.hidcategory.hidnum}" />
							<ul>
		<li class="selectGrp"><select name="bcategory.catnum"
								id="bcategory">
									<option value="0">게시판분류</option>
									<c:forEach var="bcate" items="${bcategoryList }">
										<c:if test="${bcate.catnum != 0}">
											<option value="${bcate.catnum }"
												<c:if test="${bcate.catnum == requestScope.boardVO.bcategory.catnum}">selected</c:if>
												>${bcate.catname }</option>
										</c:if>
									</c:forEach>

							</select> <select name="hidcategory.hidnum" id="hidcategory">
									<option value="">말머리분류</option>

							</select> <form:errors cssClass="bound_error" path="bcategory.catnum"></form:errors>
							</li>

								<li><input name="btitle" type="text"			placeholder="제목을 입력하세요."></input> 
									<form:errors	cssClass="bound_error" path="btitle"></form:errors></li>
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
										<input type="hidden" name="thumbnail" class="thumbnail"	value="null">										
											 <input type="file" name="files" id="pics"	multiple style="display: none;">
									</div>
								</li>
									
									
									
<!-- 리스트  -->
			<!-- 		<li>
									<div class="btn_area">
										<input type="hidden" name="thumbnail" class="thumbnail"
											value="null"> <input type="file" name=files id="pics"
											multiple>
									</div>
								</li> -->
								
								
								
								
								<li>
									<div class="attachment" id="listBox">
									<label class="uploadList">리스트 첨부</label>			
									<c:if test="${not empty requestScope.lname }">
										<input type="hidden" name="listVO.lnum" value="${requestScope.lnum }"/>
										<input type="text" name="listVO.lname" value="${requestScope.lname }"/> 
									</c:if>
									<c:if test="${empty requestScope.lname }">
										<input type="hidden" name="listVO.lnum" value="0"/>
									</c:if>
									</div>
										<script>
							const listBoxTag = document.getElementById('listBox');
							listBoxTag.addEventListener('click',function(){
								let formTag = document.Frm;
								let catnum = formTag.catnum.value;
								let returnPage = formTag.returnPage.value;
								const option 	= "width=570,height=680,location=no,resizable=no";
								let url = "http://localhost:9080/pfpkg/board/loadListForm/"
								url += catnum+"/"+returnPage;
								console.log(url);
								window.open(url,"첨부할 리스트 선택",option);								
								});
							</script>
							<!-- 리스트  -->
							
							
							
									<li><label for="bcontent"></label> 
								<input type="hidden" name="tcontent"	class="tcontent_area">
									<div class="content_area" contenteditable="true"	data-placeholder="내용을 입력하세요">${boardVO.tcontent }
								
									</div>
								 <form:errors cssClass="bound_error" path="tcontent"><c:if test="${requestScope.error }">${requestScope.error }</c:if></form:errors>								 
								 </li>

								<li>
									<div class="btnGrp">
										<button id="writeBtn" class="btn" type="button">등록</button>
										<button id="listBtn" class="btn" type="button">목록으로</button>
									</div>
								</li>

							</ul>
						</form:form>
					</div>
					<!-- section_wrap  -->
				</section>
				<!-- section -->
			</div>
		</main>
		<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
</body>
</html>