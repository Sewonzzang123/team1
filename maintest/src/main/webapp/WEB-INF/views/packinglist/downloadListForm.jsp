<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
<script type="text/javascript"
	src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script defer
	src="${pageContext.request.contextPath}/js/mypage/listView.js">
</script>
<style>
main {
	max-width: 800px;
	min-width: 350px;
	text-align: center;
	margin: auto;
}

.category {
	font-weight: bold;
}

.item {
	display: flex;
	align-items: flex-end;
	align-content: center;
	justify-content: left;
	padding-left: 30px;
	margin-bottom: 10px;
	width: 300px
}

.item .ckeck {
	width: 30px;
	border-bottom: 1px solid darkgray;

}

.item .i_name {
	text-align: left;
	width: 200px;
	border-bottom: 1px solid darkgray;
}

.item .icount {
	width: 30px;
	border-bottom: 1px solid darkgray;
}

.del {
	text-decoration: line-through;
	text-decoration-color: red;
	text-decoration-style: solid;
}

.block {
	text-align: left;
	margin-bottom: 30px;
	width: 350px;
}

.title {
	margin-bottom: 40PX;
}

.frame {
	width: 350px;
	margin: 0 auto;
}

@media ( min-width : 700px) {
	.container {
		display: flex;
		width: 700px;
		height: auto;
		margin: auto;
	}
}

@page {
	size: A4;
	margin: 0;
}

@media print {
	html, body {
		width: 210mm;
		height: 297mm;
	}
	.page {
		margin: 0;
		border: initial;
		width: initial;
		min-height: initial;
		box-shadow: initial;
		background: initial;
		page-break-after: always;
	}
	.action {
		display: none;
	}
	.container {
		display: flex;
		width: 700px;
		height: auto;
		margin: auto;
	}
}
</style>
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