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

input {
	width: 131px;
	height: 20px;
	border: 1px solid #979797;
	box-sizing: border-box;
	font-size: 12px;
	color: #000;
	padding: 0 0 0 10px;
	vertical-align: middle;
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
	width: 120px;
	text-align: center;
	padding: 0;
}

th {
	width: 130px;
	color: #737373;
	text-align: left;
	padding-left: 17px;
	background: #EFEFEF;
	font-weight: normal;
}

th, td {
	padding: 10px 0 8px 20px;
	font-size: 11px;
	font-family: Dotum, '돋움';
	color: #333;
	line-height: 17px;
	line-height: 1.55em;
	border-bottom: 1px solid #E0E0E0;
}

.page {
	width: 900px;
}

/* 개인 폼 */
#page {
	width: 900px;
}

table {
	width: 450px;
	border-top: 2px solid #CBCBCB;
	border-bottom: 2px solid #CBCBCB;
	table-layout: fixed;
	border-collapse: collapse;
	border-spacing: 0;
	border-top: 2px solid #CBCBCB;
	margin: auto;
}

.title {
	text-align: center;
}

.signupForm ul, ol {
	padding: 0;
	list-style: none;
}

.txtTel {
	width: 40px;
}

#telBox {
	display: flex;
	align-items: center;
}

.info {
	width: 150px;
}
</style>
</head>

<body>


	<div class="box-container">
		<!-- aside -->


		<!-- main -->
		<main id="page" class="signupForm">
			<h1 class="title">회원 가입</h1>
			<form:form>
				<table>
					<tr>
						<th class="table-head">아이디</th>
						<td class="table-data"><input class="info" type="text"
							name="id" id="id">
							<div class="memo">*이메일 형식</div></td>

					</tr>

					<tr>
						<th class="table-head">이름</th>
						<td class="table-data"><input class="info" type="text"
							name="name" id="name"></td>
					</tr>

					<tr>
						<th class="table-head">비밀번호</th>
						<td class="table-data"><input class="info" type="text"
							name="pw" id="pw"></td>
					</tr>

					<tr>
						<th class="table-head">비밀번호 확인</th>
						<td class="table-data"><input class="info" type="text"
							name="pwc" id="pwc"></td>
					</tr>
					<tr>
						<th class="table-head">별명</th>
						<td class="table-data"><input class="info" name="nickname"
							id="nickname" type="text"></td>
					</tr>

					<tr class="table-head">
						<th>전화번호</th>
						<td class="table-data">
							<div id="telBox">
								<select id="tel1" name="tel1">
									<option value="010">010</option>
									<option value="070">070</option>
								</select> - <input id="tel2" name="tel2" class="txtTel" type="text">
								- <input id="tel3" name="tel3" class="txtTel" type="text">
							</div>
						</td>
					</tr>
				</table>
				<div class="actionBtn">
					<input type="submit" value="회원가입"
						formaction="${pageContext.request.contextPath}/signup" /> <input
						type="button" value="취소" onClick="location.href='/maintest'" />
				</div>
			</form:form>
		</main>
	</div>
	<!-- footer -->
	<footer>footer</footer>
</body>
</html>