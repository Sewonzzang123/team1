<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>갤러리</title>
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
						<input type="hidden" id="btype" name="bcategory.btype"
							value="${boardVO.bcategory.btype}" />
						<input type="hidden" id="returnPage" name="returnPage"
							value="${returnPage}" />
						<input type="hidden" id="ucode" name="ucode"
							value="${sessionScope.member.ucode  }" />
						
							
						<ul>			
							<li>	
							
							<script type="text/javascript">
								
							</script>
							
								 <input type="file" id="thumbnail"	name="files" multiple="multiple" />							
								<div class="img_wrap">
									<div class="img_content">
										
												<!-- 보여줄 이미지 들어오는 공간 -->
													<li>
													<label for="btitle">제목</label> <input name="btitle" type="text" value="${article.btitle }"/> 
													</li>
							<li>
							<label for="bnickname">작성자</label> <input type="text"
								name="bnickname" readonly
								value="${articles.nickname }" /></li>
							
									</div>
								</div>
							
								
						
							
									<div class="btnGrp">
									<button id="tmpWriteBtn" type="button">임시저장</button>
									<button id="writeBtn" type="button">등록</button>
									<button id="listBtn" type="button">목록으로</button>
								</div>	</section>
			</div>
		</div>
	</main>


</body>
</html>