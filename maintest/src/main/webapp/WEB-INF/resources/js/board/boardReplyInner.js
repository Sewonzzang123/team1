'use strict'
        
 const reqPage = document.getElementById("reqPage").value;
 const bnum = document.getElementById("bnum").value;
 const pucode = document.getElementById("ucode").value;

let  wrtTags = document.getElementById("filloutHere");
const rbtnGrpTag = document.getElementById("rbtnGrp")
const replaceableAreaTag =  document.getElementById("replaceableArea")

let bccontent;
let currEllipsis; // 현재 찍은 ellipse I tag
let currBcNum ; //현재 찍은 ellipse I tag 의 bcnum
// 전체 bcomment class 가진 태그에 이벤트 걸기 
wrtTags.addEventListener("click",becommentTags_f);

//이벤트 리스너 구현 파트 
function becommentTags_f(e){     
    // 클래스 네임이 조건에 맞을때 실행
	//----------------------------------------------------------------------------------------------------------------	
	console.log("클릭한 타켓 ====[" + e.target.tagName+"]");
	//----------------------------------------------------------------------------------------------------------------	
     //댓글 작성창 파트
     //활성화  타겟 : typing
    if(e.target.classList.contains("typing") && e.target.tagName == 'DIV'){
    	console.log("타이핑 구역 클릭")        
        bccontent = e.target;  //작성 내용을 전역 변수 설정
        bccontent.setAttribute("contenteditable", "true");
        bccontent.focus();        
        //등록 and 취소 버튼 보이게
        rbtnGrpTag.classList.remove("hidden");
        rbtnGrpTag.classList.add("shown");
        bccontent.classList.add("typable"); 
         }    
}//becommentTags_f


//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//----------------------------------------------------------------------------------------------------------------	
//부모&자식 댓글 파트
//ellipsis 버튼 클릭 이벤트        
    if(e.target.classList.contains("fa-ellipsis-v")  && e.target.tagName == 'I'){            
        // if(currEllipsis == undefined){
        //     console.log("이전 currEllipsis//     ==== "+ currEllipsis)                
        // }else{
        // console.log("이전 currEllipsis//     ==== "+ currEllipsis.tagName);}
        // console.log("currEllipsis == undefined//    ===="+ (currEllipsis == undefined));
       
        let nowClickedBcNum  =  e.target.closest(".bcomment").parentElement.getAttribute("data-bcnum");

        console.log("currBcNum//     ==== "+ currBcNum);
        console.log("nowClickedBcNum//     ==== "+ nowClickedBcNum);

        // 최초 클릭 currEllipsis  == undefined
        if(currEllipsis == undefined){
            
            //클릭타겟이 바뀌면 BCNum도 같이 바뀌어야함.
            currEllipsis = e.target; 
            currBcNum = currEllipsis.closest(".bcomment").parentElement.getAttribute("data-bcnum");               
            if(currBcNum != nowClickedBcNum){
                currEllipsis.nextElementSibling.classList.add("hidden");
                 //클릭타겟이 바뀌면 BCNum도 같이 바뀌어야함.    
                currEllipsis = e.target;
                currBcNum = currEllipsis.closest(".bcomment").parentElement.getAttribute("data-bcnum");
            }}
            //버튼 visible
           currEllipsis.nextElementSibling.classList.remove("hidden");    
           //마우스가 해당 댓글을 벗어날때 히든메뉴 사라짐       
          document.querySelectorAll(".bcomment").forEach(tag=>{
           tag.addEventListener("mouseenter", mouseenter_f)
          })        
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

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



//공통 메소드
//마우스가 해당 댓글을 벗어날때 
function mouseenter_f(e){        
        if(currEllipsis.closest(".bcomment").parentElement.getAttribute("data-bcnum")
        !=e.target.closest(".bcomment").parentElement.getAttribute("data-bcnum") ||
        e.target.closest(".bcomment").parentElement.getAttribute("data-bcnum") == null){        
            currEllipsis.nextElementSibling.classList.add("hidden")       
            return;

        }
    }


   
//수정 
//삭제 

//종하요 
//등록 취소 버튼 이벤트 추가(onclick method on button tag)
//입력창 등록 취소  버튼 
function rbtnGrpTag_f(e){ 	
	console.log("클릭햇나? "+ e.textContent)
  let clickedBtn = e.textContent;
  let i = 0 ;
  if(clickedBtn == '등록' && e.tagName == 'BUTTON'){    
      console.log("등록")
      register(bccontent); 
  }else if(clickedBtn == '취소' && e.tagName == 'BUTTON'){
      console.log("취소")       
      rbtnGrpTag.classList.add("hidden");
      rbtnGrpTag.classList.remove("shown");
      bccontent.textContent ="";
      bccontent.setAttribute("contenteditable", "false");
      bccontent.classList.remove("typable");
  }
} //rbtnGrpTag_f 

//댓글 등록 버튼 클릭시
function register(bccontent){
    const xhttp = new XMLHttpRequest();    
    xhttp.addEventListener("readystatechange", ajaxCall)
    
    function ajaxCall(e){
        //서버 응답
        if(e.target.readyState == 4 && e.target.status == 200){            	
        	const jsonObj = JSON.parse(e.target.responseText);            
          
            if(jsonObj.result ==  'OK'){
                console.log("댓글등록 성공 및 목록가져와서 나타내기")                    
                //값들어왔을때 화면처리 할 코드  
              replaceableAreaTag.innerHTML ='';
                Array.from(jsonObj.list).forEach(data=>{ 
                	
                	let str ='';
                	if(data.bcindent == 0){
                		//부모댓글일 경우
                		console.log("부모 댓글")
                		str += `<div class="parent" data-bcnum="${data.bcnum}">`                            
                    }else{
                		//자식댓글일 경우
                		console.log("자식 댓글")
                		str += `<div class="children" data-bcnum="${data.bcnum}">`                          
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
                })       
            }else{
                console.log("등록 실패")
            }
            }
    } //ajax
    
    
    //요청메시지
          const reqMsg = {};
          reqMsg.bnum = bnum;
          reqMsg.bccontent = bccontent.textContent;
           reqMsg.pucode = pucode;             
          const changeIntoJson = JSON.stringify(reqMsg);

          let reqPage ='' ;
      //요청 메소드 + 요청URL 
      const url =`http://localhost:9080/pfpkg/bcomment/replyP/${reqPage}`;
      xhttp.open('post',url)
      xhttp.setRequestHeader('Content-Type','application/json;charset=utf-8')
      xhttp.send(changeIntoJson);        
}//register
