<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
#sidebar {
	font-family: "Inter", 'NotoSansKR', "Helvetica Neue", Helvetica, Arial,
		"맑은 고딕", malgun gothic, "돋움", Dotum, sans-serif, "Apple Color Emoji",
		"Noto Color Emoji";
	/* font-size: 16px; */
	color: #525252;
	background-color: white;
	line-height: 1.4;
	width: 250px;
	min-width: 150px;
}

#sidebar_list {
	padding: 0;
	list-style: none;
	margin: 0;
}

#sidebar_list>li {
	font-family: -apple-system, BlinkMacSystemFont, "Roboto", "Segoe UI",
		"Helvetica Neue", "Lucida Grande", Arial, sans-serif;
	font-size: 1em;
	line-height: 1.5;
	border: #263747 1px;
	margin-left: 10px;
	margin-bottom: 3px;
}

a {
	color: inherit;
	text-decoration: none;
	/* color: #333333; */
}

.nav_sub-title {
	display: block;
	margin: 0.5rem 0;
	padding: 0.25rem 0;
	font-family: -apple-system, BlinkMacSystemFont, "Roboto", "Segoe UI",
		"Helvetica Neue", "Lucida Grande", Arial, sans-serif;
	font-size: 1.5em;
	font-weight: bold;
	text-transform: uppercase;
	border-bottom: 1px solid #5d4fad;
}
</style>
<aside id="sidebar">



	<div class="nav_sub-title">마이페이지</div>
	<ul id="sidebar_list">
		<li><a
			href="${pageContext.request.contextPath}/mypage/modifyForm">회원
				정보 수정</a></li>
		<li><a href="${pageContext.request.contextPath}/mypage/mypost">내가
				쓴 글</a></li>
		<li><a href="${pageContext.request.contextPath}/mypage/mylist">내
				리스트</a></li>
	</ul>


</aside>
