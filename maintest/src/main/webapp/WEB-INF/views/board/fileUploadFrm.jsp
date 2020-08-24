<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 공통모듈 -->
 	<%@ include file="/WEB-INF/views/included/common.jsp"  %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>첨부파일</title>

<style> 
div{
display:inline-block;
outline:auto;
padding :10px;
}



.attachmentsArea{
display:flex;
width:300px;
height:400px;
opacity: 0.7;
align-items: center;
justify-content: center;
}

.attachmentsArea .content{

}
p{

magin: 0px auto;}


input[type="file"]{
display:none;
}

</style>


<script defer>

window.addEventListener("load", init);

function init(){
const dropZone = document.getElementById("attachmentsArea");
const filesTag =  document.getElementById("files");
dropZone.addEventListener("click", function(e){
	filesTag.click();
});

dropZone.addEventListener("dragstart", dragStart)
dropZone.addEventListener("dragover", dragover)
}


function dragStart(e){
	console.log(e)
}

function dragover(e){
	console.log(e)
}

</script>
</head>
<body>



<div class="attachmentsArea" id="attachmentsArea">
<div class="content">
<p>Click Or <br>Drag & drop </p>
<input type="file" id="files" name="files"> 
</div>
</div>




</body>
</html>