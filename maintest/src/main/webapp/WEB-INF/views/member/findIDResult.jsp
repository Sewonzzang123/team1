<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/member/findIDPW.css">

<body>
	<!-- header -->
	<header>
		<%@ include file="/WEB-INF/views/layout/logo.jsp"%>
	</header>
	<main>
		<div class="container">
			<div class="find_tapmenu">
				<ul>
					<li class="selected"><a
						href="${pageContext.request.contextPath}/findIDForm">아이디 찾기</a></li>
					<li><a href="${pageContext.request.contextPath}/findPWForm">비밀번호
							찾기</a></li>
				</ul>
			</div>

			<fieldset class="findid_findpw">
				<div class="textarea">
					<br>
					<div>
						<span class="nametext">${requestScope.name }</span> 님의 아이디입니다.
					</div>
					<br> <br>
					<div>${requestScope.id }</div>
					<br>
				</div>
				<div class="actionBtn">
					<input type="button" title="로그인" id="login" class="btn"
						onclick="location.href = '${pageContext.request.contextPath}/loginForm' "
						value="로그인하기" />
				</div>
			</fieldset>
		</div>
	</main>
</body>
</html>