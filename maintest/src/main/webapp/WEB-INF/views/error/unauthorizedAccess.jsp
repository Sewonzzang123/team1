<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Unauthorized access</title>
</head>
<style>
.error_wrap {
	margin-top: 200px;
}

#false_img {
	width: 300px;
}

.img_area, .test_area {
	text-align: center;
}
</style>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/layout/uppermost.jsp"%>

	<div class="error_wrap">
		<div class="img_area">
			<img id="false_img" src="<%=request.getContextPath()%>/img/false.png"
				alt="">
		</div>
		<div class="test_area">
			<h1>허가되지 않은 접근입니다.</h1>
		</div>
	</div>

</body>
</html>