<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="url_login" value="${contextPath }/maintest/loginForm" />
<c:set var="url_logout" value="${contextPath }/maintest/logout" />
<c:set var="url_myPage" value="${contextPath }/maintest/mypage" />
<%-- ${sessionScope.member } --%>
<style>
.uppermost .container.container-upm {
	display: flex;
	justify-content: space-between;
}

.uppermost .container.container-upm #logo {
	font-size: 2.3em;
	/* 최상위 메뉴 */ . uppermost { background-color : black;
	position: sticky;
	top: 0px;
	z-index: 100;
}

.uppermost>.container-upm {
	height: 30px;
	line-height: 30px;
	text-align: right;
	background-color: black;
}

.uppermost>.container-upm>p {
	margin-right: 10px;
	font-size: 0.8rem;
	color: white;
	font-weight: bold;
}

.uppermost>.container-upm>p>a {
	text-decoration: none;
	color: white;
}

.uppermost>.container-upm>p>a:hover {
	text-decoration: underline;
	color: white;
}

/* 헤더 영역 */
header {
	
}

header>.container-h {
	background-image:
		url("https://cdn.pixabay.com/photo/2020/01/26/20/14/computer-4795762__340.jpg");
	background-size: 800px 150px;
	height: 150px;
	text-align: center;
	background-color: gray;
	opacity: 0.8;
}
}
</style>
<div class="uppermost">
	<fmt:bundle basename="">
		<!-- 로그인 전 -->
		<c:if test="${empty sessionScope.member}">
			<div class="container container-upm">
				<%-- <p>
					<a href="${contextPath }"><i id="logo"
						class="fab fa-pied-piper-alt"></i></a>
				</p> --%>
				<p>
					<a href="${url_login }">로그인</a><span> | </span><a
						href="${contextPath }/maintest/signupForm">회원가입</a>
					${sessionScope.member.nickname }<span> | </span>
					
			</div>
		</c:if>
		<!-- 로그인 후  -->
		<c:if test="${!empty sessionScope.member}">
			<div class="container container-upm">
				<%-- <p>
					<a href="${contextPath }"><i id="logo"
						class="fab fa-pied-piper-alt"></i></a>
				</p> --%>
				<p>
					<a href="${url_logout }">로그아웃</a><span> | </span> <a
						href="${url_myPage}">${sessionScope.member.nickname }</a>
				</p>
			</div>
		</c:if>
		<c:out value="${contextPath }">${contextPath }</c:out>
	</fmt:bundle>
</div>