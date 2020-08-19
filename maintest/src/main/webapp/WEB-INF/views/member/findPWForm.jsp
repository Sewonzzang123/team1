<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/member/findIDPW.css">

<body>
	<!-- header -->
	<header>
		<%@ include file="/WEB-INF/views/layout/logo.jsp"%>
	</header>
	<main>
		<div class="container">
			<div class="find_tapmenu">
				<ul>
					<li><a href="${pageContext.request.contextPath}/findIDForm">아이디
							찾기</a></li>
					<li class="selected"><a
						href="${pageContext.request.contextPath}/findPWForm">비밀번호 찾기</a></li>
				</ul>
			</div>

			<fieldset class="findid_findpw">
				<form:form modelAttribute="info">
					<div class="id_area">
						<div class="input_row">
							<input type="text" id="id" name="id" class="int"
								placeholder="아이디" maxlength="25">
						</div>
					</div>

					<div class="tel_area">
						<div class="input_row">
							<input type="text" id="tel" name="tel" class="int"
								placeholder="휴대폰 번호 ( - 포함)" maxlength="13">
						</div>
					</div>
					<span class="err_msg">${requestScope.err_msg }</span>
					<div class="actionBtn">
						<input type="submit" title="아이디 찾기" id="findID" class="btn"
							value="비밀번호 찾기"
							formaction="${pageContext.request.contextPath}/findPW" />
					</div>
				</form:form>
			</fieldset>
		</div>
	</main>
</body>
</html>