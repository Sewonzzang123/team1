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
#mypost_delete {
	display: inline;
	float: right;
}

.actionPost {
	margin-top: 10px;
	display: flex;
	align-items: center;
	justify-content: space-between;
}

.checkbox {
	display: flex;
	align-items: center;
}

#mypost_delete>input {
	width: 120px;
	text-align: center;
	padding: 0;
}

.check {
	text-align: left;
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
		<main id="content" class="mypost">

			<table id="member_table">
				<colgroup>
					<col style="width: 23px;">
					<col style="width: 400px;">
					<col style="width: 100px;">
					<col style="width: 50px;">
				</colgroup>
				<thead id="member_table_header">
					<tr>
						<th></th>
						<th>제목</th>
						<th>작성일</th>
						<th>조회</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="check"><input type="checkbox"></td>
						<td class="title">제목</td>
						<td>작성일자</td>
						<td>조회수</td>
					</tr>
				</tbody>
			</table>

			<div class="actionPost">
				<div class="checkbox">
					<input type="checkbox" name="" id=""> 전체선택
				</div>
				<div id="mypost_delete">
					<input type="button" value="삭제">
				</div>
			</div>

			<div id="page_num">
				<span>1</span>
			</div>

		</main>
	</div>
	<!-- footer -->
	<footer>footer</footer>
</body>
</html>