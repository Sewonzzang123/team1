<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ page session="false" %>
 	<%@ include file="/WEB-INF/views/included/common.jsp"  %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>


<a href="${contextPath }/mypage/">정민</a>
<a href="#">게시판목록</a>
<div class="btnGrp">
<a href="${contextPath }/board/tipb" >여행팁</a>
<a href="${contextPath }/board/qab">QA</a>
<button type="button" >여행갤러리</a>
</div>

<a href="${contextPath }/"></a>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
