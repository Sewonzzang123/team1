<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<style type="text/css">
#pw {
	width: 300px;
	height: 20px;
}

form {
	width: 300px;
	margin: auto;
	margin-top: 30px;
}

.form_head, .form_data {
	height: 30px;
}
</style>
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/layout/haeder.jsp"%>

	<div class="box-container">
		<!-- aside -->
		<%@ include file="/WEB-INF/views/mypage/mypage_sidebar.jsp"%>

		<!-- main -->
		<main id="content" class="withdrawForm">
			<div class="content_sub-title">회원탈퇴 신청</div>
			<div>
				- 비밀번호를 입력 후 확인을 클릭하시면 탈퇴가 완료됩니다. <br> - 게시판에 작성하신 글은 자동으로 삭제되지
				않습니다.
			</div>
			<div id="inputForm">
				<form method="post"
					action="${pageContext.request.contextPath}/mypage/withdraw">
					<div>
						<div class="form_head">
							<strong>아이디</strong>
						</div>
						<div class="form_data">${sessionScope.member.id }</div>
					</div>
					<div>
						<div class="form_head">
							<strong>비밀번호</strong>
						</div>
						<div class="form_data">
							<input id="pw" type="password" name="pw"><span
								class="err_msg">${requestScope.err_msg }</span>
						</div>
					</div>
					<div class="actionBtn">
						<input type="submit" value="확인"> <input type="button"
							value="취소" onclick="location.href = '${pageContext.request.contextPath}/mypage' ">
					</div>
				</form>
			</div>
		</main>
	</div>
	<!-- footer -->
	<footer>footer</footer>
</body>
</html>