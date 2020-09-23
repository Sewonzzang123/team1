<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common.jsp"%>

<div class="gallery_list">
<ul> 
	<!-- data 들어오는 부분 loop-->
		<c:forEach var="tdata" items="${requestScope.boardVO}">
			<li class="inner">
			<a href="${contextPath }/board/read/${tdata.bcategory.catnum}/${tdata.bnum}
			/${pagingComponent.pageCriteria.currReqPage}/${pagingComponent.searchCriteria.searchType}/${pagingComponent.searchCriteria.searchKeyword}">
					<div class="li_img">
								<img alt="" src="${contextPath }/${tdata.thumbnail}">
						<%-- <img name="" src="data:${tdata.thumbnailVO.thumbftype };base64,${tdata.thumbnailVO.base64encoded}"	alt="" /> --%>
					</div>
					<div class="li_text">
						<div class="li_text_poa">
							<h5 class="li_text_head">${tdata.btitle }</h5>
							<p class="li_text_content">${tdata.tcontent}</p>
							<div class="li_text_cw">
								<span class="li_text_cdate">작성자 : ${tdata.bnickname }</span> <span
									class="li_text_writer"> <fmt:formatDate
										value="${tdata.budate }" pattern="MM/dd" />
								</span>
							</div>
						</div>
					</div>
			</a></li>
		</c:forEach>
	</ul> 
</div>
