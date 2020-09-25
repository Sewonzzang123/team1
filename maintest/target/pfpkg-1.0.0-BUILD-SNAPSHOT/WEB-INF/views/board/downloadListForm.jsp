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
<script>
var getucode = null;
if ('${sessionScope.member.ucode}'.trim() == null) {
	getucode = null;
} else {
	getucode = '${sessionScope.member.ucode}';
}
console.log('getucode'+getucode);
</script>
<script defer type="text/javascript" src="${contextPath }/js/board/downloadListForm.js?ver=1"></script>
<link href="${contextPath }/css/packinglist/downloadListForm.css" rel="stylesheet"/>
</head>
<body>
<form name="saveList" method="post" >
<c:if test="${not empty requestScope.bnum }">
<input type="hidden" name="bnum" value="${requestScope.bnum }">
</c:if>
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
								<c:if test="${category.ca_num == listing.ca_num }">
									<label><div class="item">
									<input type="hidden" name="checked" value="false">
											<input type="checkbox" class="ckeck" onclick="checkItem(this)">
											<div name="i_name" class="i_name" value="${listing.i_name }">${listing.i_name }</div>
											<div name="icount" class="icount" value="${listing.icount }">${listing.icount }</div>
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
								<c:if test="${category.ca_num == listing.ca_num }">
									<label><div class="item">
									<input type="hidden" name="checked" value="false">											<input type="checkbox" class="ckeck" onclick="checkItem(this)" value="false">
											<div name="i_name" class="i_name" value="${listing.i_name }">${listing.i_name }</div>
											<div name="icount" class="icount" value="${listing.icount }">${listing.icount }</div>
										</div></label>
								</c:if>
							</c:forEach>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="action">
			<input type ="button" id="saveBtn" value="내 리스트에 저장"/>
			<input type="button" id="printbtn" value="인쇄"> 
			<input type="button" id="pdfbtn" value="PDF출력">
		</div>
	</main>
	</form>
</body>
</html>