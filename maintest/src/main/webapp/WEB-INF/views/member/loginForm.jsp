<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style>
button, input, select, textarea {
	font-family: Tahoma, Gulim, '굴림', sans-serif;
}

.actionBtn {
	align-content: center;
	text-align: center;
	margin-top: 20px;
}

.actionBtn>input {
	width: 450px;
	text-align: center;
	padding: 0;
	background-color: #5d4fad;
	border: 0;
	color: white;
}

.input_row {
	margin-bottom: 6px;
	padding: 8px 35px 9px 11px;
	position: relative;
	height: 29px;
	margin-bottom: 8px;
	padding: 7px 35px 10px 11px;
	border: solid 1px #dadada;
	background: #fff;
}

.int {
	width: auto;
	font-size: 14px;
	line-height: 16px;
	position: relative;
	z-index: 9;
	width: 100%;
	height: 16px;
	padding: 8px 0 6px;
	color: #000;
	border: none;
	background: #fff;
	-webkit-appearance: none;
}

.login_form {
	border: none;
	margin: auto;
	width: 450px;
}

.btn {
	margin-bottom: 12px;
	height: 40px;
	line-height: 40px;
	font-size: 18px;
	font-weight: 700;
}

.linkbar {
	align-content: center;
	text-align: center;
	margin-top: 20px;
}

.linkbar>a {
	text-decoration: none;
	color: #8e8e8e;
}

.title>a {
	text-decoration: none;
	color: #5d4fad;
}

.errmsg {
	color: red;
}
</style>
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
							value="로그인" formaction="${pageContext.request.contextPath}/login" />
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