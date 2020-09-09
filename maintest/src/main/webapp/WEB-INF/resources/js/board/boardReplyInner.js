'use strict'
        


const becommentTags = document.querySelectorAll(".bcomment");
const rbtnGrpTag = document.querySelector(".rbtnGrp")

const bnum = document.getElementById("bnum").value;

const pucode = document.getElementById("ucode").value;

let bccontent;


becommentTags.forEach(e=>{
   
    e.addEventListener("click",becommentTags_f)
})



function becommentTags_f(e){  
   
    // 클래스 네임이 조건에 맞을때 실행
     // 입력창 활성화  
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

//입력 등록 취소  버튼 
function rbtnGrpTag_f(e){
    console.log(e.target.textContent)
    let clickedBtn = e.target.textContent;

    if(clickedBtn == '등록'){
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
          //요청메시지
                const reqMsg = {};
                reqMsg.bnum = bnum;
                reqMsg.bccontent = bccontent.textContent;
                 reqMsg.pucode = pucode
                
                
                console.log("ajax요청 메시지 " + reqMsg)
                const changeIntoJson = JSON.stringify(reqMsg);

                let reqPage ='' ;
            //요청 메소드 + 요청URL 
            const url =`http://localhost:9080/pfpkg/bcomment/replyInner/${bnum}`;
            xhttp.open('post',url)
            xhttp.setRequestHeader('Content-Type','application/json;charset=utf-8')
            xhttp.send(changeIntoJson);



        
        function ajaxCall(e){
            //서버 응답
            if(e.target.readyState == 4 && e.target.status == 200){
                if(e.target.responseText == 'success'){
                    console.log("댓글등록 성공 및 목록가져와서 나타내기")
                    const jsonObj = JSON.parse(e.target.responseText);
                    //값들어왔을때 처리 할 코드 
                }else{
                    console.log("등록 실패")
                }
                }

        }
    
    }//register



} //rbtnGrpTag_f 





    




}//becommentTags_f