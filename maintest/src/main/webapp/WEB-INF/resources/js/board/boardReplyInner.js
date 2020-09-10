'use strict'
        
const reqPage = document.getElementById("reqPage").value;

let  becommentTags = document.querySelectorAll(".bcomment");
const rbtnGrpTag = document.querySelector(".rbtnGrp")
const bnum = document.getElementById("bnum").value;
const pucode = document.getElementById("ucode").value;
const replaceableAreaTag =  document.getElementById("replaceableArea")

let bccontent;


becommentTags.forEach(e=>{   
    e.addEventListener("click",becommentTags_f)
})

function becommentTags_f(e){     
    // 클래스 네임이 조건에 맞을때 실행
	//----------------------------------------------------------------------------------------------------------------	
	console.log("클릭한 타켓 ====" + e.target.tagName);
     // 입력창 활성화  타겟 : typing
    if(e.target.classList.contains("typing")){
        bccontent = e.target;  
        console.log("타이핑 구역 클릭")        
        bccontent.setAttribute("contenteditable", "true");
        bccontent.focus();
        //버튼 visible
        rbtnGrpTag.classList.remove("hidden");
        bccontent.classList.add("typable"); 
        //등록취소버튼 이벤트 추가
        rbtnGrpTag.addEventListener("click", rbtnGrpTag_f)
         }    
    
    	//ellipsis 버튼 클릭 이벤트 
    	if(e.target.id == "ellipsis"){   
    		
    		console.log("ellipsis 타켓 ")
    		   //버튼 visible
            e.target.nextElementSibling.classList.remove("hidden");    		
    	}
    	
    	//수정 삭제 버튼 이벤트
    	
    	if(e.target.textContent == '수정'){
    		console.log("수정 버튼 클릭됨")
    		
    		
    	}
    	
    	
    
    

//입력 등록 취소  버튼 
function rbtnGrpTag_f(e){
    console.log(e.target.textContent)
    let clickedBtn = e.target.textContent;
    let i = 0 ;
    if(clickedBtn == '등록'){
        console.log( " 불러온 데이터 개수 == " + i++);
        console.log("등록")
        register(bccontent);        
    }else if(clickedBtn == '취소'){
        console.log("취소")       
        rbtnGrpTag.classList.add("hidden");
        bccontent.textContent ="";
        bccontent.setAttribute("contenteditable", "false");
        bccontent.classList.remove("typable");
    }

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
                  
                    Array.from(jsonObj.list).forEach(data=>{ 
                    	let str ='';
                    	if(data.bcindent == 0){
                    		//부모댓글일 경우
                    		console.log("부모 댓글")
                    		str += `<div class="bcomment">`
                    		    str += `<div class="profile"><img src="#" alt=""></div>`
                    		    str += `<div class="userinfo">`
                    		        str += `<div>`
                    		            str += `<span class="IRnickname">${data.bnum}</span>`
                    		            str += `<span class="goodOrBad"><a href=""><i class="far fa-thumbs-up"></i></a></span>`
                    		            str += `<span class="goodOrBad"><a href=""><i class="far fa-thumbs-down"></i></a></span>`
                    		        str += `</div>`
                    		        str += `<div class="udate">${data.udate}</div>`
                    		        str += `<div class="innerRe_area">`
                    		            str += `<span class="IRnickname">${data.pnickname}</span>`
                    		            str += `<div class="typed" contenteditable="true">`
                    		                str += `${data.bccontent}`               
                    		            str += `</div>`
                    		        str += `</div>`
                    		    str += `</div>`
                    		    str += `<div class="ellipsis"><a href=""><i class="fas fa-ellipsis-v"></i></a>`
                    		str += ``
                    		        str += `<!-- 수정/삭제 히든메뉴 -->`
                    		        str += `<div class="ellipsis hiddenMenu">`
                    		            str += `<div>`
                    		                str += `<a href="">수정</a>`
                    		                str += `<a href="">삭제</a>`
                    		            str += `</div>`
                    		        str += `</div>`
                    		    str += `</div>`
                    		str += `</div>`
                    	}else{
                    		//자식댓글일 경우
                    		console.log("자식 댓글")
                    		 str += `<div class="children">`
                    			    str += `<div class="bcomment">`
                    			        str += `<div class="profile"><img src="#" alt=""></div>`
                    			        str += `<div class="userinfo">`
                    			            str += `<div>`
                    			                str += `<span class="IRnickname">${data.bnum}</span>`
                    			                str += `<span class="goodOrBad"><a href=""><i class="far fa-thumbs-up"></i></a></span>`
                    			                str += `<span class="goodOrBad"><a href=""><i class="far fa-thumbs-down"></i></a></span>`
                    			            str += `</div>`
                    			            str += `<div class="udate">${data.udate}</div>`
                    			            str += `<div class="innerRe_area">`
                    			                str += `<span class="IRnickname">${data.pnickname}</span>`
                    			                str += `<div class="typed" contenteditable="true">`
                    			                    str += `${data.bccontent}`               
                    			                str += `</div>`
                    			            str += `</div>`
                    			        str += `</div>`
                    			        str += `<div class="ellipsis"><a href=""><i class="fas fa-ellipsis-v"></i></a>`
                    			    str += ``
                    			            str += `<!-- 수정/삭제 히든메뉴 -->`
                    			            str += `<div class="ellipsis hiddenMenu">`
                    			                str += `<div>`
                    			                    str += `<a href="">수정</a>`
                    			                    str += `<a href="">삭제</a>`
                    			                str += `</div>`
                    			            str += `</div>`
                    			        str += `</div>`
                    			    str += `</div>`
                    			    str += `</div>`
                    			
                    	}   
                    	replaceableAreaTag.innerHTML +=  str;    
                    	becommentTags = document.querySelectorAll(".bcomment");
                    	becommentTags.forEach(e=>{   
                    	    e.addEventListener("click",becommentTags_f)
                    	})
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
} //rbtnGrpTag_f 
}//becommentTags_f



//댓글 수정 


//댓글 삭제


