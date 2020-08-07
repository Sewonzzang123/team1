<<<<<<< Upstream, based on origin/master
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>main</title>
    <link rel="stylesheet" href="main.css" />
    <link
      href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap"
      rel="stylesheet"
    />
    <!-- fontawesome -->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css"
      integrity="sha256-2XFplPlrFClt0bIdPgpz8H7ojnk10H69xRqd9+uTShA="
      crossorigin="anonymous"
    />
    <script defer src="./main_js.jsp"></script>
  </head>
  <body>
    <header>
      <div class="navbar">
        <div class="logo">
          <a href="">
            <img src="img/perage_logo_3.png" alt="logo" />
          </a>
        </div>
        <div class="top_menu">
          <ul>
            <li><a href="">리스트 작성</a></li>
            <li><a href="">게시판</a></li>
            <li><a href="">회원가입</a></li>
            <li><a href="">로그인</a></li>
          </ul>
        </div>
        <i class="fas fa-bars navbar_toggleBtn" id="buggerBtn"></i>
      </div>
    </header>
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
      <span class="main_text">
        Lorem ipsum dolor sit amet, consectetur adipisicing
      </span>
    </div>
    <main>
      <section class="list scroll">
        <div class="list_wrap">
          <h3>리스트 작성</h3>
          <div class="line"></div>
          <div class="createlist">
            <div class="default_list"></div>
            <div class="user_list"></div>
          </div>
        </div>
      </section>

      <section class="tip scroll">
        <div class="list_wrap">
          <h3>여행 팁</h3>
          <div class="line"></div>
          <div class="tip_list">
            <ul>
              <li>
                <a href="">
                  <div class="qna_title">
                    <span>
                      Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                    </span>
                    <span>
                      17:12
                    </span>
                  </div>
                </a>
              </li>
              <li>
                <a href="">
                  <div class="qna_title">
                    <span>
                      Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                    </span>
                    <span>
                      17:12
                    </span>
                  </div>
                </a>
              </li>
              <li>
                <a href="">
                  <div class="qna_title">
                    <span>
                      Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                    </span>
                    <span>
                      17:12
                    </span>
                  </div>
                </a>
              </li>
              <li>
                <a href="">
                  <div class="qna_title">
                    <span>
                      Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                    </span>
                    <span>
                      17:12
                    </span>
                  </div>
                </a>
              </li>
              <li>
                <a href="">
                  <div class="qna_title">
                    <span>
                      Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                    </span>
                    <span>
                      17:12
                    </span>
                  </div>
                </a>
              </li>
              <li>
                <a href="">
                  <div class="qna_title">
                    <span>
                      Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                    </span>
                    <span>
                      17:12
                    </span>
                  </div>
                </a>
              </li>
            </ul>
          </div>
          <div class="more">
            <a href=""><span>더보기</span></a>
          </div>
        </div>
      </section>

      <section class="qna scroll">
        <div class="list_wrap">
          <h3>QnA</h3>
          <div class="line"></div>
          <div class="qna_list">
            <ul>
              <li>
                <a href="">
                  <div class="qna_title">
                    <span>
                      Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                    </span>
                    <span>
                      17:12
                    </span>
                  </div>
                </a>
              </li>
              <li>
                <a href="">
                  <div class="qna_title">
                    <span>
                      Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                    </span>
                    <span>
                      17:12
                    </span>
                  </div>
                </a>
              </li>
              <li>
                <a href="">
                  <div class="qna_title">
                    <span>
                      Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                    </span>
                    <span>
                      17:12
                    </span>
                  </div>
                </a>
              </li>
              <li>
                <a href="">
                  <div class="qna_title">
                    <span>
                      Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                    </span>
                    <span>
                      17:12
                    </span>
                  </div>
                </a>
              </li>
              <li>
                <a href="">
                  <div class="qna_title">
                    <span>
                      Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                    </span>
                    <span>
                      17:12
                    </span>
                  </div>
                </a>
              </li>
              <li>
                <a href="">
                  <div class="qna_title">
                    <span>
                      Lorem, ipsum dolor sit amet consectetur adipisicing elit.
                    </span>
                    <span>
                      17:12
                    </span>
                  </div>
                </a>
              </li>
            </ul>
          </div>
          <div class="more">
            <a href=""><span>더보기</span></a>
          </div>
        </div>
      </section>

      <section class="gallery scroll">
        <div class="list_wrap">
          <h3>갤러리</h3>
          <div class="line"></div>
          <div class="gallery_list">
            <ul>
              <li class="inner">
                <a href="#">
                  <div class="li_img">
                    <img
                      src="https://cdn.pixabay.com/photo/2020/05/05/07/52/republic-of-korea-5131925__340.jpg"
                      alt=""
                    />
                  </div>
                  <div class="li_text">
                    <div class="li_text_poa">
                      <h4 class="li_text_head">
                        Title of content
                      </h4>
                      <p class="li_text_summary">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Duis sit amet tellus velit, ut semper neque.
                      </p>
                    </div>
                  </div>
                </a>
              </li>
              <li class="inner">
                <a href="#">
                  <div class="li_img">
                    <img
                      src="https://cdn.pixabay.com/photo/2018/11/29/21/19/hamburg-3846525__340.jpg"
                      alt=""
                    />
                  </div>
                  <div class="li_text">
                    <div class="li_text_poa">
                      <h4 class="li_text_head">
                        Title of content
                      </h4>
                      <p class="li_text_summary">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Duis sit amet tellus velit, ut semper neque.
                      </p>
                    </div>
                  </div>
                </a>
              </li>
              <li class="inner">
                <a href="#">
                  <div class="li_img">
                    <img
                      src="https://cdn.pixabay.com/photo/2018/01/09/12/20/hamburg-3071437__340.jpg"
                      alt=""
                    />
                  </div>
                  <div class="li_text">
                    <div class="li_text_poa">
                      <h4 class="li_text_head">
                        Title of content
                      </h4>
                      <p class="li_text_summary">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Duis sit amet tellus velit, ut semper neque.
                      </p>
                    </div>
                  </div>
                </a>
              </li>
              <li class="inner">
                <a href="#">
                  <div class="li_img">
                    <img
                      src="https://cdn.pixabay.com/photo/2019/08/14/10/37/beach-4405371__340.jpg"
                      alt=""
                    />
                  </div>
                  <div class="li_text">
                    <div class="li_text_poa">
                      <h4 class="li_text_head">
                        Title of content
                      </h4>
                      <p class="li_text_summary">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Duis sit amet tellus velit, ut semper neque.
                      </p>
                    </div>
                  </div>
                </a>
              </li>
              <li class="inner">
                <a href="#">
                  <div class="li_img">
                    <img
                      src="https://cdn.pixabay.com/photo/2019/08/06/11/58/boat-4388160__340.jpg"
                      alt=""
                    />
                  </div>
                  <div class="li_text">
                    <div class="li_text_poa">
                      <h4 class="li_text_head">
                        Title of content
                      </h4>
                      <p class="li_text_summary">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Duis sit amet tellus velit, ut semper neque.
                      </p>
                    </div>
                  </div>
                </a>
              </li>
              <li class="inner">
                <a href="#">
                  <div class="li_img">
                    <img
                      src="https://cdn.pixabay.com/photo/2019/09/23/16/39/square-4499056__340.jpg"
                      alt=""
                    />
                  </div>
                  <div class="li_text">
                    <div class="li_text_poa">
                      <h4 class="li_text_head">
                        Title of content
                      </h4>
                      <p class="li_text_summary">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Duis sit amet tellus velit, ut semper neque.
                      </p>
                    </div>
                  </div>
                </a>
              </li>
              <li class="inner">
                <a href="#">
                  <div class="li_img">
                    <img
                      src="https://cdn.pixabay.com/photo/2020/07/22/08/39/waterfall-5428467__340.jpg"
                      alt=""
                    />
                  </div>
                  <div class="li_text">
                    <div class="li_text_poa">
                      <h4 class="li_text_head">
                        Title of content
                      </h4>
                      <p class="li_text_summary">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Duis sit amet tellus velit, ut semper neque.
                      </p>
                    </div>
                  </div>
                </a>
              </li>
              <li class="inner">
                <a href="#">
                  <div class="li_img">
                    <img
                      src="https://cdn.pixabay.com/photo/2019/12/01/21/29/walk-4666509__340.jpg"
                      alt=""
                    />
                  </div>
                  <div class="li_text">
                    <div class="li_text_poa">
                      <h4 class="li_text_head">
                        Title of content
                      </h4>
                      <p class="li_text_summary">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                        Duis sit amet tellus velit, ut semper neque.
                      </p>
                    </div>
                  </div>
                </a>
              </li>
            </ul>
          </div>
          <div class="more">
            <a href=""><span>더보기</span></a>
          </div>
        </div>
      </section>

      <!-- <section class="introduce scroll">
        <div class="introduce_img"></div>
      </section> -->
    </main>
    <footer>
      <div class="footer_inner">
        <div class="footer_text">
          <p>Contact Us</p>
          <br />
          <p>Email: : Admin@perfectPackage.co.kr</p>
          <p>Copyright 2020 PerfectPackage All Rights Reserved.</p>
        </div>

        <div class="footer_img">
          <img src="img/perage_logo_f.png" alt="" />
        </div>
      </div>
    </footer>
  </body>
</html>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
	<!-- topmenu -->
	<%@ include file="/WEB-INF/views/layout/uppermost.jsp"%>
	메인 ${sessionScope.member.id } 아이디 ${sessionScope.member.nickname }
</body>
</html>
>>>>>>> d5b286f 수정본
