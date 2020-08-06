<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
    <!-- 공통모듈 -->
 	<%@ include file="/WEB-INF/views/included/common_taglib.jsp"  %>
 	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 작성</title>
<link rel="stylesheet" href="${contextPath}/css/board/boardWriteFrm.css" >
<script type="text/javascript" src="${contextPath }/js/board/boardWriteFrm.js" defer></script>
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
					<form:form
					 id="writeFrm" 
					name="writeFrm"
					method="POST"
					action="${contextPath}/board/write" 
					enctype="multipart/form-data"
					modelAttribute="boardVO">
						
						<ul>						
							<li class="selectGrp">						
							
							<form:select path="bcategory.catnum"	>								
									<option value="">게시판분류</option>
									<option value="1">여행팁</option>
									<option value="2">Q&A</option>
									<option value="3">갤러리</option>									
							</form:select> 
							
							<form:select path="hidcategory.hidnum">
									<form:option value="">말머리선택</form:option>
									<form:option value="1">말머리1</form:option>
									<form:option value="2">말머리2</form:option>
									<form:option value="3">말머리3</form:option>
							</form:select>
							</li>
							
								<li><form:label path="">제목</form:label>
								<form:input type="text" path="btitle"	 /></li>
								<form:errors path="btitle" cssClass="error"/>
								
								<li><form:label path="">작성자</form:label>
								<form:input type="text" path="ucode" /></li>			
									<form:errors path="ucode" cssClass="error"/>				
							<li>
							<form:textarea path="bcontent" cols="30" rows="5" placeholder="내용 입력"></form:textarea>
								<form:errors path="bcontent" cssClass="error"/>
								</li>
							<li><form:label path="">파일첨부</form:label>
							<form:input type="file" path="files"  multiple="true" /></li>
							<li>							
							<div class="btnGrp">
								<form:button id="tmpWriteBtn" type="button">임시저장</form:button>
								<form:button id="writeBtn" type="button">등록</form:button>
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