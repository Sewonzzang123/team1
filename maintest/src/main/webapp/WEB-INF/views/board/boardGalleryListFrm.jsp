<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common.jsp"%>


<!-- 오늘 날짜 불러오기 -->
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now }" pattern="yy/MM/dd" var="today" />


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Board메인페이지</title>
<link rel="stylesheet" href="${contextPath }/css/board/boardListFrm.css">
<script defer type="text/javascript"
	src="${contextPath }/js/board/boardListFrm.js"></script>
</head>
<body>

<aside>
<%@ include file="../home.jsp" %>
</aside>

	<main>

		<div class="container">
			<div class="title">			
				<legend>게시글 목록</legend>
			</div>
			<input type="hidden" id="returnPage" name="returnPage"
				value="${pagingComponent.pageCriteria.currReqPage}" />
			<div class="content">
				

			<%-- 	<c:forEach var="tdata" items="${requestScope.articles}">
					<div >${tdata.dbrownum }</div>
					<div >${tdata.bcategory.catname}</div>

					<div class="td cols">
						<a
							href="${contextPath }/board/read/${tdata.bnum}/${pagingComponent.pageCriteria.currReqPage}/${pagingComponent.searchCriteria.searchType}/${pagingComponent.searchCriteria.searchKeyword}""
							id="btitle"> <!-- 페이징 넘버 반영해서 리스트버튼 누르면 같은 페이지로  --> 
							<c:if	test="${tdata.bstep != 0  }">
								<span style="padding-left:${tdata.bindent *20+10}px; ">└${tdata.btitle}</span>
							</c:if> <c:if test="${tdata.bstep == 0  }">
								<span style="padding-left:${tdata.bindent *20+10}px; ">[${tdata.hidcategory.hidname}]</span>
								<span>${tdata.btitle}</span>
							</c:if>

						</a>
					</div>

					<div class="td cols">${tdata.bnickname}</div>
					<div class="td cols">${tdata.bhits}</div>
					<div class="td cols"> --%>

		<script type="text/javascript">


		
							</script>
							
								 <input type="file" id="thumbnail"	name="files" multiple="multiple" />							
								<div class="img_wrap">
									<div class="img_content">
										
												<!-- 보여줄 이미지 들어오는 공간 -->
													<li>
													<label for="btitle">제목</label> <input name="btitle" type="text" value="${article.btitle }"/> 
													</li>
							<li>
							<label for="bnickname">작성자</label> <input type="text"
								name="bnickname" readonly
								value="${articles.nickname }" /></li>
							
									</div>
								</div>




			<div class="btnGrp">
				<button type="button" id="writeBtn">글쓰기</button>
				
				<!-- 페이징 -->
				<div class="paging">
					<div>
						<a
							href="${contextPath }/board/boardListFrm/1/${pagingComponent.searchCriteria.searchType}/${pagingComponent.searchCriteria.searchKeyword}">처음페이지</a>
					</div>
					<!-- 이전페이지 여부에 따라 표현  -->
					<c:if test="${pagingComponent.pageCriteria.prevPage }">
						<div>
							<a
								href="${contextPath }/board/boardListFrm/${pagingComponent.pageCriteria.pagingNumFrom -1}">이전페이지</a>
						</div>
					</c:if>

					<c:forEach var="reqPage"
						begin="${pagingComponent.pageCriteria.pagingNumFrom }"
						end="${pagingComponent.pageCriteria.pagingNumTo }">
						<div>
							<a
								href="${contextPath }/board/boardListFrm/${reqPage}/${pagingComponent.searchCriteria.searchType}/${pagingComponent.searchCriteria.searchKeyword}">${reqPage }</a>
						</div>

					</c:forEach>


					<c:if test="${pagingComponent.pageCriteria.nextPage }">
						<div>
							<a
								href="${contextPath }/board/boardListFrm/${pagingComponent.pageCriteria.pagingNumTo+1 }">다음페이지</a>
						</div>
					</c:if>
					<div>
						<a
							href="${contextPath }/board/boardListFrm/${pagingComponent.pageCriteria.endPagingNum}/${pagingComponent.searchCriteria.searchType}/${pagingComponent.searchCriteria.searchKeyword}">끝페이지</a>
					</div>
				</div>
			</div>


<!-- 검색 -->
			<div class="searching">
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
				</select> <input type="text" name="searchKeyword" id="searchKeyword"
					value="${pagingComponent.searchCriteria.searchKeyword}">
				<button type="button" id="searchBtn">검색</button>
			</div>



		</div>
	</main>

</body>
</html>