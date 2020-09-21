<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="url_login"
	value="${pageContext.request.contextPath }/loginForm" />
<c:set var="url_logout"
	value="${pageContext.request.contextPath }/logout" />
<c:set var="url_myPage"
	value="${pageContext.request.contextPath }/mypage" />
<c:set var="url_admin" value="${pageContext.request.contextPath }/admin" />


<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: "Noto Sans KR", sans-serif;
}

a {
	margin: 0;
	text-decoration: none;
	color: black;
	font-size: 12px;
}

a:hover {
	color: #ff8080;
	transition: color 0.2s;
}

header {
	background-color: white;
	/* position: fixed; */
	/* z-index: 1; */
	width: 100%;
	box-shadow: 0 0 10px 0 darkgray;
}

header ul {
	list-style: none;
}

/* 햄버거 버튼 */
i.navbar_toggleBtn {
	display: none;
	position: absolute;
	top: 7px;
	right: 10px;
	font-size: 27px;
	color: #ff8080;
}

/* mobile */
@media screen and (max-width: 769px) {
	header .navbar {
		max-width: 1110px;
		height: 40px;
		margin: 0 auto;
		display: flex;
		grid-template-columns: repeat(2, 1fr);
		justify-content: space-between;
	}
	header ul {
		display: none;
	}
	i.navbar_toggleBtn {
		display: block;
	}
	header .logo {
		line-height: 60px;
	}
	header img {
		width: 50px;
		height: 33px;
		margin-left: 5px;
	}
	.menu_wrap {
		position: fixed;
		z-index: -999;
		right: 0%;
		width: 60%;
		height: 100%;
		background-color: white;
		opacity: 0;
		transition: opacity 0.5s;
	}
	.menu_wrap .hide_wrap {
		border-bottom: 2px solid #ffacac;
		height: 40px;
		background: #ff8080;
	}
	.menu_wrap ul {
		list-style: none;
	}
	.menu_wrap ul li {
		border-bottom: 1px dotted lightgray;
		height: 40px;
		line-height: 40px;
		margin-left: 13px;
	}
	#buggerBtn_hide {
		color: white;
		margin-top: 13px;
		margin-left: 13px;
	}
	.cover_close {
		position: fixed;
		width: 100%;
		height: 100%;
		z-index: -998;
		background-color: black;
		opacity: 0;
	}
}

header .navbar {
	max-width: 1110px;
	height: 50px;
	margin: 0 auto;
	display: flex;
	grid-template-columns: repeat(2, 1fr);
	justify-content: space-between;
}

header .logo {
	line-height: 75px;
}

header img {
	width: 55px;
	height: 35px;
}

header .top_menu {
	line-height: 50px;
}

header ul {
	display: flex;
}

header ul li {
	text-align: center;
	font-size: 0.8em;
	padding-left: 3em;	
}

.menu_wrap {
	display: none;
}
</style>
<header>
	<div class="navbar">
		<div class="logo">
			<a href="${pageContext.request.contextPath}"> <img
				src="<%=request.getContextPath()%>/img/perage_logo_3.png" alt="logo" />
			</a>
		</div>

		<fmt:bundle basename="">
			<!-- 로그인 전 -->
			<c:if test="${empty sessionScope.member}">
				<div class="top_menu">
					<ul>
						<li><a href="${pageContext.request.contextPath}/#list_wrap">리스트 작성</a></li>
						<li><a href="${pageContext.request.contextPath}/board">게시판</a></li>
						<li><a href="${pageContext.request.contextPath}/signupForm">회원가입</a></li>
						<li><a href="${url_login }">로그인</a></li>

					</ul>
				</div>
			</c:if>
		</fmt:bundle>

		<!-- 로그인 후  -->
		<c:if test="${!empty sessionScope.member}">

			<div class="top_menu">
				<ul>
					<li><a href="${pageContext.request.contextPath}/#list_wrap">리스트 작성</a></li>
					<li><a href="${pageContext.request.contextPath}/board">게시판</a></li>
					<c:if test="${sessionScope.member.ucode==1}">
						<li><a href="${url_admin}">관리하기</a></li>
					</c:if>
					<li><a href="${url_myPage}">${sessionScope.member.nickname }</a></li>
					<li><a href="${url_logout }">로그아웃</a></li>
				</ul>
			</div>

		</c:if>

		<i class="fas fa-bars navbar_toggleBtn" id="buggerBtn"></i>

	</div>
</header>