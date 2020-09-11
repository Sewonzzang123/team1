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

<link rel="stylesheet" href="${contextPath }/css/board/boardReplyInner.css?ver=1">
<script defer type="text/javascript" src="${contextPath }/js/board/boardReplyInner.js?ver=1"></script>


</head>
<body>

		<input type="hidden" id="ucode" name="ucode"		value="${sessionScope.member.ucode  }" />						

                           
   <div class="innerRe">
        <div class="innerRe_wrapper">
	
            <!-- 댓글 작성 창 -->
            <div class="filloutHere" id="filloutHere">
                <div class="profile"><img src="../img/1.png" alt=""></div>
                <div class="userinfo">
                    <div><span class="IRnickname">${sessionScope.member.nickname }</span></div>
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
            
            
            <!-- 댓글 작성 창▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲ -->

            <!-- inner comment -->

            <div class="bcomment_wrapper" id="replaceableArea">

                <div class="parent" data-bcnum="42">
                    <div class="bcomment">
                        <div class="profile"><img src="../img/1.png" alt=""></div>
                        <div class="userinfo">
                            <div>
                                <span class="IRnickname">코멘트 작성자</span> 
                                <span class="goodOrBad"><a href=""><i class="far fa-thumbs-up"></i></a></span>
                                <span class="goodOrBad"><a href=""><i class="far fa-thumbs-down"></i></a></span>
                            </div>
                            <div class="udate"><span>20.08.10</span><button class="btn reReply"  >답글쓰기</button></div>

                            <div class="innerRe_area">
                                <span class="IRnickname">부모 아이디</span>
                                <div class="typed" contenteditable="false"> ----------------------내용 표시</div>
                            </div>
                        </div>
                        <div class="ellipsis"><i class="fas fa-ellipsis-v" id="ellipsis" ></i>
                            <!-- 수정/삭제 히든메뉴 -->
                            <div class="ellipsis  hiddenMenu hidden" >
                                <div>
                                    <button type="button">수정</button>
                                    <button type="button">삭제</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>




                <!-- inner comment _ reply1 -->
                <div class="children" data-bcnum="43">
                    <div class="bcomment">
                        <div class="profile"><img src="../img/1.png" alt=""></div>
                        <div class="userinfo">
                            <div>
                                <span class="IRnickname">코멘트 작성자</span> 
                                <span class="goodOrBad"><a href=""><i class="far fa-thumbs-up"></i></a></span>
                                <span class="goodOrBad"><a href=""><i class="far fa-thumbs-down"></i></a></span>
                            </div>
                            <div class="udate">20.08.10</div><button>답글쓰기</button>

                            <div class="innerRe_area">
                                <span class="IRnickname">부모 아이디</span>
                                <div class="typed" contenteditable="false"> ----------------------내용 표시</div>
                            </div>
                        </div>
                        <div class="ellipsis"><i class="fas fa-ellipsis-v" id="ellipsis" data-bcnum="43"></i>
                            <!-- 수정/삭제 히든메뉴 -->
                            <div class="ellipsis  hiddenMenu hidden" data-bcnum="43">
                                <div>
                                    <button type="button">수정</button>
                                    <button type="button">삭제</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <!--  -->
            </div>


        </div>
    </div>

</body>
</html>