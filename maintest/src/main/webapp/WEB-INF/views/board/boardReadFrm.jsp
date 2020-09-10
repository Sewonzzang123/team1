<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 열람</title>
<link rel="stylesheet"
	href="${contextPath}/css/board/boardReadFrm.css?ver=1">
<script defer type="text/javascript"
	src="${contextPath }/js/board/boardReadFrm.js?ver=1"></script>
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
					<form id="readModFrm" name="readModFrm" method="POST"
						enctype="multipart/form-data" action="${contextPath}/board/save">
						<input type="hidden" id="bnum" name="bnum"
							value="${requestScope.boardVO.bnum }" /> <input type="hidden"
							id="returnPage" name="returnPage"
							value="${requestScope.returnPage}" /> <input type="hidden"
							id="searchType" name="searchType"
							value="${requestScope.searchCriteria.searchType }" /> <input
							type="hidden" id="searchKeyword" name="searchKeyword"
							value="${requestScope.searchCriteria.searchKeyword }" />
						<ul>
							<li class="selectGrp"><select name="bcategory.catnum"
								id="bcategory" disabled="disabled"><option
										class="readMode"
										value="${requestScope.boardVO.bcategory.catnum }" selected>${requestScope.boardVO.bcategory.catname }</option>

									<c:forEach var="bcate" items="${requestScope.bcategoryList }">
										<option value="${bcate.catnum }">${bcate.catname }</option>
									</c:forEach>
							</select> <select name="hidcategory.hidnum" id="hidcategory"
								disabled="disabled">
									<option class="readMode" value="${boardVO.hidcategory.hidnum }"
										selected>${boardVO.hidcategory.hidname }</option>

									<c:forEach var="hid" items="${requestScope.hidcategoryList}">
										<option value="${hid.hidnum }">${hid.hidname }</option>
									</c:forEach>
							</select></li>
							<li><label for="">제목</label><input type="text" id="btitle"
								name="btitle" value="${requestScope.boardVO.btitle }" readonly></li>
							<li><label for="">작성자</label><input type="text"
								id="bnickname" name="bnickname"
								value="${requestScope.boardVO.bnickname }" readonly /></li>
							<li><label>작성일자</label> <fmt:formatDate
									value="${requestScope.boardVO.bcdate }"
									pattern="yy/MM/dd HH:mm" /></li>
							<li><label>조회수</label> <c:out
									value="${requestScope.boardVO.bhits }" /></li>



							<li>
								<div class="content_area" name="bcontent_area">${requestScope.boardVO.tcontent }</div>
							</li>
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
												<a href="${contextPath }/board/file/${file.fid}">${file.fname }</a><span>(${file.fsize/1024 }kb)</span>
												<a href="#" class="modifyMode"><i
													class="fas fa-minus-square" data-fid="${file.fid }"></i></a>
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
							<button id="modifyBtn" type="button" class="readMode">수정</button>

							<!-- 수정모드 -->
							<button id="saveBtn" type="button" class="modifyMode">저장</button>
							<button id="deleteBtn" type="submit" class="modifyMode">삭제</button>
							<button id="cancelBtn" type="button" class="modifyMode">취소</button>


							<button id="listBtn" type="button">목록</button>
						</div>



					</form>
				</section>
			</div>
		</div>
	</main>


</body>
</html>