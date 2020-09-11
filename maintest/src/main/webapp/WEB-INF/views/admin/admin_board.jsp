<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판 관리</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/admin.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/admin_main.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script defer
	src="${pageContext.request.contextPath}/js/admin/admin_board.js">
	
</script>
</head>

<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/layout/uppermost.jsp"%>

	<div class="box-container">
		<!-- aside -->
		<%@ include file="/WEB-INF/views/admin/admin_sidebar.jsp"%>

		<!-- main -->

		<main id="content" class="admin_board">
			<div class="content_sub-title">게시판 관리</div>
			<div class="b_container">
				<div class="b_sidebar">
					<div class="b_listSet">
						<ul id="b_list">
							<c:forEach var="bcate" items="${requestScope.bcategoryVO }">
								<li class="selectable"><button class="b_title_btn"
										onclick="b_select(this)" canum="${bcate.catnum }">${bcate.catname }</button></li>
							</c:forEach>
						</ul>
					</div>
					<div class="b_listBtn">
						<input type="button" onclick="addMenu()" value="추가"><input
							type="button" onclick="delMenu()" value="제거">
					</div>
				</div>


				<div class="b_content" id="b_content">
					<c:forEach var="bcate" items="${requestScope.bcategoryVO }">
						<table id="" class="set_box hide">
							<input type="hidden" class="info" id="canum_input"
								value="${bcate.catnum }" />
							<!-- 카테고리 넘버 -->

							<tr>
								<th class="caname">게시판명</th>
								<td class="caname_value"><input id="caname_input"
									class="caname_input info" type="text" value="${bcate.catname }"></td>
							</tr>

							<tr>
								<th class="bmemo">게시판 설명</th>
								<td class="bmemo_value"><input id="bmemo_input"
									class="info" type=" text" value="${bcate.bmemo }"></td>
							</tr>


							<tr>
								<th class="btype">게시판 유형</th>
								<td class="btype_input_box"><select id="btype_input"
									class="info">
										<option value="blog">자유게시판</option>
										<option value="album"
											<c:if test="${bcate.btype == 'album' }">selected</c:if>>사진게시판</option>
								</select></td>
							</tr>

							<tr>
								<th class="b_subject">말머리</th>
								<td class="b_subject_value"><input type="hidden"
									class="del_annum_list info" id="del_annum_list"> <!-- 삭제된 말머리 넘버 -->
									<div class="sub_input_box">
										<input type="text" class="sub_input"><input
											type="button" onclick="addSub(this)" value="추가">
									</div>
									<div class="sub_box">
										<c:forEach var="headId"
											items="${requestScope.headIdCategoryVO }">
											<c:if test="${bcate.catnum==headId.catnum}">
												<li class="b_subject_chd"><span class="aname"
													annum="${headId.hidnum }">${headId.hidname }</span><input
													type="button" class="delSubBtn" value="제거"></li>
											</c:if>
										</c:forEach>
									</div></td>
							</tr>
						</table>
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
</html>