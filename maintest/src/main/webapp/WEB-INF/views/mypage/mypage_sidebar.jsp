<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
#sidebar {
	font-family: "Inter", 'NotoSansKR', "Helvetica Neue", Helvetica, Arial,
		"맑은 고딕", malgun gothic, "돋움", Dotum, sans-serif, "Apple Color Emoji",
		"Noto Color Emoji";
	font-size: 16px;
	color: #263747;
	background-color: white;
	line-height: 1.4;
	width: 250px;
}

#sidebar_list {
	list-style: none;
	border: black 1px solid;
	width: 200px;
	margin: 0;
}

#sidebar_list>li:first-child {
	margin-top: 10px;
}

#sidebar_list>li {
	margin-bottom: 20px;
	border: #263747 1px;
}

a {
	font-size: 15px;
	font-weight: bold;
	text-decoration: none;
	color: #333333;
}
</style>
<aside id="sidebar">
	<ul id="sidebar_list">
		<li><a href="/maintest/mypage/modifyForm">회원 정보 수정</a></li>
		<li><a href="/maintest/mypage/mypost">내가 쓴 글</a></li>
		<li><a href="/maintest/mypage/mylist">내 리스트</a></li>



	</ul>
</aside>
