<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/included/common.jsp"%>
<title>main</title>
<script defer src="${pageContext.request.contextPath}/js/main.js"></script>
<script>
	var getid = null;
	if ('${sessionScope.member.id}'.trim() == null) {
		getid = null;
	} else {
		getid = '${sessionScope.member.id}';
	}
</script>
<script defer
	src="${pageContext.request.contextPath}/js/packinglist/packingList.js?ver=335"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/packinglist/packingList.css? ver=2">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap"
	rel="stylesheet" />
<!-- fontawesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css"
	integrity="sha256-2XFplPlrFClt0bIdPgpz8H7ojnk10H69xRqd9+uTShA="
	crossorigin="anonymous" />

</head>
<body>




	<!-- header -->
	<%@ include file="/WEB-INF/views/layout/header.jsp"%>

	<div class="menu_wrap">
		<div class="hide_wrap">
			<i class="fa fa-times" id="buggerBtn_hide"></i>
		</div>

		<ul>
			<li><a href="">리스트 작성</a></li>
			<li><a href="">게시판</a></li>
			<li><a href="">회원가입</a></li>
			<li><a href="">로그인</a></li>
		</ul>
	</div>
	<!-- 햄버거 버튼 열면 뒤에 깔리는 반투명 검정 배경 -->
	<div class="cover_close"></div>
	<div class="main_img">
		<span class="main_text"> Lorem ipsum dolor sit amet,
			consectetur adipisicing </span>
	</div>
		<a name="list_wrap"></a>
	<main>
		<section class="list scroll">
			<div class="list_wrap">
				<h3>리스트 작성</h3>
				<div class="line"></div>
				<form target="_blank" method="get" name="form">
					<div class="createlist">
						<div class="default_list tab_box_container">
							<%@ include file="/WEB-INF/views/packinglist/itemList.jsp"%>
						</div>
						<div class="user_list tab_menu_container">
							<%@ include file="/WEB-INF/views/packinglist/category.jsp"%>
						</div>
					</div>
				</form>
			</div>
		</section>


		<section class="tip scroll">
			<div class="list_wrap">
				<h3>여행 팁</h3>
				<div class="line"></div>
				<div class="tip_list">
					<ul>
						<!-- begin : data 들어오는 곳  -->
						<c:forEach var="tdata" items="#{requestScope.tipBoardVO }">

							<li><a href="${contextPath }/board/read/1/${tdata.bnum}">
									<div class="qna_title">
										<span> ${tdata.btitle }</span> <span> <fmt:formatDate
												value="${tdata.budate }" pattern="MM/dd" />
										</span>
									</div>
							</a></li>
						</c:forEach>

						<!-- end : data 들어오는 곳  -->


					</ul>
				</div>
				<div class="more">
					<a href="${contextPath }/board/1"><span>더보기</span></a>
				</div>
			</div>
		</section>

		<section class="qna scroll">
			<div class="list_wrap">
				<h3>QnA</h3>
				<div class="line"></div>
				<div class="qna_list">
					<ul>
						<!-- begin : data 들어오는 곳  -->
						<c:if test="${empty qaBoardVO}"> 게시글 없음  </c:if>
						<c:forEach var="qdata" items="#{requestScope.qaBoardVO }">
							<li><a href="${contextPath }/board/read/3/${qdata.bnum}">
									<div class="qna_title">
										<span> ${qdata.btitle }</span> <span> <fmt:formatDate
												value="${qdata.budate }" pattern="MM/dd" />
										</span>
									</div>
							</a></li>
						</c:forEach>

						<!-- end : data 들어오는 곳  -->
					</ul>
				</div>
				<div class="more">
					<a href="${contextPath }/board/3"><span>더보기</span></a>
				</div>
			</div>
		</section>

		<section class="gallery scroll">
			<div class="list_wrap">
				<h3>갤러리</h3>
				<div class="line"></div>
				<div class="gallery_list">


					<ul>
						<!-- data 들어오는 부분 loop-->
						<c:forEach var="gdata" items="#{requestScope.galBoardVO}">
							<li class="inner"><a
								href="${contextPath }/board/read/2/${gdata.bnum}">
									<div class="li_img">
										<img name=""
											src="${contextPath }/${gdata.thumbnai;}"
											alt="" />
									</div>
									<div class="li_text">
										<div class="li_text_poa">
											<h5 class="li_text_head">${gdata.btitle }</h5>
											<p class="li_text_summary">${gdata.bcontent}</p>
										</div>
									</div>
							</a></li>
						</c:forEach>
					</ul>
				</div>
				<div class="more">
					<a href="${contextPath }/board/2"><span>더보기</span></a>
				</div>
			</div>
		</section>








		</section>

		<!-- <section class="introduce scroll">
        <div class="introduce_img"></div>
      </section> -->
	</main>
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
</body>
</html>
