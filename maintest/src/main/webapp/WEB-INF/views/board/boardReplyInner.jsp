<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common.jsp"%>

    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>

<link rel="stylesheet" href="${contextPath }/css/board/boardReplyInner.css?ver=2">
<script defer type="text/javascript" src="${contextPath }/js/board/boardReplyInner.js?ver=2"></script>


</head>
<body>

		<input type="hidden" id="ucode" name="ucode" value="${sessionScope.member.ucode  }" />						

                           
   <div class="innerRe">
        <div class="innerRe_wrapper" data-bcnum="0">
	
            <!-- 댓글 작성 창 -->
            <div class="filloutHere bcomment" id="filloutHere" >
        
                <div class="profile"><img src="../img/1.png" alt=""></div>
                <div class="userinfo">
                    <div><span class="IRnickname" id="IRnickname">${sessionScope.member.nickname }</span></div>
                    <c:choose>
                    <c:when test="${! empty sessionScope.member }">
                    <div class="typing" contenteditable="false" data-placeholder="댓글 입력..."></div>
                    </c:when>
                      <c:when test="${empty sessionScope.member}">
                      <div class="typing disabled" contenteditable="false" data-placeholder="로그인 후 이용가능..."></div>                  
                     </c:when>                    
                    </c:choose>
                    <div class=" rbtnGrp hidden" id="rbtnGrp">                        
                        <button class="btn" id="rwriteBtn" onClick="rbtnGrpTag_f(this)">등록</button>
                        <button class="btn" id="rcancelBtn" onClick="rbtnGrpTag_f(this)">취소</button>
                    </div>
                </div>
          
            </div>
            
            
            <!-- 댓글 목록-->

            <!-- inner comment -->
            <div class="bcomment_wrapper" id="replaceableArea">
<%-- 			<c:forEach var="data" items="${requestScope.innerList}">
			  <c:if test="${data.bcindent==0 }">
              <div class="parent" data-bcnum="${data.bcnum}">
              </c:if>
              <c:if test="${ data.bcindent!=0 }">
              <div class="children" data-bcnum="${data.bcnum}" data-bcgrp="${data.bcgrp}" }>
              </c:if>
              <!---->
                    <div class="bcomment">                    
                        <div class="profile"><img src="#" alt=""></div>
                        <div class="userinfo">
                            <div>
                                <span class="IRnickname" data-nickname="${data.ucode }">${data.nickname}</span> 
                                <span class="goodOrBad"><i class="far fa-thumbs-up"></i>${data.bcgood }</span>
                                <span class="goodOrBad"><i class="far fa-thumbs-down"></i>${data.bcbad }</span>
                            </div>
                            <div class="udate"><span>
                            <fmt:formatDate value="${data.udate}" pattern="MM/dd"/></span>
                            <button class="btn reReply">답글쓰기</button></div>

                            <div class="innerRe_area">
                                <span class="IRnickname"  }>${data.pnickname}</span>
                                <div class="typed" contenteditable="false">${data.bccontent}</div>
                            </div>
                        </div>
                        <div class="ellipsis"><i class="fas fa-ellipsis-v" ></i>
                            <!-- 수정/삭제 히든메뉴 -->
                            <div class="ellipsis  hiddenMenu hidden" >
                                <div>
                                    <button type="button" onClick="modDelBtn_f(this)">수정</button>
                                    <button type="button" onClick="modDelBtn_f(this)">삭제</button>
                                </div>
                            </div>
                        </div>
                      </div> 
    			 </div><!-- parent/childeren  -->
			</c:forEach> --%>
		</div><!-- bcomment_wrapper  -->
	</div><!-- innerRe_wrapper  -->
</div><!-- innerRe -->

</body>
</html>