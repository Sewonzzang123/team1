<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common.jsp"%>
<link rel="stylesheet" href="${contextPath }/css/board/boardListFrm.css">
<script defer type="text/javascript"
	src="${contextPath }/js/board/boardListFrm.js"></script>
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
               <!--=======================================================================  -->
             <div class="gallery_list">
            
            <!-- data 들어오는 -->
                   <ul>
                   
                   
                   
               <!--   <li class="inner">   
            <a href="#">
                    <div class="li_img">
                      <img src="https://cdn.pixabay.com/photo/2020/05/05/07/52/republic-of-korea-5131925__340.jpg"
                        alt="" />
                    </div>
                    <div class="li_text">
                      <div class="li_text_poa">
                        <h5 class="li_text_head">Title of content</h5>
                        <p class="li_text_content">
                          Lorem ipsum dolor sit amet consectetur adipisicing
                          elit. Omnis Lorem ipsum dolor sit amet consectetur
                          adipisicing elit. OmnisLorem ipsum dolor sit amet
                          consectetur adipisicing elit. Omnis
                        </p>
                        <div class="li_text_cw">
                          <span class="li_text_cdate">작성자 : test01</span>
                          <span class="li_text_writer">08.27</span>
                        </div>
                      </div>
                    </div>
                  </a>
                </li> -->
                
                
                
                
              </ul>
            </div>















           				<!-- 페이징 -->
				<div class="paging">
				<ul>
					<li>
						<a
							href="${contextPath }/board/boardListFrm/1/${pagingComponent.searchCriteria.searchType}/${pagingComponent.searchCriteria.searchKeyword}">처음페이지</a>
					</li>
					<!-- 이전페이지 여부에 따라 표현  -->
					<c:if test="${pagingComponent.pageCriteria.prevPage }">
						<li>
							<a
								href="${contextPath }/board/boardListFrm/${pagingComponent.pageCriteria.pagingNumFrom -1}">이전페이지</a>
						</li>
					</c:if>
					<c:forEach var="reqPage"
						begin="${pagingComponent.pageCriteria.pagingNumFrom }"
						end="${pagingComponent.pageCriteria.pagingNumTo }">
						<li>
							<a
								href="${contextPath }/board/boardListFrm/${reqPage}/${pagingComponent.searchCriteria.searchType}/${pagingComponent.searchCriteria.searchKeyword}">${reqPage }</a>
						</li>

					</c:forEach>
					<c:if test="${pagingComponent.pageCriteria.nextPage }">
						<li>
							<a
								href="${contextPath }/board/boardListFrm/${pagingComponent.pageCriteria.pagingNumTo+1 }">다음페이지</a>
						</li>
					</c:if>
					<li>
						<a
							href="${contextPath }/board/boardListFrm/${pagingComponent.pageCriteria.endPagingNum}/${pagingComponent.searchCriteria.searchType}/${pagingComponent.searchCriteria.searchKeyword}">끝페이지</a>
					</li>
					</ul>
				</div>
				
				<!-- 검색 -->
        <div class="search">
          <form action="">
            <select name="searchType" id="searchType">
              <option value="A"
                <c:if test="${pagingComponent.searchCriteria.searchType == 'A'}"> selected="selected" </c:if>>전체</option>
              <option value="TC"
                <c:if test="${pagingComponent.searchCriteria.searchType == 'TC'}"> selected="selected" </c:if>>제목+내용</option>
              <option value="T" 
                <c:if test="${pagingComponent.searchCriteria.searchType == 'T'}"> selected="selected" </c:if>>제목</option>
              <option value="C"
                <c:if test="${pagingComponent.searchCriteria.searchType == 'C'}"> selected="selected" </c:if>>내용</option>
              <option value="N"
                <c:if test="${pagingComponent.searchCriteria.searchType == 'N'}"> selected="selected" </c:if>>작성자</option>
            </select> 
                  </form>
            <input type="text" name="searchKeyword" id="searchKeyword"
              value="${pagingComponent.searchCriteria.searchKeyword}">
            <button type="button" id="searchBtn">검색</button>
          </div>
          
          
               <!--=======================================================================  -->
          </div>
        </div>
      </section>
    </div>
  </main>

	

</body>
</html>