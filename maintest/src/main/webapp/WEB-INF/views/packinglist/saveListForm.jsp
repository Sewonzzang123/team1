<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/included/common_taglib.jsp"%>
<link rel="stylesheet"
	href="${contextPath }/css/packinglist/saveListForm.css">
<script defer src="${contextPath }/js/packinglist/saveListForm.js?ver=1"></script>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>리스트 저장 페이지</title>
</head>
<body>
	<div id="contentsArea">
		<form name="folderList" method="post" >
		<%--action="${contextPath }/packingList/saveList" --%>
			<div class="pop_wrapper"
				style="width: 440px; height: 530px; padding: 0px;">
				<div class="pop_header">
					<div class="pop_title_area">
						<h1 class="pop_title_txt">내 리스트</h1>
					</div>
				</div>
				<div id="content" class="pop_content_wrap estimate_list_wrap">
					<div class="estimate_list_area">
						<c:forEach items="#{list }" var="listVO">
							<p>
								<input type="radio" id="lnum_${listVO.lnum }" name="lnum"
									value="${listVO.lnum }"/> 
								<label for="lnum_${listVO.lnum }">${listVO.lname }</label>
							</p>
					
						</c:forEach>
					</div>
					<div class="folder_add_area">
						<label class="tit_sub" for="estimate_box_txt">리스트 생성</label> <input
							type="text" title="리스트 생성" id="estimate_box_txt"
							placeholder="리스트 명을 입력하세요." maxlength="10" />
						<input type="button" class="folder_add_button" type="button" id="listBtn"
							value="생성하기"/>
					</div>
					<div class="folder_info_wrap">
						<div class="info_title">리스트 저장 안내</div>
						<ul class="info_contents">
							<li>리스트 작성에 담긴 물품들을 내 리스트에 저장할 수 있습니다.</li>
							<li>저장한 리스트는 '리스트 불러오기'에서 확인할 수 있습니다.</li>
							<li>리스트가 저장되어 있는 리스트함에 새롭게 저장 시 기존 아이템 목록은 삭제됩니다.</li>
							<li>물품 체크 및 리스트 삭제는  마이페이지 > 내 리스트를 이용해 주세요.</li>
							<li>리스트 이름은 최소 2자, 최대 10자, 한글, 영문, 숫자만 입력할 수 있습니다.</li>
						</ul>
					</div>
					<div class="estimate_button_area">
						<input class="btn_pop_save" type="button" id ="saveListBtn" value="저장"/>
						<input class="btn_pop_cancel" type="button" id="cancelBtn" value="취소"/>
					</div>
				</div>
			</div>

			<!-- 리스트에 넣을 아이템 정렬 -->
			<c:if test="${not empty listing}">
				<c:forEach items="#{listing }" var="item">
					<input type="hidden" name="i_name" value="${item.i_name }" />
					<input type="hidden" name="icount" value="${item.icount }" />
					<input type="hidden" name="inum" value="${item.inum }" />
					<input type="hidden" name="icategory" value="${item.icategory }" />
					<input type="hidden" name="checked" value="${item.checked }" />
				</c:forEach>
			</c:if>
		</form>
	</div>
</body>
</html>