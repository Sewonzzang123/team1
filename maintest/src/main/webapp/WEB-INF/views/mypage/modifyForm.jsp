<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mypage/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mypage/common.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mypage/modifyForm.css">
<style type="text/css">
</style>
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/layout/haeder.jsp"%>

	<div class="box-container">
		<!-- aside -->
		<%@ include file="/WEB-INF/views/mypage/mypage_sidebar.jsp"%>

		<!-- main -->
		<main id="content" class="modifyForm">

			<form:form modelAttribute="memberVO">
				<table>

					<!-- 고정 영역 -->
					<tr>
						<th class="table-head">아이디</th>
						<td class="table-data">${sessionScope.member.id }</td>
					</tr>
					<tr>
						<th class="table-head">이름</th>
						<td class="table-data">${sessionScope.member.name }</td>
					</tr>
					<form:form modelAttribute="memberVO">
						<!-- 비밀번호 영역 -->
						<tr>
							<th class="table-head">비밀번호 변경</th>
							<td class="table-data">
								<ul>
									<!-- 현재 비밀번호 -->
									<li class="myinfo_form">
										<div class="password-header">
											<label for="txtPassword" class="text__label"> 현재 비밀번호
											</label>
										</div>
										<div class="password-body">
											<input name="culpw" id="txtPassword" type="password" />
										</div>
									</li>

									<!-- 새 비밀번호 -->
									<li class="myinfo_form">
										<div class="password-header">
											<label for="txtNewPassword1" class="text__label"> 새
												비밀번호 </label>
										</div>
										<div class="password-body">
											<input name="nextpw" id="txtNewPassword1" type="password" />
										</div>
									</li>

									<!-- 새 비밀번호 확인 -->
									<li class="myinfo_form">
										<div class="password-header">
											<label for="txtNewPassword2" class="text__label"> 새
												비밀번호 확인 </label>
										</div>
										<div class="password-body">
											<input name="nextpwc" id="txtNewPassword2" type="password" />
										</div>
										<div class="password-body">
											<input class="mypage_modify_btn" type="submit"
												id="PWChangeBtn" value="비밀번호 변경"
												formaction="${pageContext.request.contextPath}/mypage/changePW">
										</div>
									</li>
								</ul>
							</td>
						</tr>
					</form:form>

					<!-- 수정 영역 -->
					<tr>
						<th class="table-head">별명</th>
						<td class="table-data"><form:input path="nickname"
								class="info" value="${sessionScope.member.nickname }"
								maxlength="10" /> <form:errors path="nickname" class="err_msg" /></td>
					</tr>
					<tr class="table-head">
						<th>전화번호</th>
						<td class="table-data"><div id="telBox">
								<form:select path="tel1">
									<form:option value="010">010</form:option>
									<form:option value="070">070</form:option>
								</form:select>
								-
								<form:input path="tel2" class="txtTel" maxlength="4"
									value="${sessionScope.member.tel2 }" />
								-
								<form:input path="tel3" class="txtTel" maxlength="4"
									value="${sessionScope.member.tel3 }" />

							</div></td>
					</tr>
				</table>
				<div class="actionBtn">
					<input type="submit" value="회원정보 수정"
						formaction="${pageContext.request.contextPath}/mypage/modify" />
					<input type="button" value="회원정보 취소"
						onClick="location.href='/maintest'" /> <input type="button"
						value="회원탈퇴 신청"
						onClick="location.href='/maintest/mypage/withdrawForm'" />
				</div>
			</form:form>
		</main>
	</div>
	<!-- footer -->
	<footer>footer</footer>
</body>
</html>