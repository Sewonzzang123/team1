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



<a href="#">게시판목록</a>
<div class="btnGrp">
<a href="${contextPath }/board/tipb" >여행팁</a>
<a href="${contextPath }/board/qab">QA</a>
<button type="button" >여행갤러리</a>
</div>

<button id="ajaxButton" type="button">Make a request</button>

<script>
(function() {
  var httpRequest;
  document.getElementById("ajaxButton").addEventListener('click', makeRequest);

  function makeRequest() {
    httpRequest = new XMLHttpRequest();

    if(!httpRequest) {
      alert('XMLHTTP 인스턴스를 만들 수가 없어요 ㅠㅠ');
      return false;
    }
    httpRequest.onreadystatechange = alertContents;
    httpRequest.open('GET', '/pfpkg/board/boardListFrm');
    httpRequest.send();
  }

  function alertContents() {
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
      if (httpRequest.status === 200) {
        alert(httpRequest.responseText);
      } else {
        alert('request에 뭔가 문제가 있어요.');
      }
    }
  }
})();
</script>

<a href="${contextPath }/"></a>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
