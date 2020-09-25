'use strict'


        const writeBtn = document.getElementById("writeBtn");
        const writeFrm = document.getElementById("writeFrm");
        const listBtn = document.getElementById("listBtn");
   
        const filesBoxTag = document.getElementById("filesBox");      
        const filesTag = document.getElementById("files");
        		
			  const bCateTag = document.getElementById("bcategory")
				const hidCateTag = document.getElementById("hidcategory")
				        //게시판 분류 카테고리 별 말머리 연동 ajax
    		bCateTag.addEventListener("change", getHeadid_f);
        
        writeBtn.addEventListener("click", writeBtn_f);
        listBtn.addEventListener("click", listBtn_f);       
        filesBoxTag.addEventListener("click", filesBoxTag_f);
  
        
        //게시글 등록버튼
        function writeBtn_f(e){ 
        e.preventDefault();
            console.log("글등록 버튼 클릭");
            writeFrm.submit();
    		 }
    		 
    		 
    		 //목록으로 버튼     
    	function   listBtn_f(e){
    const returnPage = document.getElementById("returnPage").value;
    const url = `/pfpkg/board/${returnPage}`;
    window.location.href=url;     
     		}
     		
          		
     		//첨부파일 버튼 클릭   
     		function filesBoxTag_f(e){

     		let clickedTag  = e.target;    
     		console.log( "clickedTag" +clickedTag.tagName);   		
     		
     		
     		
     		
   			    filesTag.addEventListener("change", (e) => {

                console.log(e.target.files);
                const imgContentTag = document.querySelector(".img_content");
                imgContentTag.innerHTML = "";
                Array.from(e.target.files).forEach(function (file) {

                    // if(file.type.match("")){
                    //     alert("이미지 파일만 첨부 가능합니다.");
                    //     return;
                    // }

                    const newDiv1 = document.createElement("div");
                    const newDiv2 = document.createElement("div");
                    const newDiv3 = document.createElement("div");
                    const newImg = document.createElement("img");
                    const url = URL.createObjectURL(file);
                    newDiv1.setAttribute("class", "thumbnail_wrapper");
                    newDiv2.setAttribute("class", "thumbnail");
                    newDiv3.setAttribute("class", "centered");
                    newImg.src = url;
                    newDiv3.appendChild(newImg);
                    newDiv2.appendChild(newDiv3);
                    newDiv1.appendChild(newDiv2);
                    imgContentTag.appendChild(newDiv1);


                })
            })

  
    
    }
    	   
    	   
     		
     		
     		
//보드 카테고리에 맞는 말머리 불러오기 
//ajax
function	getHeadid_f(e){   	 	
  const bCateTagVal = bCateTag.value;
  
  //ajax 
  const xhttp = new XMLHttpRequest();
  
  xhttp.addEventListener("readystatechange", ajaxCall);
  
  
  function ajaxCall(e){
  //처리완료 시 응답처리 
  
  if(this.readyState == 4 && this.status == 200){
  
   const responseObj = this.responseText;
   const jsonToJsObj = JSON.parse(responseObj);
   switch(jsonToJsObj.rtcode){
   case "00": 
   const hidcategory = jsonToJsObj.hidcategory;
   hidCateTag.innerHTML = "";
   Array.from(hidcategory).forEach(e=>{
   hidCateTag.innerHTML += "<option value='" +e.hidnum + "' >" +e.hidname + "</option>"
   })   
   break;
   
   case"01" :
  console.log("말머리 불러오기 실패" )
   break;   
   }
  }
  }
  
  const jsObj = {};
  jsObj.catnum = bCateTagVal;
  const jsObjToJson = JSON.stringify(jsObj);
  xhttp.open("POST", "http://localhost:9080/pfpkg/board/headid");
  xhttp.setRequestHeader("Content-Type", "application/json;charset=utf-8");
  xhttp.send(jsObjToJson);     	
     	
     	}
     	