<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
 	<!-- 공통모듈 -->
 	<%@ include file="/WEB-INF/views/included/common.jsp"  %>
 	
 	
 	<!-- 오늘 날짜 불러오기 -->
 	<jsp:useBean id="now" class="java.util.Date"/>
 	<fmt:formatDate value="${now }" pattern="yy/MM/dd" var="today"/>
 	
 	
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
        <input type="hidden" id="returnPage" name="returnPage" value="${pagingComponent.pageCriteria.currReqPage}"/>
        <div class="content">
            <div class="th cols">글번호</div>
            <div class="th cols">게시판카테고리</div>
           
            <div class="th cols">제목</div>
            <div class="th cols">작성자</div>
            <div class="th cols">조회수</div>
            <div class="th cols">작성일자</div>

<c:forEach var="tdata" items="${requestScope.articles}">  
            <div class="td cols">${tdata.dbrownum }</div>
            <div class="td cols">${tdata.bcategory.catname}</div>
           
            <div class="td cols">
            <a href="${contextPath }/board/read/${tdata.bnum}/${pagingComponent.pageCriteria.currReqPage}"  id="btitle">    
            <!-- 페이징 넘버 반영해서 리스트버튼 누르면 같은 페이지로  --> 
            
            <c:if test="${tdata.bstep > 0  }">
            <span style="padding-left:${tdata.bindent *20+10}px; ">└${tdata.btitle}</span>            
            </c:if>
             <c:if test="${tdata.bstep == 0  }">
            <span style="padding-left:${tdata.bindent *20+10}px; ">[${tdata.hidcategory.hidname}]</span><span>${tdata.btitle}</span>            
            </c:if>
            </a></div>

            <div class="td cols">${tdata.bnickname}</div>
            <div class="td cols">${tdata.bhits}</div>
            <div class="td cols">
            
            <!-- 작성일 당일은 시간으로 나타남.   -->
     
            <fmt:formatDate value="${tdata.bcdate}" pattern="yy/MM/dd HH:mm"/> </div>
         
   			
   			  
				 </c:forEach>
   			<%-- <c:if test="${tdata.bcdate !=today   }"> </c:if> --%>
   			 <%--  <fmt:formatDate value="${tdata.bcdate}" pattern="yy/MM/dd HH:mm"/> </div> --%>
              <%--  <c:if test="${tdata.bcdate ==today   }">   </c:if>  --%>
          
        </div>
        
        
        
        
        
        <div class="btnGrp">
            <button type="button" id="writeBtn">글쓰기</button>
            <div class="paging">
                <div><a href="${contextPath }/board/boardListFrm/1">처음페이지</a></div>
                <!-- 이전페이지 여부에 따라 표현  -->
                <c:if test="${pagingComponent.pageCriteria.prevPage }">
                <div><a href="${pagingComponent.pageCriteria.pagingNumFrom -1}">이전페이지</a></div>
                </c:if>
                
               <c:forEach var="reqPage" begin="${pagingComponent.pageCriteria.pagingNumFrom }" 
               end="${pagingComponent.pageCriteria.pagingNumTo }">               
                <div><a href="${contextPath }/board/boardListFrm/${reqPage}">${reqPage }</a></div>
               
               </c:forEach>
               
               
               <c:if test="${pagingComponent.pageCriteria.nextPage }">
                <div><a href="${pagingComponent.pageCriteria.pagingNumTo+1 }">다음페이지</a></div>
                 </c:if>
                <div><a href="${contextPath }/board/boardListFrm/${pagingComponent.pageCriteria.endPagingNum}">끝페이지</a></div>             
            </div>        
        </div>
        
        
            <div class="searching">
        <select name="searchType" id="searchType">
            <option value="A" selected>전체</option>
            <option value="TC">제목+내용</option>
            <option value="T">제목</option>
            <option value="C">내용</option>
            <option value="N">작성자</option>
        </select>
        <input type="text" name="searchKeyword" id="searchKeyword">
        <button type="button" id="searchBtn">검색</button>
    </div>
    
        
        
    </div>
</main>

</body>
</html>