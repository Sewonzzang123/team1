<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common.jsp"%>
<title>게시글 수정</title>
<link rel="stylesheet"
	href="${contextPath }/css/board/boardMainFrm.css?ver=${today}">
<link rel="stylesheet"
	href="${contextPath}/css/board/boardWriteModifyFrm.css?ver=${today}">
<link rel="stylesheet" href="${contextPath}/css/board/button.css?ver=2">
<script defer type="text/javascript"
	src="${contextPath }/js/board/boardModifyFrm.js?ver=${today}"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
</head>
<body>
	<main>
		<!-- uppermost  메뉴  -->
		<%@ include file="/WEB-INF/views/layout/header.jsp"%>
		<!-- 메인 베너 _ 이미지 -->
		<%@ include file="/WEB-INF/views/layout/mainbanner.jsp"%>
		<div class="main_wrap">
			<!-- 게시판 카테고리 메뉴  -->
			<%@ include file="/WEB-INF/views/board/included/boardAsideMenu.jsp"%>
			<section>
				<div class="section_wrap">
					<form:form id="Frm" name="Frm" method="POST"
						enctype="multipart/form-data" action="${contextPath}/board/modify"
						modelAttribute="boardVO">
						<input type="hidden" id="bnum" name="bnum"
							value="${requestScope.boardVO.bnum }" />
						<input type="hidden" id="ucode" name="ucode"
							value="${requestScope.boardVO.ucode }" />
						<input type="hidden" id="returnPage" name="returnPage"
							value="${requestScope.returnPage}" />
						<input type="hidden" id="searchType" name="searchType"
							value="${requestScope.searchCriteria.searchType }" />
						<input type="hidden" id="searchKeyword" name="searchKeyword"
							value="${requestScope.searchCriteria.searchKeyword }" />
						<input type="hidden" id="catnum" name="catnum"
							value="${requestScope.boardVO.bcategory.catnum }" />
						<%-- <input				type="hidden" id="reqPage" name="reqPage" value="${requestScope.reqPage }" />		 --%>
						<input type="hidden" id="hidnum" name="hidnum"
							value="${requestScope.boardVO.hidcategory.hidnum}" />


						<ul>
							<li class="selectGrp"><select name="bcategory.catnum"
								id="bcategory">
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
							
							
							<li><input name="btitle" type="text"
								value="${boardVO.btitle }" placeholder="제목을 입력하세요." /> <form:errors
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
									 <input type="hidden" name="thumbnail" class="thumbnail" value="null">
									 	<input type="file" name="files" id="pics"	multiple style="display:none;">
									 			 
								</div></li>

							<li><label for="bcontent"></label> 

								<input type="hidden" name="tcontent"	class="tcontent_area">
									<div class="content_area" contenteditable="true"	data-placeholder="내용을 입력하세요">${boardVO.tcontent }</div>
								 <form:errors cssClass="bound_error" path="tcontent"></form:errors>								 
								 </li>

							<li>
								<div class="btnGrp">
									<button id="modifyBtn" class="btn" type="button">저장</button>
									<button id="cancelBtn" class="btn article_list_btn" 	type="button">취소</button>
								</div>
							</li>
						</ul>
					</form:form>
				</div>
			</section>
		</div>
	</main>
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
</body>

</html>