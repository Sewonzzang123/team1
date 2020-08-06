<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        
        

       
    </style>
</head>

<body>
    <main id="content" class="withdrawForm">
        <h3>
            회원탈퇴 신청
        </h3>
        <div>
            - 비밀번호를 입력 후 확인을 클릭하시면 탈퇴가 완료됩니다. <br>
            - 게시판에 작성하신 글은 자동으로 삭제되지 않습니다.
        </div>
        <div id="inputForm">
            <form action="">
                <div>
                    <div class="form_head">
                        <strong>아이디</strong>
                    </div>
                    <div class="form_data">
                        아이디폼
                    </div>
                </div>
                <div>
                    <div class="form_head">
                        <strong>비밀번호</strong>
                    </div>
                    <div class="form_data">
                        <input id="pw" type="password" name="pw">
                    </div>
                </div>
                <div class="actionBtn">
                    <input type="submit" value="확인"><input type="button" value="취소">
                </div>
            </form>
        </div>
    </main>
</body>
</html>