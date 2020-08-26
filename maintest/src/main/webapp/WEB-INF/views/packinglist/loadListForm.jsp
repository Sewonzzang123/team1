<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/included/common_taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<script src="https://kit.fontawesome.com/5c7aae1e3b.js"
	crossorigin="anonymous"></script>
<style>
.pop_wrapper {
	padding-bottom: 33px;
}

.pop_header {
	position: relative;
	border-bottom: 2px solid #343434;
	color: #000;
	padding: 7px 20px 10px;
	line-height: 26px;
}

.estimate_load_title {
	display: inline-block;
}

.row_a:after {
	content: "";
	display: block;
	clear: both;
}

.pop_header .pop_title_txt {
	font-size: 18px;
	font-family: "Malgun Gothic", Dotum, "돋움", "Apple SD Gothic Neo",
		Helvetica, Sans-serif;
	margin: 0;
	letter-spacing: -0.05em;
}

.grid_col {
	float: left;
	font-size: 12px;
}

.list_select_wrap {
	position: absolute;
	top: 0;
	left: 0;
	min-width: 250px;
}

.estimate_prod_wrap {
	position: relative;
	display: inline-block;
	min-width: 250px;
	height: 20px;
}

.manage_btn_wrap {
	float: right;
}

.manage_btn_wrap .manage_btn {
	font-size: 13px;
	display: inline-block;
	width: 100px;
	height: 28px;
	line-height: 28px;
	border: 1px solid #acacac;
	font-weight: bold;
	text-align: center;
	color: #333;
	text-decoration-line: none;
}

.estimate_load_wrap {
	width: 100%;
	height: 550px;
	overflow-y: scroll;
	overflow-x: hidden;
	padding: 20px 10px 20px 20px;
	box-sizing: border-box;
}

.pop_footer_t1 {
	position: fixed;
	bottom: 0;
	border-top: 1px solid #e5e5e5;
	background: #fff;
	padding: 3px 8px 0;
	width: 100%;
	height: 33px;
	box-sizing: border-box;
	padding-left:75%;
}

.row {
	clear: left;
	overflow: hidden;
}

.txt_hide {
	text-indent: -9999px;
	font-size: 0;
	height: 0;
	width: 0;
}

.btn_pop_close, .btn_upload {
	border: none;
	background: #fff;
	font-weight: bold;
	color: #333;
	font-size: 12px;
	height: 26px;
	padding: 0 4px;
	vertical-align: top;
	cursor: pointer;
}

.grid_col_r {
	float: right;
}

.estimate_list_select {
	width: 108px;
	height: 21px;
	line-height: 21px;
	border: 1px solid #999;
	margin: 0 10px;
}

.estimate_list_table table {
	text-align:center;
	width: 100%;
}

.estimate_list_table table>*>tr>th:nth-child(1) {
	width: 15%;
}

.estimate_list_table table>*>tr>th:nth-child(2) {
	width: 15%;
}

.estimate_list_table table>*>tr>th:nth-child(3) {
	width: 50%;
}

.estimate_list_table table>*>tr>th:nth-child(4) {
	width: 20%;
}

.estimate_list_table th {
	height: 38px;
	text-align: center;
	border: 1px solid #bcbcbc;
	border-width: 1px 0;
	background: #fafafa;
}

.estimate_list_table td:first-child {
	border-left: 0;
}

.estimate_list_table td:last-child {
	border-right: 0;
}

.estimate_list_table tbody>tr>td {
	border: 1px solid #f2f2f2;
}

.estimate_list_table tfoot tr {
	height: 38px;
	text-align: center;
	border: 1px solid #bcbcbc;
	border-width: 1px 0;
	background: #fafafa;
}

.estimate_list_table tfoot td {
	height: 38px;
	line-height: 38px;
	padding: 0;
	text-align: center;
	font-weight: bold;
	color: #333;
	background: #f9f9f9;
}

.estimate_list_table td {
	border-color: #f0f0f0;
}
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
	width: 350px;
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
						<% int i=0; %>
							<c:forEach var="list" items="${listVO}">			
							 <c:if test="${list.lnum eq requestScope.lnum}">
							 <option value="${list.lnum }" selected="selected">${list.lname }</option>
							 </c:if>
							<c:if test="${list.lnum ne requestScope.lnum}">
								<option value="${list.lnum }">${list.lname }</option>
								</c:if>
						<% i++ ;%>
							</c:forEach>
						</select> <span> 리스트 갯수 : <strong><%=i%></strong>개	 
						</span>
					</div>
				</div>
			</div>
			<div class="manage_btn_wrap">
				<a href="#" target="_blank" class="manage_btn"> 리스트 관리 <i
					class="fas fa-chevron-right"></i>
				</a>
			</div>
		</div>
		<!--content-->
		<div id="content" class="pop_content_wrap estimate_load_wrap">
			<div class="estimate_list_table">
				<div class="container" id="container">
			<div class="frame">
				<!-- 카테고리 -->
				<c:forEach var="category" items="${icategory }"
					begin="0" step="2">
					<div class="block">
						<div class="category">${category.ca_name }</div>

						<!-- 아이템 -->
						<c:forEach var="listing" items="${requestScope.listingVO }">
							<c:if test="${category.ca_num == listing.CategoryVO.ca_num }">
								<label><div class="item">
										<input type="checkbox" name="ckeck" class="ckeck"
											linum=${listing.LINUM }
											<c:if test="${listing.CHECKED eq 'true'}"> checked="true"</c:if>>
										<div name="i_name" class="i_name">${listing.itemVO.i_name }</div>
										<div name="icount" class="icount">${listing.ICOUNT }</div>
									</div></label>
							</c:if>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
			<div class="frame">
						<c:forEach var="category" items="${icategory }"
							begin="1" step="2">
							<div class="block">
								<div class="category">${category.ca_name }</div>

								<!-- 아이템 -->
								<c:forEach var="listing" items="${requestScope.listingVO }">
									<c:if test="${category.ca_num == listing.CategoryVO.ca_num }">
										<label><div class="item">
												<input type="checkbox" name="ckeck" class="ckeck"
													linum=${listing.LINUM }
													<c:if test="${listing.checked eq 'true'}"> checked="true"</c:if>>
												<div name="i_name" class="i_name">${listing.itemVO.i_name }</div>
												<div name="icount" class="icount">${listing.ICOUNT }</div>
											</div></label>
									</c:if>
								</c:forEach>
							</div>
						</c:forEach>
					</div>
		</div>
			</div>
		</div>
		<!--footer-->
		<div class="pop_footer_t1 row">
			<div class="grid_col_r">
				<button type="button" class="btn_upload" onclick="uploadBtn_f()"><span>불러오기</span></button>
				<button type="button" class="btn_pop_close"
					onclick="window.close();">
					<i class="fas fa-times"></i> <span>닫기</span>
				</button>
			</div>
		</div>
	</div>
</body>
<script>
 function changeList_f(e){
	 console.log(e.value);
	 let lnum = e.value;
	 let url = "http://localhost:9080/pfpkg/itemlist/loadListForm/"+lnum;
	 window.location.href=url;
	 }


 	function uploadBtn_f(){
 		let lnum = document.querySelector('option[selected="selected"]').value;
		let url = "http://localhost:9080/pfpkg/itemlist/category2/"+lnum;
		console.log("dd");
		window.opener.location = url;
		window.close();
 	 	}
	 
 
	</script>
</html>
