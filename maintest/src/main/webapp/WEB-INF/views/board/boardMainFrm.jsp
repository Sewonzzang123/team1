<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common.jsp"%>
    
    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
<main>
    <div class="main_wrap">
<!-- 게시판 카테고리 메뉴  -->
      <%@ include file="/WEB-INF/views/board/included/boardAsideMenu.jsp"%>
      
        <section>
        <div class="section_wrap">
          <div class="section_write">
            <button class="writeBtn">글쓰기</button>
          </div>
          <div class="section_table">
          
          <!-- btype에 따라 불러오는 페이지 구별 -->
            <%@ include file="/WEB-INF/views/board/boardBlogListFrm.jsp"%>
          <c:if test="" >
          
           <%@ include file="/WEB-INF/views/board/boardGalleryListFrm.jsp"%>          
          </c:if>
          
          
	<!--=====페이징/검색==================================================================  -->
			
  <%@ include file="/WEB-INF/views/board/included/paing.jsp"%>
  <%@ include file="/WEB-INF/views/board/included/searching.jsp"%>

 <!--=====페이징/검색==================================================================  -->

   				</div>
        </div>
      </section>
    </div>
  </main>



</body>
</html>