<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common.jsp"%>

<link rel="stylesheet"
	href="${contextPath }/css/board/boardMainFrm.css?ver=${today}">
<%-- <link rel="stylesheet"
	href="${contextPath}/css/board/boardWriteFrm.css?ver=${today}"> --%>
<script defer type="text/javascript"
	src="${contextPath }/js/board/boardWriteFrm.js?ver=${today}"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
</head>

<body>
	<main>
			<!-- uppermost  메뉴  -->
		<%@ include file="/WEB-INF/views/layout/header.jsp"%>
		<!-- 메인 베너 _ 이미지  + hidden 요소 catnum, returnPage-->
		<%@ include file="/WEB-INF/views/layout/mainbanner.jsp"%>
		<div class="main_wrap">
			<!-- 게시판 카테고리 메뉴  -->
			<%@ include file="/WEB-INF/views/board/included/boardAsideMenu.jsp"%>

			<div class="container">
				<div class="content">
					<section>
						<div class="section_wrap">
						
							<form:form id="writeFrm" name="writeFrm" method="POST"
								enctype="multipart/form-data"
								action="${contextPath}/board/write/" modelAttribute="boardVO">

								<input type="hidden" id="returnPage" name="returnPage"
									value="${requestScope.returnPage}" />
								<input type="hidden" id="ucode" name="ucode"
									value="${sessionScope.member.ucode  }" />
								<input type="hidden" id="catnum" name="catnum"
									value="${requestScope.bcategoryVO.catnum}" />
								<input type="hidden" id="hidnum" name="hidnum"
									value="${requestScope.boardVO.hidcategory.hidnum}" />

								<ul>
									<li class="selectGrp"><label for="bcategory.catnum">분류</label>
										<select name="bcategory.catnum" id="bcategory">
											<option value="0">게시판분류</option>
											<c:forEach var="bcate" items="${bcategoryList }">
												<c:if test="${bcate.catnum != 0}">
													<option value="${bcate.catnum }"
														<c:if test="${bcate.catnum == requestScope.catnum}">selected</c:if>>${bcate.catname }</option>
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
										<div class="bcontent_area" contenteditable="true">${boardVO.bcontent }</div>
										<form:errors cssClass="bound_error" path="bcontent"></form:errors></li>

									<li>
										<div class="attachment" id="filesBox">
											<input type="file" id="files" name="files"
												multiple="multiple" />
										</div>
									</li>

									<li>
										<div class="btnGrp">
											<button id="tmpWriteBtn" class="btn" type="button">임시저장</button>
											<button id="writeBtn22222222222222222" class="btn" type="button">등록</button>
											<button id="listBtn" class="btn" type="button">목록으로</button>
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
</html>