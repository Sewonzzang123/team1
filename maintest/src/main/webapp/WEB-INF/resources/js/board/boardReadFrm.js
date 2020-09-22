'use strict'

const acticle = document.querySelector('.acticle');
const layer_attach = document.querySelector('.layer_attach');
//hidden 속성들
const bnum = document.getElementById("bnum").value;
const returnPage = document.getElementById("returnPage").value;
const searchType = document.getElementById("searchType").value;
const searchKeyword = document.getElementById("searchKeyword").value;   	   
const catnum = document.getElementById("catnum").value;      

console.log(bnum);
acticle.addEventListener('click', (e) => {		
console.log("article 구역 클릭 " + e.target.tagName);	
  // 목록 버튼
  if (e.target.classList.contains('article_list_btn')) {
	  listBtn_f(e)
  }

  // 삭제 버튼
  if (e.target.classList.contains('article_del_btn')) {        
if(confirm('게시글을 삭제하시겠습니까?')){
  		deleteBtn_f(); 		  	
      	}
  }
  
  // 답글 버튼
  if (e.target.classList.contains(' article_rep_btn')) {
	   peplyBtn_f();    	   
  }      
 
  // 수정 버튼
  if (e.target.classList.contains('article_mod_btn')) {
console.log('수정 요청')    
    modifyBtn_f();
  }

  // gotop 버튼
  if (e.target.classList.contains('article_gotop_btn')) {
    window.scrollTo(0, 250);
  }

  // 첨부파일 버튼
  if (e.target.classList.contains('button_file')) {
if (layer_attach.getAttribute('style') == 'display: none;') {
  layer_attach.style.display = null;
} else {
  layer_attach.style.display = 'none';
    }
  }
})

      
  <!-- 답글 -->      
  function peplyBtn_f(){               
  const url = `/pfpkg/board/boardReplyFrm/${bnum}/${returnPage}`;
  window.location.href=url;      
  }
      
        
//수정버튼
function modifyBtn_f(){
    const url = `/pfpkg/board/boardModifyFrm/${catnum}/${bnum}`;                     
        location.href = url;                    
 }          
     
     
//삭제버튼
function deleteBtn_f(e){
    if(confirm("삭제하시겠습니까?")){                     
const url = `/pfpkg/board/delete/${bnum}`;
        window.location.href= url;            
        }        
 }     
 
 //목록 버튼
   function   listBtn_f(e){        
    const url = `/pfpkg/board/${catnum}/${returnPage}/${searchType}/${searchKeyword}`;    
   window.location.href=url;     
     
     }   
     
    //게시판 카테고리별 말머리 불러오기      
 function getHidcate_f(e){     
 const bcateVal = bcateTag.value; 
 //요청 시작
 const xhttp = new XMLHttpRequest();
 xhttp.addEventListener("readystatechange", ajaxCallForHid);
  //요청 처리 완료되었을때 
 function ajaxCallForHid(e){     
    if(this.readyState == 4 && this.status == 200){
 //제이슨 --> 자바스크립트 객체로 변환 
  const   jsonToJavascript = JSON.parse(this.responseText);
     switch(jsonToJavascript.rtcode) {
     case "00":
const   hidcateTag = document.getElementById("hidcategory");
const hidcategory = jsonToJavascript.hidcategory;
hidcateTag.innerHTML="";
Array.from(hidcategory).forEach(e=>{
hidcateTag.innerHTML += "<option value='" + e.hidnum + "'>" + e.hidname + "</option>";
 		})
 		break;
 
 case "01":
 console.log("hid 카테 조회 실패");
 
 break;    
  } //switch
  
  }//if    
 }//ajaxcall()    응답 end
 
 //요청 파라미터 셋팅
 const reqPara = {} ; 
 reqPara.catnum = bcateVal;
 
 const javascriptToJson = JSON.stringify(reqPara);
 
 xhttp.open("POST", "http://localhost:9080/pfpkg/board/headid");
 xhttp.setRequestHeader("Content-Type","application/json;charset=utf-8");
     xhttp.send(javascriptToJson);
  }
     
     

