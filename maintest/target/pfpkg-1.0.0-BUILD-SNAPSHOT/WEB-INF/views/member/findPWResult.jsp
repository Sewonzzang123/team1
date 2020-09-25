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
					<li><a href="${pageContext.request.contextPath}/findIDForm">아이디
							찾기</a></li>
					<li class="selected"><a
						href="${pageContext.request.contextPath}/findPWForm">비밀번호 찾기</a></li>
				</ul>
			</div>

			<fieldset class="findid_findpw">
				<div class="textarea">
					<br>
					<div>
						<span class="nametext">${requestScope.id }</span>
					</div>

					<br>
					<div>로 비밀번호가 전송되었습니다.</div>
					<br>
				</div>
				<div class="actionBtn">
					<input type="submit" title="로그인" id="login" class="btn"
						value="로그인하기"
						onclick="location.href = '${pageContext.request.contextPath}/loginForm' " />
				</div>
			</fieldset>
		</div>
	</main>
</body>
</html>