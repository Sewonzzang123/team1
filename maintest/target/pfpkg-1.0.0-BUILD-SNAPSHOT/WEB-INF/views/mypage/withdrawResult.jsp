<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원탈퇴 완료</title>
<style>
.result {
	text-align: center;
}
</style>
</head>
<body>
	<!-- header -->
	<header>
		<%@ include file="/WEB-INF/views/layout/logo.jsp"%>
	</header>
	<div class="content">
		<div class="result">
			<h3>회원탈퇴가 완료되었습니다.</h3>

			<div class="actionBtn">
				<input type="button" value="메인화면으로"
					onclick="location.href = '${pageContext.request.contextPath}'">
			</div>
		</div>
	</div>
</body>
</html>