<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common.jsp"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 열람</title>
<link rel="stylesheet"
	href="${contextPath}/css/board/boardReadFrm.css?ver=1">
<script defer type="text/javascript"
	src="${contextPath }/js/board/boardReadFrm.js?ver=1"></script>
</head>

<body>


	<main>
		<div class="container">
			<div class="content">
				<section>
					<hr>
					<form id="readModFrm" name="readModFrm" method="POST"
						enctype="multipart/form-data" action="${contextPath}/board/save">
						<input type="hidden" id="bnum" name="bnum"
							value="${requestScope.boardVO.bnum }" /> <input type="hidden"
							id="ucode" name="ucode" value="${requestScope.boardVO.ucode }" />
						<input type="hidden" id="returnPage" name="returnPage"
							value="${requestScope.returnPage}" /> <input type="hidden"
							id="searchType" name="searchType"
							value="${requestScope.searchCriteria.searchType }" /> <input
							type="hidden" id="searchKeyword" name="searchKeyword"
							value="${requestScope.searchCriteria.searchKeyword }" /> <input
							type="hidden" id="catnum" name="catnum"
							value="${requestScope.bcategoryVO.catnum }" /> <input
							type="hidden" id="reqPage" name="reqPage" value="1" />


						<ul>
							<li class="selectGrp"><select name="bcategory.catnum"
								id="bcategory" disabled="disabled">
									<option class="readMode"
										value="${requestScope.boardVO.bcategory.catnum }" selected>
										${requestScope.boardVO.bcategory.catname }</option>
									<c:forEach var="bcate" items="${requestScope.bcategoryList }">
										<option value="${bcate.catnum }">${bcate.catname }</option>
									</c:forEach>
							</select> <select name="hidcategory.hidnum" id="hidcategory"
								disabled="disabled">
									<option class="readMode" value="${boardVO.hidcategory.hidnum }"
										selected>${boardVO.hidcategory.hidname
                    }</option>
									<c:forEach var="hid" items="${requestScope.hidcategoryList}">
										<option value="${hid.hidnum }">${hid.hidname }</option>
									</c:forEach>
							</select></li>
							<li><label for="">제목</label><input type="text" id="btitle"
								name="btitle" value="${requestScope.boardVO.btitle }" readonly></li>
							<li><label for="">작성자</label><input type="text"
								id="bnickname" name="bnickname"
								value="${requestScope.boardVO.bnickname }" readonly /></li>
							<li><label>작성일자</label> <fmt:formatDate
									value="${requestScope.boardVO.bcdate }"
									pattern="yy/MM/dd HH:mm" /></li>
							<li><label>조회수</label> <c:out
									value="${requestScope.boardVO.bhits }" /></li>

							<li><textarea id="bcontent" name="bcontent" cols="30"
									rows="5" placeholder="내용 입력" readonly>${requestScope.boardVO.bcontent }</textarea></li>
							<li>
								<%--  <c:if test="${empty requestScope.files }">
                  <label for="">첨부목록</label>
                  <div>
                    <p>첨부파일 없음</p>
                  </div>
                </c:if>
                <c:if test="${requestScope.files != null}">
                  <label for="">첨부목록</label>
                  <div id="attachments">
                    <c:forEach var="file" items="${requestScope.files }">
                      <div class="attchfiles">
                        <a href="${contextPath }/board/file/${file.fid}/${file.isthumb}">${file.fname
                          }</a><span>(${file.fsize/1024 }kb)</span>
                        <a href="#" class="modifyMode"><i class="fas fa-minus-square" data-fid="${file.fid }"
                            data-isthumb="${file.isthumb} "></i></a>
                      </div>
                    </c:forEach>
                  </div>
                </c:if> --%>
							</li>
						</ul>

						<div class="modifyMode attachments">
							<label for="">파일추가</label> <input type="file" id="files"
								name="files" multiple="multiple" />
						</div>

						<div class="btnGrp">
							<!--읽기모드 -->
							<button id="peplyBtn" type="button" class="readMode">답글</button>
							<c:if
								test="${requestScope.boardVO.ucode == sessionScope.member.ucode }">
								<button id="modifyBtn" type="button" class="readMode">수정</button>
							</c:if>

							<!-- 수정모드 -->
							<button id="saveBtn" type="button" class="modifyMode">저장</button>
							<button id="deleteBtn" type="submit" class="modifyMode">삭제</button>
							<button id="cancelBtn" type="button" class="modifyMode">취소</button>

							<button id="listBtn" type="button">목록</button>
						</div>
					</form>
				</section>
			</div>
		</div>

		<div class="acticle">
			<div class="acticle_wrap">
				<div class="article_top_btn">
					<div class="left_area">
						<input type="button" class="article_del_btn article_btn"
							value="수정"></input> <input type="button"
							class="article_mod_btn article_btn" value="삭제"></input>
					</div>
					<div class="right_area">
						<input type="button" class="article_list_btn article_btn"
							value="목록"></input>
					</div>
				</div>
				<div class="article_contentBox">
					<div class="article_header">
						<div class="article_title">
							<a href="#" class="link_board">${requestScope.boardVO.bcategory.catname }</a>
							<div class="title_area">
								<h3 class="title_text">${requestScope.boardVO.btitle }</h3>
							</div>
						</div>
						<div class="writerInfo">
							<div class="profile_area">
								<div class="nick_box">${requestScope.boardVO.bnickname }</div>
							</div>
							<div class="article_info">
								<span class="date"><fmt:formatDate
										value="${requestScope.boardVO.bcdate }"
										pattern="yy/MM/dd HH:mm" /></span> <span class="count">조회
									${requestScope.boardVO.bhits }</span>
							</div>
						</div>
					</div>
					<div class="article_container">
						<div class="main_container">
							<div class="article_viewer">
								${requestScope.boardVO.bcontent }</div>
						</div>

						<div class="comments_container">
							<div class="comment_option">
								<h3 class="comment_title">댓글</h3>

							</div>
						</div>
					</div>
				</div>
				<div class="article_bottom_btn">
					<div class="left_area">
						<input type="button" class="article_del_btn article_btn"
							value="수정"></input> <input type="button"
							class="article_mod_btn article_btn" value="삭제"></input>
					</div>
					<div class="right_area">
						<input type="button" class="article_list_btn article_btn"
							value="목록"></input> <input type="button"
							class="article_gotop_btn article_btn" value="TOP"></input>
					</div>
				</div>
			</div>
		</div>
	</main>

	<style>
body {
	margin: 0px;
	font-family: "Noto Sans KR", sans-serif;
	font-weight: 400px;
}

a {
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}

.container {
	width: 800px;
	margin: 0px auto;
}

ul {
	list-style-type: none;
}

/* 작성 폼 css */
label {
	display: inline-block;
	min-width: 70px;
}

.content:nth-last-child() h3 {
	
}

#readModFrm {
	min-width: 400px;
	margin: 50px;
}

#readModFrm * {
	box-sizing: border-box;
}

#readModFrm>ul {
	width: 80%;
	margin: 0 auto;
	padding: 0px;
}

#readModFrm>ul li {
	width: 100%;
	display: grid;
	grid-template-columns: 20% 1fr;
}

#readModFrm>ul .selectGrp {
	width: 100%;
	display: grid;
	grid-template-columns: 20% 20% 20%;
}

#readModFrm>ul .selectGrp>select {
	margin-right: 5px;
}

.attachments {
	display: flex;
	width: 80%;
	margin: 0 auto;
	display: none;
}

#readModFrm .btnGrp {
	display: flex;
	justify-content: flex-end;
	width: 80%;
	margin: 0 auto;
}

.modifyMode {
	display: none;
}

.attchfiles {
	display: flex;
}

#readModFrm>ul textarea {
	grid-column: 1/3;
	border-collapse: collapse;
}
</style>
	<style>
.acticle a, .acticle a:visited, .acticle a:hover {
	text-decoration: none;
}

.acticle .acticle_wrap h3 {
	margin: 0;
	padding: 0;
}

.acticle .acticle_wrap {
	position: relative;
	width: 860px;
	margin: 0 auto;
	font-size: 12px;
}

.acticle .acticle_wrap .article_contentBox {
	padding: 29px 29px 0;
	border: 1px solid #ebecef;
	border-radius: 6px;
}

.acticle .acticle_wrap .article_contentBox .article_header {
	position: relative;
	margin-bottom: 20px;
	padding-bottom: 20px;
	border-bottom: 1px solid #ebecef;
}

.article_title {
	margin-bottom: 12px;
	font-size: 13px;
}

.article_title .link_board {
	color: #03c75a;
}

.article_title .title_area {
	margin-top: 7px;
}

.article_title .title_area .title_text {
	font-weight: 400;
	font-size: 26px;
	word-break: break-all;
	word-wrap: break-word;
	word-break: break-word;
}

.writerInfo:after {
	content: "";
	display: block;
	clear: both;
}

.writerInfo .profile_info {
	margin-bottom: 6px;
}

.writerInfo .profile_info .nick_box {
	display: inline-block;
	position: relative;
	vertical-align: top;
}

.writerInfo .article_info {
	font-size: 12px;
	line-height: 13px;
	color: #979797;
}

.writerInfo .article_info .date {
	margin-right: 8px;
}

.comments_container {
	margin-top: 20px;
	border-top: 1px solid #ebecef;
}

.comments_container .comment_option {
	position: relative;
	padding-top: 16px;
	margin-bottom: 11px;
}

.comments_container .comment_option .comment_title {
	/* float: left; */
	margin-top: 3px;
	margin-right: 12px;
	font-size: 17px;
}

.article_top_btn {
	padding: 0 0 14px;
}

.article_bottom_btn {
	padding-top: 14px;
}

.acticle .article_bottom_btn, .acticle .article_top_btn {
	display: flex;
	justify-content: space-between;
}

.acticle .article_btn {
	min-width: 46px;
	height: 36px;
	margin-left: 10px;
	padding: 0 12px;
	font-size: 13px;
	line-height: 36px;
	border: 1px solid #00000000;
	background: #eff0f2;
	color: #000000;
	display: inline-block;
	border-radius: 6px;
	box-sizing: border-box;
	font-weight: 700;
	text-align: center;
	vertical-align: top;
}
</style>
</body>

</html>