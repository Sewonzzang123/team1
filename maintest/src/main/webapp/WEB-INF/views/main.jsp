<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
	<!-- topmenu -->
	<%@ include file="/WEB-INF/views/layout/uppermost.jsp"%>
	메인 ${sessionScope.member.id } 아이디 ${sessionScope.member.nickname }
</body>
</html>