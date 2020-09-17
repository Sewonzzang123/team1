<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common.jsp"%>

<title>게시글 열람</title>
<link rel="stylesheet" 	href="${contextPath}/css/board/boardMainFrm.css?ver=1"> 
<link rel="stylesheet" 	href="${contextPath}/css/board/boardReadFrm.css?ver=1">
<link rel="stylesheet" 	href="${contextPath}/css/board/button.css?ver=2"> 


<script  defer type="text/javascript" src="${contextPath }/js/board/boardReadFrm.js?ver=1"></script>

</head>
<body>
	<main>
		<!-- uppermost  메뉴  -->
		<%@ include file="/WEB-INF/views/layout/header.jsp"%>
		<!-- 메인 베너 _ 이미지  + hidden 요소 catnum, reqPage-->
		<%@ include file="/WEB-INF/views/layout/mainbanner.jsp"%>
		<div class="main_wrap">
			<!-- 게시판 카테고리 메뉴  -->
			<%@ include file="/WEB-INF/views/board/included/boardAsideMenu.jsp"%>

			<div class="acticle">
				<div class="acticle_wrap">
					<div class="hidden_area">					
					
						<input type="hidden" id="bnum" name="bnum" value="${requestScope.boardVO.bnum }" /> 			
							<input type="hidden"				id="ucode" name="ucode" value="${requestScope.boardVO.ucode }" />
							<input type="hidden" id="returnPage" name="returnPage"		value="${requestScope.returnPage}" />
							 <input type="hidden"			id="searchType" name="searchType"		value="${requestScope.searchCriteria.searchType }" /> 
							 <input		type="hidden" id="searchKeyword" name="searchKeyword"			value="${requestScope.searchCriteria.searchKeyword }" /> 
							<input			type="hidden" id="catnum" name="catnum"	value="${requestScope.boardVO.bcategory.catnum }" /> 
							<%-- <input				type="hidden" id="reqPage" name="reqPage" value="${requestScope.reqPage }" />		 --%>
							<input type="hidden" id="hidnum" name="hidnum"				value="${requestScope.boardVO.hidcategory.hidnum}" />
																				
							
					</div>
					
		<div class="article_top_btn">
						<div class="left_area">
							<input type="button" class="btn article_rep_btn article_btn"
									value="답글"></input>
							<c:if
								test="${requestScope.boardVO.ucode == sessionScope.member.ucode}">
								<input type="button" class="btn article_del_btn article_btn"
									value="수정"></input>
								<input type="button" class="btn article_mod_btn article_btn"
									value="삭제"></input>
							</c:if>
						</div>
						<div class="right_area">
							<input type="button" class=" btn  article_list_btn article_btn"
								value="목록"></input>
						</div>
					</div> 

					
					<div class="article_contentBox">
						<div class="article_header">
							<div class="article_title">
							
								<div class="title_area">
									<h3 class="title_text">${requestScope.boardVO.btitle }</h3>
								</div>
							</div>
							<div class="writerInfo">
								<div class="profile_area">
									<div class="nick_box">${requestScope.boardVO.bnickname }</div>
								</div>
								<div class="article_info">
									<span class="date"> <fmt:formatDate
											value="${requestScope.boardVO.bcdate }"
											pattern="yy/MM/dd HH:mm" /></span> <span class="count">조회
										${requestScope.boardVO.bhits }</span>
								</div>
							</div>
						</div>
						<div class="article_container">
							<div class="AttachFileList">
								<c:if test="${requestScope.files != null}">
									<div class="attach_file">
										<a href="#" class="button_file" role="button">첨부파일</a>
										<div class="layer_attach" style="display: none;">
											<ul class="list">
												<c:forEach var="file" items="${requestScope.files }">
													<li class="AttachFileListItem">
														<div class="file_name">
															<a
																href="${contextPath }/board/file/${file.fid}/${file.isthumb}">${file.fname
                                  }</a>
														</div> <span>(${file.fsize/1024 }kb)</span> <a href="#"
														class="modifyMode"><i class="fas fa-minus-square"
															data-fid="${file.fid }" data-isthumb="${file.isthumb} "></i></a>
													</li>
												</c:forEach>
											</ul>
										</div>
									</div>
								</c:if>
							</div>
							<div class="main_container">
								<div class="article_viewer">
									${requestScope.boardVO.tcontent }</div>
							</div>

							<div class="comments_container">
								<div class="comment_option">
									<h3 class="comment_title">댓글</h3>
	
																		
					<%@ include file="/WEB-INF/views/board/boardReplyInner.jsp" %>
									
									

								</div>
							</div>
						</div>
					</div>
					<div class="article_bottom_btn">
						<div class="left_area">
							<c:if
								test="${requestScope.boardVO.ucode == sessionScope.member.ucode}">
								<input type="button" class="btn  article_del_btn article_btn"
									value="수정"></input>
								<input type="button" class=" btn  article_mod_btn article_btn"
									value="삭제"></input>
							</c:if>
						</div>
						<div class="right_area">
							<input type="button" class="btn article_list_btn article_btn"
								value="목록"></input> <input type="button"
								class=" btn  article_gotop_btn article_btn" value="TOP"></input>
						</div>
					</div>
				</div>
				
			
									
				
				
			</div>
		</div>
	</main>
	
	
	
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
</body>


</html>