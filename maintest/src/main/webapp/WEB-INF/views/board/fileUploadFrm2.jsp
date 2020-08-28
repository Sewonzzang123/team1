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



<script defer>

window.addEventListener("load", init);

function init(){


//케이스 2 

document.getElementById("gdsimg").addEventListener("change",function(){

    console.log("1"+ this.files[0])
    console.log( "2" + this.files.length)

    if(this.files && this.files[0]){
        let i = 0;
            Array.from(this.files).forEach((e) => {

                i++;
                console.log(i)
        
        let reader = new FileReader;

        reader.addEventListener("load", (data)=>{

            console.log(data.target.result)                
                let parent = document.querySelector(".select_img");
                let imgTag =   document.querySelector(".select_img img");
            imgTag.setAttribute("src", data.target.result)
            imgTag.style.width = "500px";
                parent.appendChild(imgTag);

            })
          
        reader.readAsDataURL(this.files[0]);
            }
        )
    }



})


}//init

</script>
</head>
<body>

<h2> 케이스 2]</h2>
    <div class="inputArea">
        <label for="gdsmimg">이미지</label><input type="file" name="file" id="gdsimg" multiple>
        <div class="select_img"><img src="" alt="" ></div>
    </div>



<%=request.getRealPath("/") %>






</body>
</html>