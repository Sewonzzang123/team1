<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${contextPath }/maintest/css/mypage/main.css">
<link rel="stylesheet"
	href="${contextPath }/maintest/css/mypage/common2.css">
<style type="text/css">
#member_exit {
	width: 100px;
	display: inline-block;
}

#member_exit>input {
	width: 100px;
}

#box1 {
	display: flex;
	align-items: center;
	justify-content: space-between;
}

#box2 {
	width: 370px;
	display: flex;
	align-items: center;
	justify-content: space-around;
}
</style>
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/layout/haeder.jsp"%>

	<div class="box-container">
		<!-- aside -->
		<%@ include file="/WEB-INF/views/mypage/admin_sidebar.jsp"%>

		<!-- main -->
		<main class="content" id="adminMember">
			<div id="adminMember_action">
				<div id="box1">
					<div id="box2">
						회원 검색 <select name="" id="field">
							<option value="id">아이디</option>
							<option value="id">닉네임</option>
						</select> <input type="text"> <input type="button" value="검색">
					</div>
					<div id="member_exit">
						<input type="button" value="강제 탈퇴">
					</div>
				</div>
			</div>
			<br>
			<table id="member_table">
				<colgroup>
					<col width="23">
					<col width="100">
					<col width="200">
					<col width="100">
					<col width="150">
				</colgroup>
				<thead id="member_table_header">
					<tr>
						<th class="check"><input type="checkbox"></th>
						<th>닉네임</th>
						<th>아이디</th>
						<th>전화번호</th>
						<th>가입일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="checkbox"></td>
						<td class="nickname">닉네임</td>
						<td class="id">아이디</td>
						<td>전화번호</td>
						<td>가입일</td>
					</tr>
				</tbody>
			</table>

			<div id="page_num">
				<span>1</span>
			</div>
		</main>
	</div>

	<!-- footer -->
	<footer>footer</footer>
</body>
</html>