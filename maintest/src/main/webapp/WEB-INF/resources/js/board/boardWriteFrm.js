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
        files.addEventListener("change", showImg);
        
        //게시글 등록버튼
        function writeBtn_f(e){ 
        e.preventDefault();
            console.log("글등록 버튼 클릭");
        writeFrm.submit();
    		 }
    		 
    		 
    		 //목록으로 버튼     
    	function   listBtn_f(e){
    const returnPage = document.getElementById("returnPage").value;
    const url = `/pfpkg/board/boardListFrm/${returnPage}`;
    window.location.href=url;     
     		}
     		
          		
     		//첨부파일 버튼 클릭   
     		function filesBoxTag_f(e){

     		let clickedTag  = e.target;    
     		console.log( "clickedTag" +clickedTag.tagName);   		
   			
   			 //버튼 클릭시 해당 버튼 삭제 및 첨부 영역 확장 + 문구 표시 
     		if(clickedTag.tagName == 'BUTTON') {        
     	  e.preventDefault();
     		filesTag.click();		   		
     		clickedTag.parentElement.removeChild(clickedTag);
				filesBoxTag.classList.add("nowAddF");
				const nowAddFTag = document.querySelector(".nowAddF");
				nowAddFTag.parentElement.style.display ="block";  
				 //nowAddFTag.children[0].style.display = "float";
				}else{				
				filesTag.click();		 
				}  	
    		}
    		 //filesTag.parentElement.innerHTML = "";
    function	showImg(){
    
    Array.from(filesTag.files).forEach(file=>{   
    const url = URL.createObjectURL(file);
    const imgTag = document.createElement("img");
    imgTag.src=url;
    filesTag.parentElement.appendChild(imgTag);
    
    })
    const url = URL.createObjectURL(filesTag.files[0]);
    console.log(url)
  
    
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
     		 
     	
     		