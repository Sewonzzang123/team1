<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
 	<%@ include file="/WEB-INF/views/included/common.jsp"  %>
<title>답글 작성</title>
<link rel="stylesheet" 	href="${contextPath }/css/board/boardMainFrm.css?ver=2">	
<link rel="stylesheet" 	href="${contextPath}/css/board/boardWriteModifyFrm.css?ver=1">
<link rel="stylesheet" 	href="${contextPath}/css/board/button.css?ver=2"> 	
<%-- <link rel="stylesheet" href="${contextPath}/css/board/boardReplyFrm.css"> --%>
<script defer type="text/javascript"	src="${contextPath }/js/board/boardReplyFrm.js?ver=0"></script>
	 <link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" /> 
</head>
<body>
		<main>
			<!-- uppermost  메뉴  -->
		<%@ include file="/WEB-INF/views/layout/header.jsp"%>
		<!-- 메인 베너 _ 이미지  + hidden 요소-->
		<%@ include file="/WEB-INF/views/layout/mainbanner.jsp"%>		
		<div class="main_wrap">
			<!-- 게시판 카테고리 메뉴  -->
			<%@ include file="/WEB-INF/views/board/included/boardAsideMenu.jsp"%>
					<section>
						<div class="section_wrap">								
						
						
					<form:form id="Frm" name="Frm" method="POST" 	enctype="multipart/form-data"
						action="${contextPath}/board/reply">		
																					
									<input type="hidden" id="bnum" name="bnum" value="${boardVO.bnum }"/> 
									<input type="hidden" id="catnum" name="bcategory.catnum" value="${boardVO.bcategory.catnum }" />
									<input type="hidden" name="hidcategory.hidnum" value="${boardVO.hidcategory.hidnum }"/>
									<input type="hidden" name="ucode" value="${boardVO.ucode }"/>									
									<input type="hidden" name="bgroup" value="${boardVO.bgroup }"/>
									<input type="hidden" name="bstep" value="${boardVO.bstep }"/>
									<input type="hidden" name="bindent" value="${boardVO.bindent }"/>
									<input type="hidden" id="returnPage" name="returnPage" value="${returnPage}"/>
									
									
						<ul>						
							<li class="selectGrp">													
							<select name="bcategory.catnum" 
								id="bcategory.catnum"disabled="disabled">
								<option value="${boardVO.bcategory.catnum }" disabled="disabled" selected>${boardVO.bcategory.catname }</option>								
								</select> 							
							<select name="hidcategory.hidnum" id="hidcategory.hidnum" disabled="disabled">	
							<option class="readMode" value="${boardVO.hidcategory.hidnum }"selected>${boardVO.hidcategory.hidname }</option>													
							</select>							
							
							</li>							
									<li><input name="btitle" 
										type="text"  placeholder="제목을 입력하세요."  value="${boardVO.btitle }"/> 								
										 <form:errors
											cssClass="bound_error" path="btitle" value="${boardVO.btitle }"></form:errors></li> 
											
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
											<input type="file" name="files" id="pics"	multiple style="display: none;">
										</div></li>									
										
										<li><label for="bcontent"></label> 						
										<input type="hidden" name="tcontent"	class="tcontent_area">
									<div class="content_area" contenteditable="true"	data-placeholder="내용을 입력하세요">[답글]</div>
								 <form:errors cssClass="bound_error" path="tcontent"></form:errors>								 
								 </li>
								
								<li>					
								<div class="btnGrp">					
							
							<button id="replyBtn" type="button"  class="btn" >등록</button>
							<button id="listBtn" type="button"  class="btn" >목록으로</button> 
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