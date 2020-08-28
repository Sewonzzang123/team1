<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/member/loginForm.css">
</head>

<body>


	<div class="box-container">
		<!-- header -->
		<header>
			<%@ include file="/WEB-INF/views/layout/logo.jsp"%>
		</header>

		<!-- main -->
		<main id="page" class="signupForm">
			<form:form modelAttribute="info">
				<fieldset class="login_form">
					<div class="id_area">
						<div class="input_row">
							<input type="text" id="id" name="id" class="int"
								placeholder="아이디" maxlength="20">
						</div>
					</div>

					<div class="pw_area">
						<div class="input_row">
							<input type="password" id="pw" name="pw" class="int"
								placeholder="비밀번호" maxlength="10">
						</div>
					</div>
					<div>
						<span class="errmsg">${requestScope.svr_msg }</span>
					</div>
					<div class="actionBtn">
						<input type="submit" title="로그인" id="login" class="btn"
							value="로그인"
							formaction="${pageContext.request.contextPath}/login?reqURI=${reqURI}" />
					</div>
				</fieldset>
			</form:form>

			<div class="linkbar">
				<a href="${pageContext.request.contextPath}/findIDForm">아이디 찾기</a> |
				<a href="${pageContext.request.contextPath}/findPWForm">비밀번호 찾기</a>
				| <a href="${pageContext.request.contextPath}/signupForm">회원가입</a>
			</div>
		</main>
	</div>
	<!-- footer -->

</body>
</html>