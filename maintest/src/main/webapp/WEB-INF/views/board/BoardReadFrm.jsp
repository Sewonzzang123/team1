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
<link rel="stylesheet" href="${contextPath}/css/board/BoardReadFrm.css" >
<script  defer type="text/javascript" src="${contextPath }/js/board/BoardReadFrm.js" ></script>
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
					<form id="writeFrm" name="writeFrm" method="POST"
						action="${contextPath}/board/write">
						
						<ul>
						
							<li class="selectGrp">						
							
							<select name="bcategory.catnum"
								id="bcategory.catnum">								
									<option value="">게시판분류</option>
									<option value="1">여행팁</option>
									<option value="2">Q&A</option>
									<option value="3">갤러리</option>									
							</select> 
							
							<select name="hidcategory.hidnum" id="hidcategory.hidnum">
									<option value="">말머리선택</option>
									<option value="1">말머리1</option>
									<option value="2">말머리2</option>
									<option value="3">말머리3</option>
							</select>
							</li>
							
							<li><label for="">제목</label><input type="text" id="btitle"	name="btitle" /></li>
								<li><label for="">아이디</label><input type="text" id="ucode"	name="ucode" /></li>
					<!-- 		<li><label for="">파일첨부</label><input type="file" id="files" name="files" multiple /></li> -->
								
							<li><textarea id="bcontent" name="bcontent" cols="30" rows="5" placeholder="내용 입력"></textarea></li>
							
							<li>							
							<div class="btnGrp">
								<button id="tmpWriteBtn" type="button">임시저장</button>
								<button id="writeBtn" type="button">등록</button>
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