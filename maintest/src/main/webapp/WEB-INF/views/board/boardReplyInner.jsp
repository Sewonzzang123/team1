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

<link rel="stylesheet" href="${contextPath }/css/board/boardReplyInner.css">
<script defer type="text/javascript" src="${contextPath }/js/board/boardReplyInner.js"></script>


</head>
<body>




		<input type="hidden" id="ucode" name="ucode"		value="${sessionScope.member.ucode  }" />
						
			 <!-- ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼ -->

                           
                            <div class="innerRe">
                                <div class="innerRe_wrapper">

                                    <!-- 댓글 작성 창 -->
                                    <div class="bcomment">
                                        <div class="profile"><img src="../img/1.png" alt=""></div>

                                        <div class="userinfo">
                                            <div>
                                                <span class="IRnickname">코멘트 작성자</span></div>
                                            <div class="typing" contenteditable="false" data-placeholder="댓글 입력..."></div>
                                            <div class="rbtnGrp hidden"> <button id="rwriteBtn">등록</button> <button id="rcancelBtn">취소</button></div>
                                        </div>
                                    </div>
                                    <!-- 댓글 작성 창▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲ -->

                                    <!-- inner comment -->
                                    <div class="parent">
                                        <div class="bcomment">
                                            <div class="profile"><img src="../img/1.png" alt=""></div>
                                            <div class="userinfo">
                                                <div>
                                                    <span class="IRnickname">코멘트 작성자</span>
                                                    <span class="goodOrBad"><a href=""><i
                                                                class="far fa-thumbs-up"></i></a></span>
                                                    <span class="goodOrBad"><a href=""><i
                                                                class="far fa-thumbs-down"></i></a></span>
                                                </div>
                                                <div class="udate">20.08.10</div>
                                                <div class="innerRe_area">
                                                    <span class="IRnickname">부모 아이디</span>
                                                    <div class="typed" contenteditable="true">----------------------내용
                                                        표시
                                                    </div>
                                                    </div>
                                                </div>
                                                <div class="ellipsis"><a href=""><i class="fas fa-ellipsis-v"></i></a>

                                                    <!-- 수정/삭제 히든메뉴 -->
                                                    <div class="ellipsis hiddenMenu">
                                                        <div>
                                                            <a href="">수정</a>
                                                            <a href="">삭제</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>




                                        <!-- inner comment _ reply1 -->
                                        <div class="children">
                                            <div class="bcomment">
                                                <div class="profile"><img src="../img/1.png" alt=""></div>
                                                <div class="userinfo">
                                                    <div>
                                                        <span class="IRnickname">코멘트 작성자</span>
                                                        <span class="goodOrBad"><a href=""><i
                                                                    class="far fa-thumbs-up"></i></a></span>
                                                        <span class="goodOrBad"><a href=""><i
                                                                    class="far fa-thumbs-down"></i></a></span>
                                                    </div>
                                                    <div class="udate">20.08.10</div>
                                                    <div class="innerRe_area">
                                                        <span class="IRnickname">부모 아이디</span>
                                                        <div class="typed" contenteditable="true">
                                                            ----------------------내용
                                                            표시</div>
                                                    </div>
                                                </div>
                                                <div class="ellipsis"><a href=""><i class="fas fa-ellipsis-v"></i></a>

                                                    <!-- 수정/삭제 히든메뉴 -->
                                                    <div class="ellipsis hiddenMenu">
                                                        <div>
                                                            <a href="">수정</a>
                                                            <a href="">삭제</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>



                                        <!-- inner comment _ reply2 -->
                                        <div class="children">
                                            <div class="bcomment">
                                                <div class="profile"><img src="../img/1.png" alt=""></div>
                                                <div class="userinfo">
                                                    <div>
                                                        <span class="IRnickname">코멘트 작성자</span>
                                                        <span class="goodOrBad"><a href=""><i
                                                                    class="far fa-thumbs-up"></i></a></span>
                                                        <span class="goodOrBad"><a href=""><i
                                                                    class="far fa-thumbs-down"></i></a></span>
                                                    </div>
                                                    <div class="udate">20.08.10</div>
                                                    <div class="innerRe_area">
                                                        <span class="IRnickname">부모 아이디</span>
                                                        <div class="typed" contenteditable="true">
                                                            ----------------------내용
                                                            표시</div>
                                                    </div>
                                                </div>
                                                <div class="ellipsis"><a href=""><i class="fas fa-ellipsis-v"></i></a>

                                                    <!-- 수정/삭제 히든메뉴 -->
                                                    <div class="ellipsis hiddenMenu">
                                                        <div>
                                                            <a href="">수정</a>
                                                            <a href="">삭제</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>






                                        <!--  -->
                                    </div>
                                </div>















                                <!-- ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲ -->



</div>





</body>
</html>