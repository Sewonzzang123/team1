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
</head>
<body>
	<main>
			<!-- uppermost  메뉴  -->
		<%@ include file="/WEB-INF/views/layout/header.jsp"%>
		<!-- 메인 베너 _ 이미지  + hidden 요소 catnum, returnPage-->
		<%@ include file="/WEB-INF/views/layout/mainbanner.jsp"%>
		<div class="main_wrap">
			<!-- 게시판 카테고리 메뉴  -->
			<%@ include file="/WEB-INF/views/board/included/boardAsideMenu.jsp"%>

			<div class="acticle">
				<div class="acticle_wrap">
					<div class="hidden_area">
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
							value="${requestScope.boardVO.bcategory.catnum }" /> <input
							type="hidden" id="reqPage" name="reqPage" value="1" />
					</div>
					<div class="article_top_btn">
						<div class="left_area">
							<c:if
								test="${requestScope.boardVO.ucode == sessionScope.member.ucode}">
								<input type="button" class="article_del_btn article_btn"
									value="수정"></input>
								<input type="button" class="article_mod_btn article_btn"
									value="삭제"></input>
							</c:if>
						</div>
						<div class="right_area">
							<input type="button" class="article_list_btn article_btn"
								value="목록"></input>
						</div>
					</div>
					<div class="article_contentBox">
						<div class="article_header">
							<div class="article_title">
								<a
									href="
${pageContext.request.contextPath}/board/${requestScope.boardVO.bcategory.catnum }"
									class="link_board">${requestScope.boardVO.bcategory.catname }</a>
								<div class="title_area">
									<h3 class="title_text">${requestScope.boardVO.btitle }</h3>
								</div>
							</div>
							<div class="writerInfo">
								<div class="profile_area">
									<div class="nick_box">${requestScope.boardVO.bnickname }</div>
								</div>
								<div class="article_info">
									<span class="date"> <fmt:formatDate
											value="${requestScope.boardVO.bcdate }"
											pattern="yy/MM/dd HH:mm" /></span> <span class="count">조회
										${requestScope.boardVO.bhits }</span>
								</div>
							</div>
						</div>
						<div class="article_container">
							<div class="AttachFileList">
								<c:if test="${requestScope.files != null}">
									<div class="attach_file">
										<a href="#" class="button_file" role="button">첨부파일</a>
										<div class="layer_attach" style="display: none;">
											<ul class="list">
												<c:forEach var="file" items="${requestScope.files }">
													<li class="AttachFileListItem">
														<div class="file_name">
															<a
																href="${contextPath }/board/file/${file.fid}/${file.isthumb}">${file.fname
                                  }</a>
														</div> <span>(${file.fsize/1024 }kb)</span> <a href="#"
														class="modifyMode"><i class="fas fa-minus-square"
															data-fid="${file.fid }" data-isthumb="${file.isthumb} "></i></a>
													</li>
												</c:forEach>
											</ul>
										</div>
									</div>
								</c:if>
							</div>
							<div class="main_container">
								<div class="article_viewer">
									${requestScope.boardVO.tcontent }</div>
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
							<c:if
								test="${requestScope.boardVO.ucode == sessionScope.member.ucode}">
								<input type="button" class="article_del_btn article_btn"
									value="수정"></input>
								<input type="button" class="article_mod_btn article_btn"
									value="삭제"></input>
							</c:if>
						</div>
						<div class="right_area">
							<input type="button" class="article_list_btn article_btn"
								value="목록"></input> <input type="button"
								class="article_gotop_btn article_btn" value="TOP"></input>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>
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

.writerInfo .profile_area {
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

.acticle .article_top_btn {
	padding-bottom: 14px;
}

.acticle .article_bottom_btn {
	padding-top: 14px;
}

.acticle .article_bottom_btn .left_area, .acticle .article_top_btn .left_area
	{
	float: left;
}

.acticle .article_bottom_btn .right_area, .acticle .article_top_btn .right_area
	{
	float: right;
}

.acticle .article_bottom_btn:after, .acticle .article_top_btn:after {
	content: "";
	display: block;
	clear: both;
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

.acticle .article_btn:first-child {
	margin-left: 0;
}

.AttachFileList, .AttachFileList .attach_file {
	position: relative;
}

.AttachFileList {
	margin: -2px 0 18px;
	text-align: right;
}

.AttachFileList .button_file {
	display: inline-block;
	font-size: 13px;
	color: #323232;
}

.AttachFileList .layer_attach {
	overflow-y: auto;
	position: absolute;
	top: 100%;
	right: 0;
	z-index: 11;
	max-width: 500px;
	max-height: 416px;
	margin-top: 6px;
	padding: 10px 0;
	border: 1px solid rgba(0, 0, 0, .06);
	border-radius: 6px;
	box-shadow: 0 1px 12px 0 rgba(0, 0, 0, .06);
	background-color: #fff;
	box-sizing: border-box;
	color: #000;
}

.AttachFileListItem .file_name {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	display: block;
	max-width: 100%;
}

.AttachFileListItem {
	display: flex;
	min-width: 100px;
	height: 36px;
	padding: 0 15px;
	box-sizing: border-box;
	font-size: 13px;
	line-height: 37px;
	white-space: nowrap;
	color: #000;
}

.added_img {
	display: block;
	max-width: 500px;
	margin: auto;
}
</style>

<script>

  const acticle = document.querySelector('.acticle');
  const layer_attach = document.querySelector('.layer_attach');
  const bnum = document.querySelector('#bnum').value;
  console.log(bnum);

  acticle.addEventListener('click', (e) => {
    // 목록 버튼
    if (e.target.classList.contains('article_list_btn')) {
      listBtn_f(e)
    }

    // 삭제 버튼
    if (e.target.classList.contains('article_mod_btn')) {        
    	if(confirm('게시글을 삭제하시겠습니까?')){
    		location.href="${pageContext.request.contextPath}/board/delete/"+bnum;
        	}
    }

    // 수정 버튼
    if (e.target.classList.contains('article_del_btn')) {
      console.log('수정 요청')
      location.href = '${pageContext.request.contextPath}/board/modifyFrm/'+bnum;
    }

    // gotop 버튼
    if (e.target.classList.contains('article_gotop_btn')) {
      window.scrollTo(0, 0);
    }

    // 첨부파일 버튼
    if (e.target.classList.contains('button_file')) {
      e.preventDefault();
      if (layer_attach.getAttribute('style') == 'display: none;') {
        layer_attach.style.display = null;
      } else {
        layer_attach.style.display = 'none';
      }
    }
  })

</script>

</html>