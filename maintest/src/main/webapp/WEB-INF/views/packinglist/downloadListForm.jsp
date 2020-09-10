<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/included/common_taglib.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>PDF/인쇄 페이지</title>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<script type="text/javascript"
	src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
<script defer src="${contextPath}/js/mypage/listView.js"></script>
<link href="${contextPath }/css/packinglist/downloadListForm.css" rel="stylesheet"/>
</head>
<body>

	<main>		
		<div class="page">
			<h3 class="title">have a good day and a better tomorrow</h3>
			<div class="container" id="container">
				<div class="frame">
					<!-- 카테고리 -->
					<c:forEach var="category" items="${category }"
						begin="0" step="2">
						<div class="block">
							<div class="category">${category.ca_name }</div>

							<!-- 아이템 -->
							<c:forEach var="listing" items="${requestScope.listing }">
								<c:if test="${category.ca_num == listing.icategory }">
									<label><div class="item">
											<input type="checkbox" name="ckeck" class="ckeck"
												<c:if test="${listing.checked eq 'true'}"> checked="true"</c:if>>
											<div name="i_name" class="i_name">${listing.i_name }</div>
											<div name="icount" class="icount">${listing.icount }</div>
										</div></label>
								</c:if>
							</c:forEach>
						</div>
					</c:forEach>
				</div>
				<div class="frame">
					<c:forEach var="category" items="${category }"
						begin="1" step="2">
						<div class="block">
							<div class="category">${category.ca_name }</div>

							<!-- 아이템 -->
							<c:forEach var="listing" items="${listing }">
								<c:if test="${category.ca_num == listing.icategory }">
									<label><div class="item">
											<input type="checkbox" name="ckeck" class="ckeck"
												<c:if test="${listing.checked eq 'true'}"> checked="true"</c:if>>
											<div name="i_name" class="i_name">${listing.i_name }</div>
											<div name="icount" class="icount">${listing.icount }</div>
										</div></label>
								</c:if>
							</c:forEach>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="action">
			<input type="button" id="printbtn" value="인쇄"> <input
				type="button" id="pdfbtn" value="PDF출력">
		</div>
	</main>

</body>
</html>