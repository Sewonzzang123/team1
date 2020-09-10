<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common.jsp"%>

<title>게시글 열람</title>
<link rel="stylesheet"
	href="${contextPath}/css/board/boardReadFrm.css?ver=1">
<script defer type="text/javascript"
	src="${contextPath }/js/board/boardReadFrm.js?ver=1"></script>
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

			<section>
				<div class="section_wrap">
					<div class="section_write">
						<button type="button" class="writeBtn" id="writeBtn">글쓰기</button>
					</div>
					<div class="section_table">


						<div class="container">
							<div class="content">

								<hr>
								<form id="readModFrm" name="readModFrm" method="POST"			enctype="multipart/form-data"	action="${contextPath}/board/save">
									<input type="hidden" id="bnum" name="bnum" 	value="${requestScope.boardVO.bnum }" /> 
									<input		type="hidden" id="ucode" name="ucode" 	value="${requestScope.boardVO.ucode }" />
										<input type="hidden" id="returnPage" name="returnPage"	value="${requestScope.returnPage}" /> 
										<input type="hidden" id="searchType" name="searchType"		value="${requestScope.searchCriteria.searchType }" />
										 <input	type="hidden" id="searchKeyword" name="searchKeyword"  	value="${requestScope.searchCriteria.searchKeyword }" /> 
										<input		type="hidden" id="catnum" name="catnum" 	value="${requestScope.bcategoryVO.catnum }" />
										<input		type="hidden" id="reqPage" name="reqPage" 	value="1" />
										

									<ul>
										<li class="selectGrp">
										<select name="bcategory.catnum"
											id="bcategory" disabled="disabled">
											<option	class="readMode"		value="${requestScope.boardVO.bcategory.catnum }" selected>${requestScope.boardVO.bcategory.catname }</option>
												<c:forEach var="bcate"	items="${requestScope.bcategoryList }">
													<option value="${bcate.catnum }">${bcate.catname }</option>
												</c:forEach>
										</select> <select name="hidcategory.hidnum" id="hidcategory"
											disabled="disabled">
												<option class="readMode"	value="${boardVO.hidcategory.hidnum }" selected>${boardVO.hidcategory.hidname }</option>
												<c:forEach var="hid" items="${requestScope.hidcategoryList}">
													<option value="${hid.hidnum }">${hid.hidname }</option>
												</c:forEach>
										</select></li>
										<li><label for="">제목</label><input type="text"
											id="btitle" name="btitle"
											value="${requestScope.boardVO.btitle }" readonly></li>
										<li><label for="">작성자</label><input type="text"		id="bnickname" name="bnickname"
											value="${requestScope.boardVO.bnickname }" readonly /></li>
										<li><label>작성일자</label> <fmt:formatDate	value="${requestScope.boardVO.bcdate }"
												pattern="yy/MM/dd HH:mm" /></li>
										<li><label>조회수</label> <c:out	value="${requestScope.boardVO.bhits }" /></li>

										<li><textarea id="bcontent" name="bcontent" cols="30" rows="5" placeholder="내용 입력" readonly>${requestScope.boardVO.bcontent }</textarea></li>
										<li><c:if test="${empty requestScope.files }">
												<label for="">첨부목록</label>
												<div>
													<p>첨부파일 없음</p>
												</div>
											</c:if> <c:if test="${requestScope.files != null}">
												<label for="">첨부목록</label>
												<div id="attachments">
													<c:forEach var="file" items="${requestScope.files }">
														<div class="attchfiles">
															<a
																href="${contextPath }/board/file/${file.fid}/${file.isthumb}">${file.fname }</a><span>(${file.fsize/1024 }kb)</span>
															<a href="#" class="modifyMode"><i
																class="fas fa-minus-square" data-fid="${file.fid }"
																data-isthumb="${file.isthumb} "></i></a>
														</div>
													</c:forEach>
												</div>
											</c:if></li>
									</ul>

									<div class="modifyMode attachments">
										<label for="">파일추가</label> <input type="file" id="files"
											name="files" multiple="multiple" />
									</div>

									<div class="btnGrp">
										<!--읽기모드 -->
										<button id="peplyBtn" type="button" class="readMode">답글</button>
										<c:if test = "${requestScope.boardVO.ucode == sessionScope.member.ucode }" >
										<button id="modifyBtn" type="button" class="readMode">수정</button>
										</c:if>
										
										<!-- 수정모드 -->
										<button id="saveBtn" type="button" class="modifyMode">저장</button>
										<button id="deleteBtn" type="submit" class="modifyMode">삭제</button>
										<button id="cancelBtn" type="button" class="modifyMode">취소</button>
										
										<button id="listBtn" type="button">목록</button>
									</div>
								</form>
								
								
								
								<%@ include file="/WEB-INF/views/board/boardReplyInner.jsp" %>
								
								
								
							</div>
						</div>



					</div>
				</div>
			</section>
		</div>
	</main>
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>

</body>
</html>