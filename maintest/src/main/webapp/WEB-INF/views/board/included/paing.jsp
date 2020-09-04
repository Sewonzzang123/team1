<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	
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