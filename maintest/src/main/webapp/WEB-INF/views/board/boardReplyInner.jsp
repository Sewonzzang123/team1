<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
<%@ include file="/WEB-INF/views/included/common.jsp"%>


<link rel="stylesheet" href="${contextPath }/css/board/boardReplyInner.css?ver=3">
<script defer type="text/javascript" src="${contextPath }/js/board/boardReplyInner.js?ver=2"></script>



		<input type="hidden" id="ucode" name="ucode" value="${sessionScope.member.ucode  }" />						
                           
   <div class="innerRe">
        <div class="innerRe_wrapper" data-bcnum="0">
	
            <!-- 댓글 작성 창 -->
            <div class="filloutHere bcomment" id="filloutHere" >   
                <div class="profile">
                <div  class="profileImg">${sessionScope.member.nickname.substring(0,1) }</div></div>
                <div class="userinfo">
                    <div><span class="IRnickname" id="IRnickname" data-sessionUcode="${sessionScope.member.ucode }">${sessionScope.member.nickname }</span></div>
                    <c:choose>
                    <c:when test="${! empty sessionScope.member }">
                    <div class="typing isLogin" contenteditable="false"  data-isLogin="true" data-placeholder="댓글 입력..."></div>
                    </c:when>
                      <c:when test="${empty sessionScope.member}">
                      <div class="typing disabled" contenteditable="false"  data-isLogin="false" data-placeholder="로그인 후 이용가능..."></div>                  
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
					<!--ajax data  -->
					
					
					
					</div><!-- bcomment_wrapper  -->
			<button type="button"  class="btn btn-showMore"  id="showMoreBtn" onClick="showMore_f(this)"  data-innerRqPage="1">더보기</button> 
	</div><!-- innerRe_wrapper  -->
</div><!-- innerRe -->
