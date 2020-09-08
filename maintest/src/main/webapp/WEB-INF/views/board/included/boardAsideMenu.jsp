<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common.jsp"%>


<nav>
        <div class="nav_wrap">
          <div class="category">
            <div class="category_now">
              <div class="now_wrap">
              	<c:choose >
              	<c:when test="${requestScope.bcategoryVO.catnum != 0 }">
                <h2>${requestScope.bcategoryVO.catname }</h2>              	          	
              	</c:when>    
              	<c:otherwise>
              	 <h2>전체글</h2>
              	 </c:otherwise>
              	</c:choose>
                <div class="underline"></div>
              </div>
            </div>
            <ul>
              <li><a href="${contextPath }/board/1">여행 팁</a></li>
              <li><a href="${contextPath }/board/2">갤러리</a></li>
              <li><a href="${contextPath }/board/3">QnA</a></li>
            </ul>
          </div>
        </div>
</nav>