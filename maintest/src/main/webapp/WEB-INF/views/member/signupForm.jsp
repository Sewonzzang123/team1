<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/member/signupForm.css">
</head>

<body>

	<!-- header -->
	<header>
		<%@ include file="/WEB-INF/views/layout/logo.jsp"%>
	</header>

	<!-- main -->
	<main id="page" class="signupForm">
		<form:form modelAttribute="memberVO">
			<table>
				<tr>
					<th class="table-head">*아이디 (이메일 형식)</th>
					<td class="table-data"><form:input path="id" class="info"
							maxlength="20" autocomplete="off"/> <form:errors path="id" class="err_msg" /> <br></td>
				</tr>

				<tr>
					<th class="table-head">*비밀번호</th>
					<td class="table-data"><form:password path="pw" class="info"
							maxlength="10" /> <form:errors path="pw" class="err_msg" /></td>
				</tr>

				<tr>
					<th class="table-head">*비밀번호 확인</th>
					<td class="table-data"><form:password path="pwc" class="info"
							maxlength="10" /> <form:errors path="pwc" class="err_msg" /></td>
				</tr>

				<tr>
					<th class="table-head">*이름</th>
					<td class="table-data"><form:input path="name" class="info"
							maxlength="10" autocomplete="off" /> <form:errors path="name" class="err_msg" /></td>
				</tr>

				<tr>
					<th class="table-head">*별명</th>
					<td class="table-data"><form:input path="nickname"
							class="info" maxlength="10" autocomplete="off" /> <form:errors path="nickname"
							class="err_msg" /></td>
				</tr>

				<tr class="table-head">
					<th>전화번호</th>
					<td class="table-data">
						<div id="telBox">
							<form:select path="tel1">
								<form:option value="010">010</form:option>
								<form:option value="070">070</form:option>
							</form:select>
							-
							<form:input path="tel2" class="txtTel" maxlength="4" autocomplete="off" />
							-
							<form:input path="tel3" class="txtTel" maxlength="4" autocomplete="off" />
						</div>
					</td>
				</tr>
			</table>
			<div class="actionBtn">
				<input type="submit" value="회원가입"
					formaction="${pageContext.request.contextPath}/signup" /> <input
					type="button" value="취소"
					onClick="location.href='${pageContext.request.contextPath}'" />
			</div>
		</form:form>
	</main>
</body>
</html>