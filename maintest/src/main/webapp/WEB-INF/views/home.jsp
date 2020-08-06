<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ page session="false" %>
 	<!-- 공통모듈 taglib -->
 	<%@ include file="/WEB-INF/views/included/common_taglib.jsp"  %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>


<a href="${contextPath }/mypage/">정민</a>
<a href="${contextPath }/board/">대석</a>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
