<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
 	<!-- 공통모듈 -->
 	<%@ include file="/WEB-INF/views/included/common_taglib.jsp"  %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Board메인페이지</title>
<link rel="stylesheet" href="${contextPath }/css/board/boardListFrm.css">
<script defer type="text/javascript" src="${contextPath }/js/board/boardListFrm.js" ></script>
</head>
<body>

<main>

    <div class="container">
        <div class="title">
            <legend>게시글 목록</legend>
        </div>
        <div class="content">
            <div class="th cols">글번호</div>
            <div class="th cols">게시판카테고리</div>
            <div class="th cols">말머리</div>
            <div class="th cols">제목</div>
            <div class="th cols">작성자</div>
            <div class="th cols">조회수</div>
            <div class="th cols">작성일자</div>

<c:forEach var="tdata" items="${requestScope.listBoardVO}"> 
            <div class="td cols">${tdata.bnum }</div>
            <div class="td cols">${tdata.bcategory.catname}</div>
            <div class="td cols">${tdata.hidcategory.hidname}</div>
            <div class="td cols">${tdata.btitle}</div>
            <div class="td cols">${tdata.bnickname}</div>
            <div class="td cols">${tdata.bhits}</div>
            <div class="td cols">${tdata.bcdate}</div>
            </c:forEach>
        </div>
        
        <div class="btnGrp">
            <button type="button" id="writeBtn">글쓰기</button>

            <div class="paging">
                <div><a href="">처음페이지</a></div>
                <div><a href="">이전페이지</a></div>
                <div><a href="">|1</a></div>
                <div><a href="">2</a></div>
                <div><a href="">3</a></div>
                <div><a href="">4</a></div>
                <div><a href="">5</a></div>
                <div><a href="">6</a></div>
                <div><a href="">7</a></div>
                <div><a href="">8</a></div>
                <div><a href="">9|</a></div>
                <div><a href="">다음페이지</a></div>
                <div><a href="">끝페이지</a></div>             
            </div>        
        </div>
    </div>
</main>
</main>
</body>
</html>