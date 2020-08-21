'use strict'

<!--읽기모드 -->
        const peplyBtn = document.getElementById("peplyBtn");
         const modifyBtn = document.getElementById("modifyBtn");
        const listBtn = document.getElementById("listBtn");
     
        if( peplyBtn)  	peplyBtn.addEventListener("click", peplyBtn_f);  
           if( modifyBtn) modifyBtn.addEventListener("click", modifyBtn_f);                 
          if( listBtn)  listBtn.addEventListener("click", listBtn_f);     
      
      
      <!-- 답글 -->      
      function peplyBtn_f(){      
      console.log("답글 버튼 클릭");
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
        
        
        
       if( deleteBtn) deleteBtn.addEventListener("click",deleteBtn_f);      
       if( saveBtn)    saveBtn.addEventListener("click", saveBtn_f);           
       if( cancelBtn) cancelBtn.addEventListener("click", cancelBtn_f);        
        //첨부파일 일부 삭제
         if( attachments)  attachments.addEventListener("click", deleteFile_f);
        
        
        
        //수정버튼
        function modifyBtn_f(){
            console.log("modifyBtn 버튼 클릭");
               const isModifyMode = true; 
            // 클릭과 함께 readonly disabled 속성 값 변환
            modeChange(isModifyMode);     
                     
     }     
     
     
     //수정 / 읽기 모드 처리 로직
     function modeChange(isModifyMode){          
     if(isModifyMode== true){
     //읽기모드--> 수정모드 
     const ritems = document.getElementsByClassName("readMode")
     Array.from(ritems).forEach((item)=>{item.style.display = "none"});     
      const mitems = document.getElementsByClassName("modifyMode")
     Array.from(mitems).forEach((item)=>{item.style.display = "inline-block"});
     
     // selectTags disabled 해제
     const selectTags = document.getElementsByTagName("select")
     Array.from(selectTags).forEach((item)=>{item.removeAttribute("disabled")});
     
     //btitle & bcontent readOnly 해제
   document.getElementById("btitle").removeAttribute("readonly");
   document.getElementById("bcontent").removeAttribute("readonly")
     
     //bcategory 및 hidcategory 의 db에서 불러온 값 selected 속성 풀기    
        document.getElementById("bcategory.catnum").children[0].removeAttribute("selected")
     document.getElementById("hidcategory.hidnum").children[0].removeAttribute("selected")
     
     }else{     	
       // 읽기모드 --> 수정모드
       const ritems = document.getElementsByClassName("readMode")
     Array.from(ritems).forEach((item)=>{item.style.display = "inline-block"});  
       const mitems = document.getElementsByClassName("modifyMode")
     Array.from(mitems).forEach((item)=>{item.style.display = "none"});
     
     
     // selectTags disabled 설정
     const selectTags = document.getElementsByTagName("select")
     Array.from(selectTags).forEach((item)=>{item.setAttribute("disabled",true)});
     
     //btitle & bcontent readOnly 설정
   document.getElementById("btitle").setAttribute("readonly",true);
document.getElementById("bcontent").setAttribute("readonly",true);


       //bcategory db에서 불러온 값 다시 보이기
     document.getElementById("bcategory.catnum").children[0].setAttribute("selected", true)
     document.getElementById("hidcategory.hidnum").children[0].setAttribute("selected",true)
     }
     }
     
     //삭제버튼
        function deleteBtn_f(e){
        e.preventDefault();        
            console.log("deleteBtn 버튼 클릭");               
            const isModifyMode = false; 
       			modeChange(isModifyMode);     
            if(confirm("삭제하시겠습니까?")){            
            const bnum = document.getElementById("bnum").value;
            const url = `/pfpkg/board/delete/${bnum}`;
            window.location.href= url;            
            }
            
     }     
     
     //목록 버튼
   function   listBtn_f(e){        
      e.preventDefault();
      const isModifyMode = false; 
       modeChange(isModifyMode);       
    const searchType = document.getElementById("searchType").value;
   	const searchKeyword = document.getElementById("searchKeyword").value;   	
    const returnPage = document.getElementById("returnPage").value;       
    const url = `/pfpkg/board/boardListFrm/${returnPage}/${searchType}/${searchKeyword}`;
    
   window.location.href=url;     
     
     }
     
     
     //저장버튼     
      function   saveBtn_f(e){        
    
    if(confirm("저장하시겠습니까?")){    
           const readModFrm = document.getElementById("readModFrm");
            readModFrm.submit();                
    }  
    }
     //취소 버튼
   function   cancelBtn_f(e){        
      const isModifyMode = false;
      readModFrm.reset();      
     modeChange(isModifyMode);             
     }
     
     
     //첨부 파일 삭제 
     function deleteFile_f(e){     
     console.log(e.target);
       if(e.target.tagName != "I") return false;
       const iTag = e.target;
       const fid = iTag.getAttribute("data-fid");
       
       
     if(confirm("삭제하시겠습니까?")) {   
     //AJAX사용
     // XMLHttpRequest 객체 새성
     const xhttp = new XMLHttpRequest();
     
     //서비스 요청
     const uri = `http://localhost:9080/pfpkg/board/deleteFile/${fid}`;
     
     xhttp.open("get", uri)
     xhttp.send();
     
     //응답처리
     //readystate
     //0:open()이 호출되지 않은상태
     //1:open()이 실행된 상태 -> 서버에 연결됨.
     //2:send()가 실행된 상태, 서버가 클라이언트 요청을 받았음.
     //3:서버가 클라이언트 요청을 처리중 / 응답헤더는 수신했으나 바디가 수신중인 상태
     //4:서버가 클라이언트의 요청을 완료 햇고 , 서버도 응답이 완료된 상태
     xhttp.addEventListener("readystatechange", ajaxCall);
     
     function ajaxCall(e){
     if(this.readyState == 4 && this.status == 200){
     //서버에서 삭제를 처리햇으면 
     if(this.responseText == "success"){
     //성공했을때 첨부파일 표시 하던 tag 삭제 처리  
     const parent = iTag.parentElement.parentElement;
     parent.remove(iTag); 		
     }else{     
     console.log("파일삭제 실패!") 
     
     }
     }     
     }
     
     }
     
     
     
     
     }
     
     