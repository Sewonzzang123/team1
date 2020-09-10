<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/layout/modal.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/member/loginForm.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
	integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
	crossorigin="anonymous"></script>
</head>
<body>


	<div class="box-container">
		<!-- header -->
		<header>
			<%@ include file="/WEB-INF/views/layout/logo.jsp"%>
		</header>

		<!-- main -->
		<main id="page" class="signupForm">
			<form:form modelAttribute="info">
				<fieldset class="login_form">
					<div class="id_area">
						<div class="input_row">
							<input type="text" id="id" name="id" class="int"
								placeholder="아이디" maxlength="20">
						</div>
					</div>

					<div class="pw_area">
						<div class="input_row">
							<input type="password" id="pw" name="pw" class="int"
								placeholder="비밀번호" maxlength="10">
						</div>
					</div>
					<div>
						<span class="errmsg">${requestScope.svr_msg }</span>
					</div>
					<div class="actionBtn">
						<input type="submit" title="로그인" id="login" class="btn"
							value="로그인"
							formaction="${pageContext.request.contextPath}/login?reqURI=${reqURI}" />
					</div>

					<dvi class="check_info">
					<div class="login_check">
						<span class="login_check_box"> <input type="checkbox"
							id="login_chk" name="login_chk" value="off"> <label
							for="login_chk" id="label_login_chk">로그인 상태 유지</label> <input
							type="hidden" id="login_chk_val" name="login_chk_val" value="off" />
						</span>
					</div>
					</dvi>
				</fieldset>
			</form:form>

			<div class="linkbar">
				<a href="${pageContext.request.contextPath}/findIDForm">아이디 찾기</a> |
				<a href="${pageContext.request.contextPath}/findPWForm">비밀번호 찾기</a>
				| <a href="${pageContext.request.contextPath}/signupForm">회원가입</a>
			</div>
		</main>
	</div>
	<!-- footer -->

	<!-- 모달 -->
	<div class="modal fade" id="myModal" data-backdrop="static"
		data-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">자동 로그인</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					자동로그인을 사용하시면 다음부터 아이디와 패스워드를 입력하실 필요가 없습니다. <br> <br>
					공공장소에서는 개인정보가 유출될 수 있으니 사용을 자제하여 주십시오. <br> <br> 자동로그인을
					사용하시겠습니까?
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary">확인</button>
				</div>
			</div>
		</div>
	</div>
</body>

<script>
  const myModal = document.querySelector('#myModal');
  const btn_cancel = document.querySelector('.btn-secondary');
  const btn_check = document.querySelector('.btn-primary');
  const login_chk = document.querySelector('#login_chk');
	const login_chk_val = document.getElementById('login_chk_val')
	
  login_chk.addEventListener('change', (e) => {

    if (e.target.checked == true)
      $('#myModal').modal('show');
  });

  btn_check.addEventListener('click', () => {
	  login_chk.value = 'on';
	  login_chk_val.value = "on"
		  
    $('#myModal').modal('hide');
  })

  btn_cancel.addEventListener('click', () => {
	  login_chk.value = 'off';
	  login_chk_val.value = "off"
		  
	  login_chk.checked = false;
    $('#myModal').modal('hide');
  })
</script>

</html>