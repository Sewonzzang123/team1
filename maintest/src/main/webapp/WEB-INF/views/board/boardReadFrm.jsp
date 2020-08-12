<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
    <!-- 공통모듈 -->
 	<%@ include file="/WEB-INF/views/included/common_taglib.jsp"  %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 열람</title>
<link rel="stylesheet" href="${contextPath}/css/board/boardReadFrm.css" >
<script  defer type="text/javascript" src="${contextPath }/js/board/boardReadFrm.js" ></script>
</head>
<body>


	<main>
		<div class="container">
			<div class="content">
				<section>
					<h2>
						<a href="">
							게시글 제목
						</a>
					</h2>
					<hr>
					<form id="readModFrm" name="readModFrm" method="POST"
						action="${contextPath}/board/save">											
									<input type="hidden" id="bnum" name="bnum" value="${boardVO.bnum }"/>

						<ul>						
							<li class="selectGrp">													
							<select name="bcategory.catnum" 
								id="bcategory.catnum"disabled="disabled"><option class="readMode"  value="${boardVO.bcategory.catnum }" selected>${boardVO.bcategory.catname }</option>								
							
								<c:forEach var="bcate"  items="${bcategory }">
								<option    value="${bcate.catnum }">${bcate.catname }</option>
							 </c:forEach>
							</select> 
							
							<select name="hidcategory.hidnum" id="hidcategory.hidnum" disabled="disabled">	<option class="readMode" value="${boardVO.hidcategory.hidnum }"selected>${boardVO.hidcategory.hidname }</option>
												
												<c:forEach var="hid"  items="${hidcategory }">
												<option value="${hid.hidnum }">${hid.hidname }</option>			
												</c:forEach>
							</select>							
							
							</li>							
							<li><label for="">제목</label><input type="text" id="btitle"	name="btitle" value="${boardVO.btitle }" readonly></li>
								<li>
								<label for="">작성자</label><input type="text" id="bnickname"	name="bnickname" value="${boardVO.bnickname }" readonly/>
								작성일:<fmt:formatDate value="${boardVO.bcdate }" pattern="yy/MM/dd HH:mm"/>
								조회:<c:out value="${boardVO.bhits }"/>
								</li>
					<!-- 		<li><label for="">파일첨부</label><input type="file" id="files" name="files" multiple /></li> -->
								
							<li><textarea id="bcontent" name="bcontent" cols="30" rows="5" placeholder="내용 입력"  readonly>${boardVO.bcontent }</textarea></li>
							
							
						
							
							<li>							
							<div class="btnGrp">						
								<!--읽기모드 -->
								<button id="modifyBtn" type="button"  class="readMode">수정</button>
								<button id="deleteBtn" type="submit"  class="readMode">삭제</button>
								
								<!-- 수정모드 -->
									<button id="saveBtn" type="button" class="modifyMode" >저장</button>
									<button id="cancelBtn" type="button"  class="modifyMode">취소</button>
												
								
											<button id="listBtn" type="button">목록</button>						
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