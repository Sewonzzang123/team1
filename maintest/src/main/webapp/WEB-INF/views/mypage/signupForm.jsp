<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<form:form modelAttribute="info">

				<table>
					<!-- 고정 영역 -->
					<tr>
						<th class="table-head">아이디</th>
						<td class="table-data">${sessionScope.member.id }</td>
					</tr>
					<tr>
						<th class="table-head">이름</th>
						<td class="table-data">${sessionScope.member.nickname }</td>
					</tr>

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
										<input name="txtPassword" id="txtPassword" type="password" />
									</div>
								</li>

								<!-- 새 비밀번호 -->
								<li class="myinfo_form">
									<div class="password-header">
										<label for="txtNewPassword1" class="text__label"> 새
											비밀번호 </label>
									</div>
									<div class="password-body">
										<input name="txtPassword" id="txtNewPassword1" type="password" />
									</div>
								</li>

								<!-- 새 비밀번호 확인 -->
								<li class="myinfo_form">
									<div class="password-header">
										<label for="txtNewPassword2" class="text__label"> 새
											비밀번호 확인 </label>
									</div>
									<div class="password-body">
										<input name="txtPassword" id="txtNewPassword2" type="password" />
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

					<!-- 수정 영역 -->
					<tr>
						<th class="table-head">별명</th>
						<td class="table-data"><input name="nickname" id="nickname"
							type="text" value="${sessionScope.member.nickname }"></td>
					</tr>
					<tr class="table-head">
						<th>전화번호</th>
						<td class="table-data"><div id="telBox">
								<select id="tel1" name="tel1">
									<option value="010">010</option>
									<option value="070">070</option>
								</select>-<input id="tel2" name="tel2" class="txtTel" type="text">-<input
									id="tel3" name="tel3" class="txtTel" type="text">
							</div></td>
					</tr>
				</table>
				<div class="actionBtn">
					<input type="submit" value="회원정보 수정"
						formaction="${pageContext.request.contextPath}/mypage/modify" />
					<input type="button" value="회원정보 취소"
						onClick="location.href='/portfolio'" /> <input type="button"
						value="회원탈퇴 신청"
						onClick="location.href='/portfolio/mypage/withdrawForm'" />
				</div>
			</form:form>
		</main>
	</div>
	<!-- footer -->
	<footer>footer</footer>
</body>
</html>