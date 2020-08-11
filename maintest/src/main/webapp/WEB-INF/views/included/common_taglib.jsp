<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />      

<%--  	

<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common_taglib.jsp"  %> 
 	
 	
 	${contextPath}
 	--%>
 	
 	<!-- font awesome -->
<link
	rel="stylesheet"
	href="${contextPath }/webjars/font-awesome/5.13.1/css/all.css"
/>

<!-- bootstrap -->
<link
		rel="stylesheet"
	href="${contextPath }/webjars/bootstrap/4.5.0/css/bootstrap.css"
/>

<!--폰트-->
<link
  href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@400;700&family=Nanum+Myeongjo:wght@400;700&family=Noto+Sans+KR:wght@100;400;900&display=swap"
  rel="stylesheet"
/>

<!--jquery-->
<script src="${contextPath }/webjars/jquery/3.5.1/jquery.js" defer></script>

<!-- bootstrap -->
<script src="${contextPath }/webjars/bootstrap/4.5.0/js/bootstrap.js" defer></script>

<!-- popper -->
<script src="${contextPath }/webjars/popper.js/2.0.2/umd/popper.js" defer></script>


<!--d3-->
<script src="${contextPath }/webjars/d3js/5.16.0/d3.js" defer></script>