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
<script>
	window.onload = function() {
		const PWChangeBtn = document.getElementById('PWChangeBtn');
		PWChangeBtn.addEventListener('click', PWChangeBtn_f);

	}

	function PWChangeBtn_f() {
		var curpw = document.getElementById('curpw');
		var nextpw = document.getElementById('nextpw');
		var nextpwc = document.getElementById('nextpwc');

		var curpw_msg = document.getElementById('curpw_msg');
		var nextpw_msg = document.getElementById('nextpw_msg');

		curpw_msg.innerText = '';
		nextpw_msg.innerText = '';

		if (curpw.value.trim().length == 0) {
			curpw_msg.innerText = '*현재 비밀번호를 입력해주세요.';
			curpw.focus();
			return;
		}

		if (curpw.value != ${sessionScope.member.pw }) {
			curpw_msg.innerText = '*현재 비밀번호가 일치하지 않습니다.';
			curpw.focus();
			return;
		}

		if (nextpw.value.trim().length == 0) {
			nextpw_msg.innerText = '*새 비밀번호를 입력해주세요.';
			nextpw.focus();
			return;
		}

		if (nextpw.value.trim().length < 6) {
			nextpw_msg.innerText = '*비밀번호는 6자리 이상입니다.';
			nextpw.focus();
			return;
		}

		if (nextpw.value != nextpwc.value) {
			nextpw_msg.innerText = '*새 비밀번호를 확인해주세요.';
			nextpwc.focus();
			return;
		}

		
		var ajax = new XMLHttpRequest();
		ajax.onreadystatechange = function() {
			if (ajax.readyState === ajax.DONE) {
				if (ajax.status === 200 || ajax.status === 201) {
					console.log(ajax.responseText);
				} else {
					console.error(ajax.responseText);
				}
			}
		};
		ajax.open("POST", "/maintest/mypage/changePW"); //
		ajax.send(nextpw.value);
		
		curpw.value = '';
		nextpw.value = '';
		nextpwc.value = '';
		alert('비밀번호가 변경되었습니다.')
	}
</script>
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/layout/haeder.jsp"%>

	<div class="box-container">
		<!-- aside -->
		<%@ include file="/WEB-INF/views/mypage/mypage_sidebar.jsp"%>

		<!-- main -->
		<main id="content" class="modifyForm">
			<div class="content_sub-title">회원정보수정</div>
			<form method="post">
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
					<!-- 비밀번호 영역 -->
					<tr>
						<th>비밀번호 변경</th>
						<td class="table-data">
							<ul>
								<li class="myinfo_form">
									<div class="password-header">
										<label for="txtPassword" class="text__label"> 현재 비밀번호
										</label>
									</div>
									<div class="password-body">
										<input name="curpw" id="curpw" type="password"></Input>


									</div> <span class="err_msg" id="curpw_msg"></span>
								</li>

								<li class="myinfo_form">
									<div class="password-header">
										<label for="txtNewPassword1" class="text__label"> 새
											비밀번호 </label>
									</div>
									<div class="password-body">
										<input name="nextpw" id="nextpw" type="password"></Input>
									</div> <span class="err_msg" id="nextpw_msg"></span>
								</li>

								<li class="myinfo_form">
									<div class="password-header">
										<label for="txtNewPassword2" class="text__label"> 새
											비밀번호 확인 </label>
									</div>
									<div class="password-body">
										<input name="nextpwc" id="nextpwc" type="password"></Input>
									</div>
									<div class="password-body">
										<input class="mypage_modify_btn" type="button"
											id="PWChangeBtn" value="비밀번호 변경">
									</div>
								</li>

							</ul>
						</td>
					</tr>

					<!-- 수정 영역 -->
					<tr>
						<th class="table-head">별명</th>
						<td class="table-data"><input name="nickname" class="info"
							value="${sessionScope.member.nickname }" maxlength="10" /><span
							class="err_msg">${requestScope.err_msg }</span>
					</tr>
					<tr class="table-head">
						<th>전화번호</th>
						<td class="table-data"><div id="telBox">
								<select name="tel1">
									<option value="010">010</option>
									<option value="070">070</option>
								</select> - <input type="text" name="tel2" class="txtTel" maxlength="4"
									value="${sessionScope.member.tel2 }" /> - <input type="text"
									name="tel3" class="txtTel" maxlength="4"
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
			</form>
		</main>
	</div>
	<!-- footer -->
	<footer>footer</footer>
</body>
</html>