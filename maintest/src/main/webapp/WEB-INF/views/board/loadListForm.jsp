<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/included/common_taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>리스트 불러오기</title>
<link rel="stylesheet"
	href="${contextPath }/css/packinglist/loadListForm.css">
<script>
	let catnum = `${sessionScope.catnum}`;
	let returnPage= `${sessionScope.returnPage}`;
</script>
<script defer src="${contextPath }/js/board/loadListForm.js?ver=3"></script>

</head>
<body>
	<div class="pop_wrapper">
		<div class="pop_header row_a">
			<div class="pop_title_area grid_col">
				<h2 class="pop_title_txt estimate_load_title">
					<!-- 닉네임 가져와야됨 sessionScope.member.nickname-->
					a님의 리스트 목록
				</h2>
				<div class="estimate_prod_wrap">
					<div class="list_select_wrap">
						<select class="estimate_list_select" onchange="changeList_f(this)">
							<%
								int i = 0;
							%>
							<c:forEach var="list" items="${listVO}">
								<c:if test="${list.lnum eq requestScope.lnum}">
									<option value="${list.lnum }" selected="selected">${list.lname }</option>
								</c:if>
								<c:if test="${list.lnum ne requestScope.lnum}">
									<option value="${list.lnum }">${list.lname }</option>
								</c:if>
								<%
									i++;
								%>
							</c:forEach>
						</select> <span> 리스트 갯수 : <strong><%=i%></strong>개
						</span>
					</div>
				</div>
			</div>
		</div>
		<!--content-->
		<div id="content" class="pop_content_wrap estimate_load_wrap">
			<div class="estimate_list_table">
				<div class="container" id="container">
					<div class="frame">
						<!-- 카테고리 -->
						<c:forEach var="category" items="${icategory }" begin="0" step="2">
							<div class="block">
								<div class="category">${category.ca_name }</div>

								<!-- 아이템 -->
								<c:forEach var="listing" items="${requestScope.listingVO }">
									<c:if test="${category.ca_num == listing.ca_num }">
										<label><div class="item">
												<input type="checkbox" disabled="true" sname="ckeck"
													class="ckeck" linum=${listing.linum }
													<c:if test="${listing.checked eq 'true'}"> checked="true"</c:if>>
												<div name="i_name" class="i_name"
													<c:if test="${listing.checked eq 'true'}"> style="text-decoration-line: line-through"</c:if>>
													${listing.i_name }</div>
												<div name="icount" class="icount">${listing.icount }</div>
											</div></label>
									</c:if>
								</c:forEach>
							</div>
						</c:forEach>
					</div>
					<div class="frame">
						<c:forEach var="category" items="${icategory }" begin="1" step="2">
							<div class="block">
								<div class="category">${category.ca_name }</div>

								<!-- 아이템 -->
								<c:forEach var="listing" items="${requestScope.listingVO }">
									<c:if test="${category.ca_num == listing.ca_num }">
										<label><div class="item">
												<input type="checkbox" disabled="true" name="ckeck" class="ckeck"
													linum=${listing.linum }
													<c:if test="${listing.checked eq 'true'}"> checked="true"</c:if>>
												<div name="i_name" class="i_name"
													<c:if test="${listing.checked eq 'true'}"> style="text-decoration-line: line-through"</c:if>>
													${listing.i_name }</div>
												<div name="icount" class="icount">${listing.icount }</div>
											</div></label>
									</c:if>
								</c:forEach>
							</div>
						</c:forEach>
						
						<c:if test="${not empty sessionScope.boardVO }">
							${sessionScope.boardVO.btitle }
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<!--footer-->
		<div class="pop_footer_t1 row">
			<div class="grid_col_r">
				<button type="button" class="btn_upload" onclick="uploadBtn_f()">
					<span>첨부하기</span>
				</button>
				<button type="button" class="btn_pop_close"
					onclick="window.close();">
					<i class="fas fa-times"></i> <span>닫기</span>
				</button>
			</div>
		</div>
	</div>
</body>
</html>
