<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common.jsp"%>


<nav>
	<div class="nav_wrap">
		<div class="category">
			<div class="category_now">
				<div class="now_wrap">
					<c:choose>
						<c:when test="${requestScope.bcategoryVO.catnum != 0 }">
							<a
								href="${pageContext.request.contextPath}/board/${requestScope.bcategoryVO.catnum }">
								${requestScope.bcategoryVO.catname }</a>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/board">전체글 보기</a>
						</c:otherwise>
					</c:choose>
					<div class="underline"></div>
				</div>
			</div>
			<ul>
					<c:forEach var="bcatelist" items="${requestScope.bcatelist}" begin="1" >
					<li><a href="${contextPath }/board/${bcatelist.catnum  }">${bcatelist.catname  }</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</nav>