<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/included/common_taglib.jsp"%>
<style>
.tab_box_container, 
.tab_menu_container{
		overflow-y: scroll;
}
.highlight > tbody > tr:hover {
	background-color:lightgray;
}
.highlight >tbody>tr {
	margin-top:5px;
	margin-bottom:3px;
}
</style>

<form target="_blank" method="post" name="form">
	<div id="wrapper">
		<div class="container additem tab_box_container">
			<table class="highlight">
				<thead>
					<tr>
						<td hidden="true">체크버튼</td>
						<td hidden="true">이름</td>
					</tr>
				</thead>
				<tbody>
				
					<c:if test="${empty requestScope.listing}">
						<c:forEach items="#{itemList }" var="itemVO">
							<tr class="tab_box tab_box${itemVO.itemCategoryVO.ca_num }">
								<td><a href="#"> <i class="fas fa-minus"
										onClick="deletelist2_f(this)" style="display: none"></i> <i
										class="fas fa-plus" onClick="addlist_f(this)"></i></a></td>
								<td><div class="item${itemVO.i_num } iname" selected="false">${itemVO.i_name }</div></td>
							</tr>
						</c:forEach>
					</c:if>

					<c:if test="${!empty requestScope.listing}">
						<c:forEach items="#{itemList }" var="itemVO">							
										<tr class="tab_box tab_box${itemVO.itemCategoryVO.ca_num }">
											<td><a href="#"> <i class="fas fa-minus"
													onClick="deletelist2_f(this)" style="display: none"></i> <i class="fas fa-plus"
													onClick="addlist_f(this)" ></i></a></td>
											<td><div class="item${itemVO.i_num } iname" selected="false">${itemVO.i_name }</div></td>
										</tr>									
						</c:forEach>
					</c:if>
			</table>
		</tbody>

			<div class="buttons">
				<input type="button" id="additem" value="아이템 추가" /><span>|</span> <input
					type="button" value="저장" id="saveBtn" /> <span>|</span> <input
					type="button" value="리스트 불러오기" id="loadBtn" /> <span>|</span> <a
					href="${contextPath }/itemlist/"> 인쇄</a>
			</div>

		</div>