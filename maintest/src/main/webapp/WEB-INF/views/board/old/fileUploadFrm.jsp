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
position:relative;
magin: 0px auto;}



input[type="file"]{
display:none;
}

.dragover{
border:1px dotted blue;
background-color: #cccccc
}

img{
width:150px;
height: 200px;
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

addImgFiles();


function addImgFiles(){
dropZone.addEventListener("dragstart", function (e){
e.preventDefault();

	console.log("dragStart")
	console.log("fileTag.files[0]" + filesTag.files[0])

	e.dataTransferData("key",  filesTag.files[0]);	
})



dropZone.addEventListener("dragenter", function(e){
e.preventDefault();
console.log("dragenter")
dropZone.classList.add("dragover");	
})

dropZone.addEventListener("dragleave", function(e){
	e.preventDefault();
	console.log("dragleave")
	dropZone.classList.remove("dragover")	
})

dropZone.addEventListener("dragover", function(e){
	e.preventDefault();
	console.log("dragleave")
	
})


dropZone.addEventListener("drop", function(e){
e.preventDefault();
console.log("drop")
console.log("e.dataTransfer.files[0]" + e.dataTransfer.files[0])

const file = e.dataTransfer.files[0];

const url = URL.createObjectURL(e.dataTransfer.files[0]);

console.log("url" + url);
const newImg = document.createElement("img");
newImg.src = url;

dropZone.appendChild(newImg);
})








}//addImgFiles
}//init

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