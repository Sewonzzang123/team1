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
<!-- css/javascript  -->
<link rel="stylesheet"	href="${contextPath }/css/board/boardMainFrm.css?ver=${today}">
<link rel="stylesheet"
	href="${contextPath}/css/board/boardWriteFrm.css?ver=${today}">
<script defer type="text/javascript"
	src="${contextPath }/js/board/boardWriteFrm.js?ver=${today}"></script>

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
		
		<div class="container">
			<div class="content">
				<section>
				<div class="section_wrap">
					<h2>
						<a href=""> 게시글 작성 </a>
					</h2>					
					<form:form id="writeFrm" name="writeFrm" method="POST"
						enctype="multipart/form-data" action="${contextPath}/board/write"
						modelAttribute="boardVO">
						
						<input type="hidden" id="returnPage" name="returnPage"
							value="${requestScope.returnPage}" />
						<input type="hidden" id="ucode" name="ucode"
							value="${sessionScope.member.ucode  }" />
							
					
		<input type="hidden" id="catnum" name="catnum"
		value="${requestScope.bcategoryVO.catnum}" />				
							
							
						<ul>
							<li class="selectGrp">
							<label for="bcategory.catnum">분류</label>
								<select name="bcategory.catnum" id="bcategory">
										<option value="0">게시판분류</option>															
									<c:forEach var="bcate" items="${bcategoryList }">
									<c:if test="${bcate.catnum != 0}">
									<option value="${bcate.catnum }">${bcate.catname }</option>
									</c:if>
									</c:forEach>
									
									</select> 							
							<select name="hidcategory.hidnum" id="hidcategory">
									<option value="">말머리분류</option>
							<%-- 		<c:forEach var="hcate" items="${hidcategoryList }">
										<option value="${hcate.hidnum }">${hcate.hidname }</option>
									</c:forEach> --%>
							</select>
								<form:errors cssClass="bound_error" path="bcategory.catnum"></form:errors>	
							</li>
							<li><label for="btitle">제목</label> 
							<input name="btitle" type="text" value="${boardVO.btitle }"/> 
								<form:errors cssClass="bound_error" path="btitle"></form:errors>		</li>
								
				
							<li><label for="bcontent"></label>
							
 <textarea
									name="bcontent" rows="10" placeholder="내용 입력">${boardVO.bcontent }</textarea> 
										<form:errors cssClass="bound_error" path="bcontent"></form:errors>			
										
										
										
																	
									</li>

							<li>
								<div class="attachment" id="filesBox">
									<label for="files">파일첨부</label> <input type="file" id="files"
										name="files" multiple="multiple" />
								</div>
								<div class="img_wrap">
									<div class="img_content">
										
												<!-- 보여줄 이미지 들어오는 공간 -->
											
									</div>
								</div>
							</li>
							

							<li>
								<div class="btnGrp">
									<button id="tmpWriteBtn"  class="btn" type="button">임시저장</button>
									<button id="writeBtn" class="btn"  type="button">등록</button>
									<button id="listBtn" class="btn"  type="button">목록으로</button>
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