<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	썸네일 테스트
	<img id="mypic" src="data:image/png;base64,${requestScope.pic }" alt="" />
	<c:forEach var="thumb" items="${requestScope.thumb }">

		<%-- <img alt=""
			src="${pageContext.request.contextPath}${thumb.fthumbnail }"> --%>
		<%-- ${thumb.fthumbnail.fdata } --%>
	</c:forEach>
</body>
<script type="text/javascript">
	
</script>
</html>