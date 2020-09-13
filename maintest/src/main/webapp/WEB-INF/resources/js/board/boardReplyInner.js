


	'use strict'
 //controller 상호 전달 인수s
 const reqPage = document.getElementById("reqPage").value;
 const bnum = document.getElementById("bnum").value;
 const pucode = document.getElementById("ucode").value;

//작성창 
const wrtTag = document.getElementById("filloutHere");
const replaceableAreaTag = document.getElementById("replaceableArea")
const innerRe_wrapperTag =  document.querySelector(".innerRe_wrapper")


//댓글 리스트 범위에 이벤트 걸기
innerRe_wrapperTag.addEventListener("click",bcommentTags_f)



//

//마우스 해당구역 벗어날때 메뉴 히든 처리 
// 현재 이벤트가 일어나는 구역 
let clickedTag;// 이전에 클릭한 tag
let clickedBcnum //이전에 클릭한 태그가 속한 댓글의 bcnum
let nowClickedBcNum // 지금 클릭한 태그가 속한 댓글의 bcnum

let tagNowOn; //현재 마우스 포인트가 올라가있는 태그 
let bcNumNowOn; // 현재 마우스 포인트가 올라가있는 태그가 속한 댓글의 bcnum

innerRe_wrapperTag.addEventListener("mouseover",function(e){
//부모 태그가 없을때 익셉션 처리
if( e.target.className =='innerRe_wrapper'){
console.log("부모요소없음");return;}
//작성창 bcnum 리딩 익셉션처리
if( e.target.closest(".bcomment") == null) {
 console.log("작성창");  
 bcNumNowOn = 0;
 return;} 
tagNowOn = e.target.className;
bcNumNowOn =e.target.closest(".bcomment").parentElement.getAttribute("data-bcnum");
hideNowOpenedMenu_f(e);
})

//wrapper 전역 클릭 이벤트 감지 
function bcommentTags_f(e){
console.log(" 댓글 목록 안에서 클릭한 타켓 ====[" + e.target.tagName+"]");
    //작성창 이벤트 
    //등록 취소 버튼 이벤트 
    wrtTag_f(e);

	//ellipsis 버튼 클릭
	//수정 삭제 이벤트   
    ellipsisBtn_f(e);	    

    //답글쓰기 버튼 클릭
    //작성창 추가
    replyWriting_f(e);
    
    //등록 취소 이벤트 

	
}



//이벤트 리스너 구현 파트
//----------------------------------------------------------------------------------------------------------------	 
function wrtTag_f(e){     
	console.log("작성창 안에서 클릭한 타켓 ====[" + e.target.tagName+"]");
     //댓글 작성창 파트
     //활성화  타겟 : typing    
    if(e.target.classList.contains("typing") && e.target.tagName == 'DIV' && e.target.dataset["placeholder"].indexOf("로그인") == 0){
    	alert("로그인이 필요한 서비스 입니다.로그인 하시겠습니까?")    
    }else if(e.target.classList.contains("typing") && e.target.tagName == 'DIV'){
    	
     	console.log("타이핑 구역 클릭")  
        
       
        e.target.setAttribute("contenteditable", "true");
        e.target.focus();        
        //등록 and 취소 버튼 보이게
        e.target.nextElementSibling.classList.remove("hidden");
         e.target.nextElementSibling.classList.add("shown");
        e.target.classList.add("typable"); 
         }    
}//wrtTag_f







//부모&자식 댓글 파트
//ellipsis 버튼 클릭 이벤트 	
function ellipsisBtn_f(e){       
    if(e.target.classList.contains("fa-ellipsis-v")  && e.target.tagName == 'I'){           
        //새로이 찍은 태그가 속한 댓글의 bcnum
     	nowClickedBcNum  =  e.target.closest(".bcomment").parentElement.getAttribute("data-bcnum");
        // 최초 클릭 clickedTag  == undefined
        if(clickedTag == undefined){            
            //클릭타겟이 바뀌면 BCNum도 같이 바뀌어야함.
            clickedTag = e.target; 
            clickedBcnum = clickedTag.closest(".bcomment").parentElement.getAttribute("data-bcnum");  
        //다른 ellipsis 버튼을 클랙했을때 
        } else if(clickedBcNum != nowClickedBcNum){
                clickedTag.nextElementSibling.classList.add("hidden");
                clickedTag = e.target;
                clickedBcNum = nowClickedBcNum;
         }
            //버튼 visible
           clickedTag.nextElementSibling.classList.remove("hidden");    
                    
  }
     
     //수정 삭제 버튼 이벤트    	
     switch(e.target.textContent){
         case "수정": 
         if(e.target.tagName == 'BUTTON'){
             console.log("수정 버튼 클릭됨")
         }
         break;
         case "삭제":
             if(e.target.tagName == 'BUTTON'){
                 console.log("삭제 버튼 클릭됨")  
             }
         break;           
     }

}

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



//마우스가 해당 댓글을 벗어날때 ellipsis 히든 버튼 감추기
function hideNowOpenedMenu_f(e){ 
      console.log("hideNowOpenedMenu_f 마우스 오버 == " + e.target.tagName);
       if(clickedTag == undefined ) return;
        if(clickedTag.closest(".bcomment").parentElement.getAttribute("data-bcnum")
        !=e.target.closest(".bcomment").parentElement.getAttribute("data-bcnum") ||
        e.target.closest(".bcomment").parentElement.getAttribute("data-bcnum") == undefined){        
           
           
           if(clickedTag.tagName == 'i'){
            clickedTag.nextElementSibling.classList.add("hidden")   
            }
            
            if(clickedTag.classList.contains("typing")){            
            clickedTag.nextElementSibling.classList.add("hidden")
            clickedTag.nextElementSibling.classList.remove("shown")  
            return;}
        }
    }



//답글쓰기 버튼 

function replyWriting_f(e){
 if(e.target.classList.contains("reReply")  && e.target.tagName == 'BUTTON'){   
if(e.target.closest(".bcomment").parentElement.childElementCount  < 2){
 	addWindow(e);
}
}
}

function addWindow(e){

let nickname = document.getElementById("IRnickname").textContent

let str = ''; 

str += `<div class="filloutHere" id="filloutHere" >`;
str += `<div class="fillout">`;
str += `<div class="profile"><img src="../img/1.png" alt=""></div>`;
str += `<div class="userinfo">`;
str += `<div><span class="IRnickname">${nickname }</span></div>`;
str += `<div class="typing" contenteditable="false" data-placeholder="댓글 입력..."></div>`
str += `<div class=" rbtnGrp hidden" id="rbtnGrp"> `
str += `<button class="btn" id="rwriteBtn" onClick="rbtnGrpTag_f(this)">등록</button>`
str += `<button class="btn" id="rcancelBtn" onClick="rbtnGrpTag_f(this)">취소</button>`
str += `</div>`
str += `</div>`
str += `</div>`
str += `</div>`
e.target.closest(".bcomment").parentElement.innerHTML += str;

}


   
//수정 
//삭제 

//종하요 
//등록 취소 버튼 이벤트 추가(onclick= method on button tag)
//입력창 등록 취소  버튼 
function rbtnGrpTag_f(e){  
	console.log("클릭햇나? "+ e.textContent)
  if(e.textContent == '등록' && e.tagName == 'BUTTON'){    
     console.log("등록")
     const reqMsg = {};
     
  		let bcGrp;
     //부모 댓글의 자식 댓글
       if(e.closest(".filloutHere").parentElement.className == 'parent'){ 
       //자식댓글인 경우 추가로 부모댓글의 bcnum을 grp번호로 가진다.    
        bcGrp = e.closest(".filloutHere").parentElement.getAttribute("data-bcnum"); 
        }
        
        
      //자식 댓글의 댓글
      if(e.closest(".filloutHere").parentElement.className == 'child'){ 
       //자식댓글인 경우 추가로 부모댓글의 bcnum을 grp번호로 가진다.    
        bcGrp = e.closest(".filloutHere").parentElement.getAttribute("data-bcgrp"); ; 
        }
      
     
     //부모 댓글  -- 작성창 상위에 부모자식태그 없음.    
      let bccontent = e.parentElement.previousElementSibling.textContent;     
            
          reqMsg.bcGrp = bcGrp;  
          reqMsg.bnum = bnum;
          reqMsg.pucode = pucode;     
          reqMsg.bccontent = bccontent;           
      register(reqMsg); 
      
      
      
  }else if(e.textContent == '취소' && e.tagName == 'BUTTON'){
      console.log("취소")    
  }
  
  	  e.parentElement.classList.add("hidden");
      e.parentElement.classList.remove("shown");
       e.parentElement.previousElementSibling.textContent ="";
      e.parentElement.previousElementSibling.setAttribute("contenteditable", "false");
      e.parentElement.previousElementSibling.classList.remove("typable");
  
  
  
  
  
  
} //rbtnGrpTag_f 



//댓글 등록 버튼 클릭시
function register(reqMsg){   
const xhttp = new XMLHttpRequest();    
xhttp.addEventListener("readystatechange", ajaxCall);  
 //ajax 요청메시지 작성
 

 

 
   
  //요청메시지   --> json                   
       const changeIntoJson = JSON.stringify(reqMsg);      
    
      //요청 메소드 + 요청URL 
      const url =`http://localhost:9080/pfpkg/bcomment/replyP/${reqPage}`;
      xhttp.open('post',url)
      xhttp.setRequestHeader('Content-Type','application/json;charset=utf-8')
      xhttp.send(changeIntoJson);          
        
}//register



//자식댓글
//답글쓰기 버튼 클릭시 작성창 표시

//등록 처리



//댓글 리스트 호출
function callList(){
const xhttp = new XMLHttpRequest();    
xhttp.addEventListener("readystatechange", ajaxCall); 
// ajax 요청메시지 
const reqMsg = {};
reqMsg.bnum = bnum;

const url = `http://localhost:9080/pfpkg/bcomment/${bnum}/${reqPage+1}`;
xhttp.open('GET', url);
xhttp.setReqeustheader('Content-type', 'application/json;charset=utf-8');
xhttp.send();

}


//ajax 응답 
    function ajaxCall(e){
        //서버 응답
        if(e.target.readyState == 4 && e.target.status == 200){            	
        	const jsonObj = JSON.parse(e.target.responseText);            
          
            if(jsonObj.result ==  'OK'){
                console.log("댓글등록 성공 및 목록가져와서 나타내기") 
                showUpList(jsonObj.list);
            }else{
                console.log("등록 실패")
            }
            }
    } //ajax





//댓글 리스트 표현
function showUpList(list){
 //값들어왔을때 화면처리 할 코드  
              replaceableAreaTag.innerHTML ='';              
             Array.from(list).forEach(data=>{                 	
                	let str ='';
                	if(data.bcindent == 0){
                		//부모댓글일 경우
                		console.log("부모 댓글")
                		str += `<div class="parent" data-bcnum="${data.bcnum}">`                            
                    }else{
                		//자식댓글일 경우
                		console.log("자식 댓글")
                		str += `<div class="children" data-bcnum="${data.bcnum}" data-bcgrp="${data.bcgrp}">`                          
                    }
                	
                		str += `<div class="bcomment">`
                		    str += `<div class="profile"><img src="#" alt=""></div>`
                		    str += `<div class="userinfo">`
                		        str += `<div>`
                		        	 str += `<span class="IRnickname">${data.nickname}</span>`
                		            str += `<span class="goodOrBad"><a href=""><i class="far fa-thumbs-up"></i></a></span>`
                		            str += `<span class="goodOrBad"><a href=""><i class="far fa-thumbs-down"></i></a></span>`
                		        str += `</div>`
                		        str += `<div class="udate"><span>${data.udate}</span><button class="btn reReply"  >답글쓰기</button></div>`
                		        str += `<div class="innerRe_area">`
                		            str += `<span class="IRnickname">${data.pnickname}</span>`
                		            	 str += `<div class="typed" contenteditable="false">${data.bccontent}</div>`                    		      
                		        str += `</div>`
                		    str += `</div>`
                		     str += ` <div class="ellipsis"><i class="fas fa-ellipsis-v" id="ellipsis" ></i>`       
                		        str += `<!-- 수정/삭제 히든메뉴 -->`
                		        	str += ` <div class="ellipsis  hiddenMenu hidden" >`
                		            str += `<div>`
                		                str += `       <button type="button">수정</button>`
                		                str += `<button type="button">삭제</button>`
                		            str += `</div>`
                		        str += `</div>`
                		    str += `</div>`
                		str += `</div>`                   	
                	str += `</div>`                   	
                	replaceableAreaTag.innerHTML +=  str;  
                	
                	str = '';         
                })  
} //showUpList