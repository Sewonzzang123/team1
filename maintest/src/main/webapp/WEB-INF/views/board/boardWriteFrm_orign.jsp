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
<script defer  type="text/javascript" src="${contextPath }/js/board/boardWriteFrm.js?ver=1" ></script>
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
					modelAttribute="writeBoardVO"
					>
						
						<ul>						
							<li class="selectGrp">						
							<form:label path="bcategory.catnum">분류</form:label>
							<form:select path="bcategory.catnum" 	>								
									<option value="0">게시판분류</option>
									<form:options path="bcategory.catnum"  items="${bcategory }" itemValue="catnum" itemLabel="catname"/>
			 				</form:select> 
			 					<form:errors path="bcategory.catnum" cssClass="error"/>
							
							<form:select path="hidcategory.hidnum">
									<form:option value="0">말머리선택</form:option>
								<form:options path="hidcategory.hidnum" items="${hidcategory}" itemValue="hidnum" itemLabel="hidname"/>
								
							</form:select>
							<form:errors path="hidcategory.hidnum" cssClass="error"/>
							</li>
							
								<li>
								<form:label path="btitle" >제목</form:label>
								<form:input path="btitle" type="text" />	
								<form:errors path="btitle" cssClass="error"/></li>
			
								
								<li>
								<form:label path="ucode">작성자</form:label>
								<form:input type="text" path="ucode" />			
								<form:errors path="ucode" cssClass="error"/></li>
								
												
							<li><form:label path="bcontent">내용</form:label>
							<form:textarea path="bcontent" rows="10" placeholder="내용 입력"></form:textarea>
							<form:errors path="bcontent" cssClass="error"/></li>
								
								
								
							<li><form:label path="files">파일첨부</form:label>
							<form:input type="file" path="files"  multiple="true" /></li>
							
							<li>										
							<div class="btnGrp">
							<form:button id="tmpWriteBtn" type="button">임시저장</form:button>
							<form:button id="writeBtn" type="button">등록</form:button>
							<form:button id="listBtn" type="button">취소</form:button>
							</div></li>
							
						</ul>
					</form:form>
				</section>
			</div>
		</div>
	</main>
	
	
</body>
</html>