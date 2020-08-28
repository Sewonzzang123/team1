<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/included/common_taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>아이템 관리</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/admin.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/admin_main.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script defer
	src="${pageContext.request.contextPath}/js/admin/admin_item.js">
	
</script>
</head>

<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/layout/uppermost.jsp"%>

	<div class="box-container">
		<!-- aside -->
		<%@ include file="/WEB-INF/views/admin/admin_sidebar.jsp"%>

		<!-- main -->

		<main id="content" class="admin_item">
			<div class="content_sub-title">아이템 관리</div>
			<div class="b_container">
				<div class="b_sidebar">
					<div class="b_listSet">
						<ul id="b_list">
							<c:forEach var="icate" items="${requestScope.itemCategoryVO }">
								<li class="selectable"><button class="icate_name"
										onclick="icate_name_select(this)" ca_num="${icate.ca_num }">${icate.ca_name }</button></li>
							</c:forEach>

						</ul>
					</div>
					<div class="b_listBtn">
						<input type="button" onclick="addMenu()" value="추가"><input
							type="button" onclick="delMenu()" value="제거">
					</div>
				</div>



				<div class="b_content" id="b_content">
					<c:forEach var="icate" items="${requestScope.itemCategoryVO }">

						<div id="icategory_set_box" class="set_box" style="display: none;">

							<table id="icategory_table">
								<caption>
									<input id="icate_name_input" class="icate_name_input info"
										type="text" value="${icate.ca_name }">
								</caption>
								<input type="hidden" class="info" id="ca_num_input"
									value="${icate.ca_num }" />
								<!-- 카테고리 넘버 -->
								<tr>
									<td class="item_area">
										<ul>
											<li><c:forEach var="item" items="${requestScope.item }">
													<c:if test="${item.itemCategoryVO.ca_num == icate.ca_num }">
														<div class="item_set" i_num="${item.i_num }">
															<span class="item_name">${item.i_name }</span><input
																type="button" value="삭제" class="item_del_btn"
																onclick="item_del(this)">
														</div>
													</c:if>
												</c:forEach></li>
										</ul>
									</td>
								</tr>
							</table>

							<div class="icategory_add_btn_area">
								<input type="button" value="아이템 추가" class="icategory_add_btn"
									onclick="item_add()">
							</div>
						</div>
					</c:forEach>

				</div>
			</div>

			<div class="actionBtn">
				<input type="button" id="saveBtn" value="저장"> <input
					type="button" value="취소">
			</div>

		</main>
	</div>
</body>
</html> --%>