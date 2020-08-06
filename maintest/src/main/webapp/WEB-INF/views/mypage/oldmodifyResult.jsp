<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <style>
    table {
      width: 100%;
      border-top: 2px solid #CBCBCB;
      border-bottom: 2px solid #CBCBCB;
      table-layout: fixed;
      border-collapse: collapse;
      border-spacing: 0;
    }

    th {
      color: #737373;
      text-align: left;
      padding-left: 17px;
      background: #EFEFEF;
      font-weight: normal;
    }

  th,td {
      line-height: 1.55em;
      padding-top: 8px;
      padding-bottom: 4px;
      border-bottom: 1px solid #E0E0E0;
    }

    #txt {
      text-align: center;
    }

    #action {
      margin-top: 20px;
      text-align: center;
    }
  </style>
</head>

<body>
  <main id="content" class="modifyResult">
    <div id="txt">
      <div>
        <strong class="org">회원정보가 수정되었습니다.</strong>
      </div>
    </div>

    <table>
      <colgroup>
        <col style="width: 200px">
        <col style="width: 600px">
      </colgroup>

      <h3>기본정보</h3>
      <tbody>
        <tr>
          <th>아이디</th>
          <td>아이디 값</td>
        </tr>
        <tr>
          <th>이름</th>
          <td>이름 값</td>
        </tr>
        <tr>
          <th>별명</th>
          <td>별명 값</td>
        </tr>
        <tr>
          <th>전화번호</th>
          <td>전화번호 값</td>
        </tr>
      </tbody>
    </table>

    <div id="action"><input type="button" value="메인페이지로"> <input type="button" value="이전 페이지"> </div>
  </main>


</body>
</html>