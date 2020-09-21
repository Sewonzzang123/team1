'use strict'

<!--읽기모드 -->
        const peplyBtn = document.getElementById("peplyBtn");
// const modifyBtn = document.getElementById("modifyBtn");
        const listBtn = document.getElementById("listBtn");

        if( peplyBtn)  	peplyBtn.addEventListener("click", peplyBtn_f);  
// if( modifyBtn) modifyBtn.addEventListener("click", modifyBtn_f);
          if( listBtn)  listBtn.addEventListener("click", listBtn_f);     
      
      
      <!-- 답글 -->      
      function peplyBtn_f(){          
      const bnum = document.getElementById("bnum").value;
       const returnPage = document.getElementById("returnPage").value;
      const url = `/pfpkg/board/boardReplyFrm/${bnum}/${returnPage}`;
      window.location.href=url;      
      }
      
      
        <!--수정모드 -->
        const deleteBtn = document.getElementById("deleteBtn");
        const saveBtn = document.getElementById("saveBtn");
        const cancelBtn = document.getElementById("cancelBtn");
        const attachments = document.getElementById("attachments");
        // 게시판 카테고리별 말머리 불러오기
        const   bcateTag = document.getElementById("bcategory");
        
        
       if( deleteBtn) deleteBtn.addEventListener("click",deleteBtn_f);      
       if( saveBtn)    saveBtn.addEventListener("click", saveBtn_f);           
       if( cancelBtn) cancelBtn.addEventListener("click", cancelBtn_f);        
        // 첨부파일 일부 삭제
         if( attachments)  attachments.addEventListener("click", deleteFile_f);
       if(bcateTag) bcateTag.addEventListener("change", getHidcate_f);
        // 수정버튼
// function modifyBtn_f(){
// console.log("modifyBtn 버튼 클릭");
// const isModifyMode = true;
// // 클릭과 함께 readonly disabled 속성 값 변환
// modeChange(isModifyMode);
//                     
// }
     
     
// // 수정 / 읽기 모드 처리 로직
// function modeChange(isModifyMode){
// if(isModifyMode== true){
// // 읽기모드--> 수정모드
// const ritems = document.getElementsByClassName("readMode")
// Array.from(ritems).forEach((item)=>{item.style.display = "none"});
// const mitems = document.getElementsByClassName("modifyMode")
// Array.from(mitems).forEach((item)=>{item.style.display = "block"});
//     
// // selectTags disabled 해제
// const selectTags = document.getElementsByTagName("select")
// Array.from(selectTags).forEach((item)=>{item.removeAttribute("disabled")});
//     
// // btitle & bcontent readOnly 해제
// document.getElementById("btitle").removeAttribute("readonly");
// document.getElementById("bcontent").removeAttribute("readonly")
//     
// // bcategory 및 hidcategory 의 db에서 불러온 값 selected 속성 풀기
// document.getElementById("bcategory").children[0].removeAttribute("selected")
// document.getElementById("hidcategory").children[0].removeAttribute("selected")
//     
// }else{
// // 수정 --> 읽기모드
// const ritems = document.getElementsByClassName("readMode")
// Array.from(ritems).forEach((item)=>{item.style.display = "block"});
// const mitems = document.getElementsByClassName("modifyMode")
// Array.from(mitems).forEach((item)=>{item.style.display = "none"});
//     
//     
// // selectTags disabled 설정
// const selectTags = document.getElementsByTagName("select")
// Array.from(selectTags).forEach((item)=>{item.setAttribute("disabled",true)});
//     
// // btitle & bcontent readOnly 설정
// document.getElementById("btitle").setAttribute("readonly",true);
// document.getElementById("bcontent").setAttribute("readonly",true);
//
//
// // bcategory db에서 불러온 값 다시 보이기
// document.getElementById("bcategory").children[0].setAttribute("selected",
// true)
// document.getElementById("hidcategory").children[0].setAttribute("selected",true)
//
// // hidcateTag.innerHTML += "<option value='${hid.hidnum }'
// // style='display:none'>${hid.hidname }</option> "
//     
//     
//     
// }
// }
     
// // 삭제버튼
// function deleteBtn_f(e){
// e.preventDefault();
// console.log("deleteBtn 버튼 클릭");
// const isModifyMode = false;
// modeChange(isModifyMode);
// if(confirm("삭제하시겠습니까?")){
// const bnum = document.getElementById("bnum").value;
// const url = `/pfpkg/board/delete/${bnum}`;
// window.location.href= url;
// }
//            
// }
     
     // 목록 버튼
   function   listBtn_f(e){        
      e.preventDefault();
// const isModifyMode = false;
// modeChange(isModifyMode);
    const searchType = document.getElementById("searchType").value;
   	const searchKeyword = document.getElementById("searchKeyword").value;   	
    const returnPage = document.getElementById("returnPage").value;       
    const catnum = document.getElementById("catnum").value;       
    const url = `/pfpkg/board/${catnum}/${returnPage}/${searchType}/${searchKeyword}`;
    
   window.location.href=url;     
     
     }
   
     // 저장버튼
      function   saveBtn_f(e){        
//    
// if(confirm("저장하시겠습니까?")){
// const readModFrm = document.getElementById("readModFrm");
// readModFrm.submit();
// }
    }
     // 취소 버튼
   function   cancelBtn_f(e){        
// const isModifyMode = false;
// readModFrm.reset();
// modeChange(isModifyMode);
     }
     
     
     // 첨부 파일 삭제
     function deleteFile_f(e){
     console.log(e.target);
       if(e.target.tagName != "I") return false;
       const iTag = e.target;
       const fid= iTag.getAttribute("data-fid");
       
      const isthumb = iTag.getAttribute("data-isthumb");
             
     if(confirm("삭제하시겠습니까?")) {   
     // AJAX사용
     // XMLHttpRequest 객체 새성
     const xhttp = new XMLHttpRequest();
     
     // 서비스 요청
     const uri = `http://localhost:9080/pfpkg/board/deleteFile/${fid}/${isthumb}`;
     
     xhttp.open("get", uri)
     xhttp.send();
     
     // 응답처리
     // readystate
     // 0:open()이 호출되지 않은상태
     // 1:open()이 실행된 상태 -> 서버에 연결됨.
     // 2:send()가 실행된 상태, 서버가 클라이언트 요청을 받았음.
     // 3:서버가 클라이언트 요청을 처리중 / 응답헤더는 수신했으나 바디가 수신중인 상태
     // 4:서버가 클라이언트의 요청을 완료 햇고 , 서버도 응답이 완료된 상태
     xhttp.addEventListener("readystatechange", ajaxCall);
     
     function ajaxCall(e){
     if(this.readyState == 4 && this.status == 200){
     // 서버에서 삭제를 처리햇으면
     if(this.responseText == "success"){
     // 성공했을때 첨부파일 표시 하던 tag 삭제 처리
     const parent = iTag.parentElement.parentElement;
     parent.remove(iTag); 		
     }else{     
     console.log("파일삭제 실패!") 
          }
 			    }     
   			  }
          }
 }
     
      // 게시판 카테고리별 말머리 불러오기
     
     function getHidcate_f(e){
     
     const bcateVal = bcateTag.value;
     

  
     // 요청 시작
     const hidnumV = document.querySelector('#hidnum').value;
     const xhttp = new XMLHttpRequest();
     
     xhttp.addEventListener("readystatechange", ajaxCallForHid);  
     
          // 요청 처리 완료되었을때
     function ajaxCallForHid(e){
        if(this.readyState == 4 && this.status == 200){
     
     // 제이슨 --> 자바스크립트 객체로 변환
  const   jsonToJavascript = JSON.parse(this.responseText);
     
     switch(jsonToJavascript.rtcode){
     case "00":
        const   hidcateTag = document.getElementById("hidcategory");
     
     		const hidcategory = jsonToJavascript.hidcategory;
     		hidcateTag.innerHTML="";
     		Array.from(hidcategory).forEach(e=>{
     			if(hidnumV ==  e.hidnum){
     				hidcateTag.innerHTML += "<option value='" + e.hidnum + "' selected>" + e.hidname + "</option>";
     			} else{
     		hidcateTag.innerHTML += "<option value='" + e.hidnum + "'>" + e.hidname + "</option>";}
     		})
     	 	
     		break;
     
     case "01":
     console.log("hid 카테 조회 실패");
     
     break;    
        }
        }
     }

     // 요청 파라미터 셋팅
     const reqPara = {} ; 
     reqPara.catnum = bcateVal;
     
     const javascriptToJson = JSON.stringify(reqPara);
     
     xhttp.open("POST", "http://localhost:9080/pfpkg/board/headid");
     xhttp.setRequestHeader("Content-Type","application/json;charset=utf-8");
     xhttp.send(javascriptToJson);
        
     }
     
     const acticle = document.querySelector('.section_wrap');
     const layer_attach = document.querySelector('.layer_attach');
     const bnum = document.querySelector('#bnum').value;
     console.log(bnum);

     acticle.addEventListener('click', (e) => {
       // 목록 버튼
       if (e.target.classList.contains('article_list_btn')) {	
// listBtn_f(e)
    	   history.back();
       }

       // 삭제 버튼
       if (e.target.classList.contains('article_mod_btn')) {        
       	if(confirm('게시글을 삭제하시겠습니까?')){
       		location.href="${pageContext.request.contextPath}/board/delete/"+bnum;
           	}
       }

       // 수정 버튼
       if (e.target.classList.contains('article_del_btn')) {
         console.log('수정 요청')
         location.href = '${pageContext.request.contextPath}/board/modifyFrm/'+bnum;
       }

       // gotop 버튼
       if (e.target.classList.contains('article_gotop_btn')) {
         window.scrollTo(0, 0);
       }

       // 첨부파일 버튼
       if (e.target.classList.contains('button_file')) {
         e.preventDefault();
         if (layer_attach.getAttribute('style') == 'display: none;') {
           layer_attach.style.display = null;
         } else {
           layer_attach.style.display = 'none';
         }
       }  
       });
     
     getHidcate_f();
 