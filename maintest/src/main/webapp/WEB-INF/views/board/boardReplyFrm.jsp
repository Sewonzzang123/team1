<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
 	<%@ include file="/WEB-INF/views/included/common.jsp"  %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>답글 작성</title>
<link rel="stylesheet" href="${contextPath}/css/board/boardReplyFrm.css">
<script defer type="text/javascript"
	src="${contextPath }/js/board/boardReplyFrm.js?ver=0"></script>
</head>
<body>


	<main>
		<div class="container">
			<div class="content">
				<section>
					<h2>
						<a href=""> 답글 작성하기 </a>
					</h2>
					<hr>
					<form id="replyFrm" name="replyFrm" method="POST"
						action="${contextPath}/board/reply">											
									<%-- <input type="hidden" id="bnum" name="bnum" value="${boardVO.bnum }"/> --%>
									<input type="hidden" name="bcategory.catnum" value="${boardVO.bcategory.catnum }" />
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
							<li><label for="">제목</label><input type="text" id="btitle"	name="btitle" value="${boardVO.btitle }" ></li>
								<li>
									<li>
								<label for="">작성자</label><input type="text" id="bnickname"	name="bnickname" value="${boardVO.bnickname }" readonly/>
								</li>
										<li>
								<label>작성일자</label><fmt:formatDate value="${boardVO.bcdate }" pattern="yy/MM/dd HH:mm"/>		</li>
								<li>	<label>조회수</label><c:out value="${boardVO.bhits }"/>	</li>
								
								
					<!-- 		<li><label for="">파일첨부</label><input type="file" id="files" name="files" multiple /></li> -->
								
							<li><textarea id="bcontent" name="bcontent" cols="30" rows="5" placeholder="내용 입력"    >${boardVO.bcontent }</textarea></li>
					
														
								<div class="btnGrp">					
							<button id="tmpWriteBtn" type="button">임시저장</button>
							<button id="replyBtn" type="button">등록</button>
							<button id="listBtn" type="button">목록으로</button> 
								</div>
							</li>
						</ul>
					</form>
				</section>
			</div>
		</div>
	</main>


</body>
</html>