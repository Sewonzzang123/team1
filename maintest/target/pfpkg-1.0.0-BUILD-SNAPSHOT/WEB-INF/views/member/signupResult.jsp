<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입 완료</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/member/signupResult.css">
</head>

<body>

	<!-- header -->
	<header>
		<%@ include file="/WEB-INF/views/layout/logo.jsp"%>
	</header>

	<!-- main -->
	<main id="page" class="signupForm">

		<h1 class="text1">환영합니다.</h1>


		<div class="text2">
			<span>${sessionScope.member.name }</span>님의 회원가입을 축하합니다.
		</div>
		<div class="text2">
			새로운 아이디는 <span>${sessionScope.member.id }</span> 입니다.
		</div>

		<div class="actionBtn">
			<input type="button" class="btn" value="시작하기"
				onclick="location.href = '${pageContext.request.contextPath}'" />
		</div>
	</main>

</body>
</html>