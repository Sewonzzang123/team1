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


<link rel="stylesheet" href="${contextPath }/css/board/bType_Blog.css">
<%-- <script defer type="text/javascript"	src="${contextPath }/js/board/boardBlogListFrm.js"></script> --%>
	
	
	
</head>
<body>

<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<div class="main_img"></div>


	<main>
		<input type="hidden" id="returnPage" name="returnPage"
			value="${pagingComponent.pageCriteria.currReqPage}" />


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
						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>게시판분류</th>
									<th class="title">제목</th>
									<th>작성자</th>
									<th>조회수</th>
									<th>작성일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="tdata" items="${requestScope.articles}">
									<tr>
										<td>${tdata.dbrownum }</td>
										<td>${tdata.bcategory.catname}</td>

										<td class="title"><a
											href="${contextPath }/board/read/${tdata.bnum}/${pagingComponent.pageCriteria.currReqPage}/${pagingComponent.searchCriteria.searchType}/${pagingComponent.searchCriteria.searchKeyword}"
											"
							id="btitle"> <!-- 페이징 넘버 반영해서 리스트버튼 누르면 같은 페이지로  -->
												<c:if test="${tdata.bstep != 0  }">
													<span style="padding-left:${tdata.bindent *20+10}px; ">└${tdata.btitle}</span>
												</c:if> <c:if test="${tdata.bstep == 0  }">
													<span style="padding-left:${tdata.bindent *20+10}px; ">[${tdata.hidcategory.hidname}]</span>
													<span>${tdata.btitle}</span>
												</c:if>
										</a></td>
										<td>${tdata.bnickname}</td>
										<td>${tdata.bhits}</td>
										<td><fmt:formatDate value="${tdata.bcdate}"
												pattern="yy/MM/dd HH:mm" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

        
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