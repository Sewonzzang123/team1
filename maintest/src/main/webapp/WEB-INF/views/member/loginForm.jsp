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
html {
	font-family: Tahoma, Gulim, '굴림';
	font-size: 12px;
	line-height: 1.3;
	color: #333333;
	-webkit-text-size-adjust: 100%;
	-ms-text-size-adjust: 100%;
}

header {
	height: 150px;
	line-height: 180px;
}

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
}

.title {
	text-align: center;
}

.input_row {
	margin-bottom: 6px;
	padding: 8px 35px 9px 11px;
}

.input_row {
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
	width: 300px;
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
</style>
</head>

<body>


	<div class="box-container">
		<!-- header -->
		<header>
			<h1 class="title">
				<a href="#">PACKING</a>
			</h1>
		</header>

		<!-- main -->
		<main id="page" class="signupForm">

			<form:form modelAttribute="info">

				<fieldset class="login_form">
					<div class="id_area">
						<div class="input_row">
							<input type="text" id="id" name="id" class="int"
								placeholder="아이디" maxlength="41">
						</div>
					</div>

					<div class="pw_area">
						<div class="input_row">
							<input type="password" id="pw" name="pw" class="int"
								placeholder="비밀번호" maxlength="41">
						</div>
					</div>
					<div class="actionBtn">
						<input type="submit" title="로그인" id="login" class="btn"
							value="로그인" formaction="${pageContext.request.contextPath}/login" />
					</div>
				</fieldset>
			</form:form>

			<div class="linkbar">
				<a href="#">아이디 찾기</a> | <a href="#">비밀번호 찾기</a> | <a href="#">회원가입</a>
			</div>
		</main>
	</div>
	<!-- footer -->
	<footer>footer</footer>
</body>
</html>