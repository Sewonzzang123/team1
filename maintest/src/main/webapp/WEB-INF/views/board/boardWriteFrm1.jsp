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

<h1>TEST </h1>
	<main>
		<div class="container">
			<div class="content">
		
		
		
			<form method="post" action="${contextPath }/testDO">
			
			<label for="">btitle</label>
			<input type="text" name="btitle" value="${testVO.btitle }" />
			<label for="">bnum</label>
			<input type="text" name="bnum" value="${testVO.bnum }" />
			<input type="submit" value="보내기"/>
			</form>
			<%-- <form:form method="post"  action="${contextPath }/testDO">
			
			<form:input type="text" path="btitle" />
			
			
			<input type="submit" value="보내기"/>
			
			</form:form> --%>
			</div>
		</div>
	</main>
	
	
</body>
</html>