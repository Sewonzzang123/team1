<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style>
header {
	height: 150px;
	line-height: 180px;
}

.title {
	text-align: center;
}

.title>a {
	text-decoration: none;
	color: #5d4fad;
}

html {
	font-family: Tahoma, Gulim, '굴림';
	font-size: 12px;
	line-height: 1.3;
	color: #333333;
	-webkit-text-size-adjust: 100%;
	-ms-text-size-adjust: 100%;
}

.text1 {
	text-align: center;
	font-weight: bold;
	font-size: 50px;
}

.text2 {
	text-align: center;
	font-weight: bolder;
}

.actionBtn {
	text-align: center;
	margin-top: 50px;
}

.btn {
	width: 300px;
	margin-bottom: 12px;
	height: 40px;
	line-height: 40px;
	font-size: 18px;
	font-weight: 700;
	margin: auto;
}

span {
	color: blue;
}
</style>
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