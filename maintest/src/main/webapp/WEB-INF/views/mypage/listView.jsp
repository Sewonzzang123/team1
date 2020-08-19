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
<script>
	$(document).ready(function() {

		var ajax = new XMLHttpRequest();
		ajax.onreadystatechange = function() {
			if (ajax.readyState === ajax.DONE) {
				if (ajax.status === 200 || ajax.status === 201) {
					console.log(ajax.responseText);
				} else {
					console.error(ajax.responseText);
				}
			}
		};

		const ck = $(".ckeck");
		ck.change(function(e) {
			console.log('변경됨');
			if (e.target.checked == true) {
				e.target.parentElement.classList.add('del');

				ajax.open("POST", "/maintest/mypage/check"); //
				ajax.send(e.target.getAttribute('linum'));
			} else {
				e.target.parentElement.classList.remove('del');

				ajax.open("POST", "/maintest/mypage/uncheck"); //
				ajax.send(e.target.getAttribute('linum'));
			}
		});

		for ( var i in ck) {

			if (ck[i].checked == true) {
				const parent = ck[i].parentElement
				parent.classList.add('del');
				console.log('변경됨')
			}
		}

		var g_oBeforeBody = document.getElementById('container').innerHTML;

		$('#printbtn').click(function() {

			// 프린트를 보이는 그대로 나오기위한 셋팅
			window.onbeforeprint = function(ev) {
				document.body.innerHTML = g_oBeforeBody;
			};
			window.print();
			location.reload();
		});
	});
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

.container {
	display: inline-flex;
}

.frame {
	display: inline-flex;
	flex-direction: column;
	align-content: flex-start;
	/* margin: auto; */
}

.block {
	text-align: left;
	margin-bottom: 30px;
	width: 330px;
}

.title {
	margin-bottom: 40PX;
}

@media ( max-width : 680px) {
	.container {
		display: inline-flex;
		flex-direction: column;
	}
}
</style>
</head>

<body>

	<main>
		<h3 class="title">have a good day and a better tomorrow</h3>
		<div class="container" id="container">
			<div class="frame">
				<!-- 카테고리 -->
				<c:forEach var="category" items="${requestScope.icategory }"
					begin="0" step="2">
					<div class="block">
						<div class="category">${category.CA_NAME }</div>

						<!-- 아이템 -->
						<c:forEach var="listing" items="${requestScope.listing }">
							<c:if test="${category.CA_NUM == listing.ca_num }">
								<label><div class="item">
										<input type="checkbox" name="ckeck" class="ckeck"
											linum=${listing.linum }
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
				<c:forEach var="category" items="${requestScope.icategory }"
					begin="1" step="2">
					<div class="block">
						<div class="category">${category.CA_NAME }</div>

						<!-- 아이템 -->
						<c:forEach var="listing" items="${requestScope.listing }">
							<c:if test="${category.CA_NUM == listing.ca_num }">
								<label><div class="item">
										<input type="checkbox" name="ckeck" class="ckeck"
											linum=${listing.linum }
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
		<div class="action">
			<input type="button" id="printbtn" value="인쇄"> <input
				type="button" id="pdfbtn" value="PDF출력">
		</div>
	</main>

</body>
</html>