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
<style>
#txt {
	text-align: center;
}
</style>
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/layout/uppermost.jsp"%>

	<div class="box-container">
		<!-- aside -->
		<%@ include file="/WEB-INF/views/mypage/mypage_sidebar.jsp"%>

		<!-- main -->
		<main id="content" class="modifyResult">
			<div id="txt">
				<div>
					<strong class="org">회원정보가 수정되었습니다.</strong>
				</div>
			</div>

			<table>
				<colgroup>
					<col style="width: 150px">
					<col style="width: 450px">
				</colgroup>

				<h3>기본정보</h3>
				<tbody>
					<tr>
						<th>아이디</th>
						<td class="table-head">${sessionScope.member.id }</td>
					</tr>
					<tr>
						<th>이름</th>
						<td class="table-head">${sessionScope.member.name }</td>
					</tr>
					<tr>
						<th>별명</th>
						<td class="table-head">${sessionScope.member.nickname }</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td class="table-head">${sessionScope.member.tel }</td>
					</tr>
				</tbody>
			</table>

			<div class="actionBtn">
				<input type="button" value="메인페이지로"
					onClick="location.href='${pageContext.request.contextPath}'"> <input type="button"
					value="이전 페이지"
					onClick="location.href='${pageContext.request.contextPath}/mypage/modifyForm'">
			</div>
		</main>
	</div>

	<!-- footer -->
	<footer>footer</footer>
</body>
</html>