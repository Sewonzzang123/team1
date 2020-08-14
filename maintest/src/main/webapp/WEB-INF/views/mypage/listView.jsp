<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>

<style>
.container {
	margin-bottom: 20px;
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
</style>
</head>

<body>

	<main>
		<h3>have a good day and a better tomorrow</h3>

		<!-- 카테고리 -->

		<c:forEach var="category" items="${requestScope.icategory }">
			<div class="container">
				<div class="category">${category.CA_NAME }</div>

				<!-- 아이템 -->
				<c:forEach var="listing" items="${requestScope.listing }">
					<label><div class="item">
							<input type="checkbox" name="ckeck" class="ckeck"
								<c:if test="${listing.checked eq 'true'}"> checked="true"</c:if>>
							<div name="i_name" class="i_name">${listing.i_name }</div>
							<div name="icount" class="icount">${listing.icount }</div>
						</div></label>

				</c:forEach>
			</div>
		</c:forEach>


		<!-- 카테고리 -->
		<c:forEach var="category" items="${requestScope.icategory }">
			<div class="category">${category.CA_NAME }</div>

			<!-- 아이템 -->
			<c:forEach var="listing" items="${requestScope.listing }">
				<div class="item">
					<input type="checkbox" name="ckeck" class="ckeck">
					<div name="i_name" class="i_name">${listing.i_name }</div>
					<div name="icount" class="icount">${listing.icount }</div>
				</div>
			</c:forEach>
		</c:forEach>
	</main>

</body>
</html>