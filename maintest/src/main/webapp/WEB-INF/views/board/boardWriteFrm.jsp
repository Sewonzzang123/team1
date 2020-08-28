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
<script defer type="text/javascript"
	src="${contextPath }/js/board/boardWriteFrm.js?ver=2"></script>

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
					<form:form id="writeFrm" name="writeFrm" method="POST"
						enctype="multipart/form-data" action="${contextPath}/board/write"
						modelAttribute="boardVO">



						<input type="hidden" id="returnPage" name="returnPage"
							value="${returnPage}" />
						<input type="hidden" id="ucode" name="ucode"
							value="${sessionScope.member.ucode  }" />
						<ul>
							<li class="selectGrp">
							<label for="bcategory.catnum">분류</label>
								<select name="bcategory.catnum" id="bcategory">
									<option value="0">게시판분류</option>
									<c:if test="${!boardVO.bcategory.btitle != '' }">
									<option value="${boardVO.bcategory.catnum }" selected>${boardVO.bcategory.catname }</option>
									</c:if>
									
									<c:forEach var="bcate" items="${bcategoryList }">
									<option value="${bcate.catnum }">${bcate.catname }</option>
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
								
								<li><label for="bnickname">작성자</label> <input type="text"
								name="bnickname" readonly
								value="${sessionScope.member.nickname }" /></li>
							<li><label for="bcontent"></label> <textarea
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


</body>
</html>