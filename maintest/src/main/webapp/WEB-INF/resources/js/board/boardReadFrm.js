'use strict'

<!--읽기모드 -->
        const peplyBtn = document.getElementById("peplyBtn");
         const modifyBtn = document.getElementById("modifyBtn");
        const listBtn = document.getElementById("listBtn");
      	peplyBtn.addEventListener("click", peplyBtn_f);  
        modifyBtn.addEventListener("click", modifyBtn_f);        
         
        listBtn.addEventListener("click", listBtn_f);     
      
      
      <!-- 답글 -->      
      function peplyBtn_f(){      
      console.log("답글 버튼 클릭");
      const bnum = document.getElementById("bnum").value;
      const url = `/pfpkg/board/boardReplyFrm/${bnum}`;
      window.location.href=url;      
      }
      
      
      
        <!--수정모드 -->
        const deleteBtn = document.getElementById("deleteBtn");
        const saveBtn = document.getElementById("saveBtn");
        const cancelBtn = document.getElementById("cancelBtn");
        deleteBtn.addEventListener("click",deleteBtn_f);      
          saveBtn.addEventListener("click", saveBtn_f);           
        cancelBtn.addEventListener("click", cancelBtn_f);        
        
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
      const isModifyMode = false; 
       modeChange(isModifyMode);     
    const url = `/pfpkg/board/boardListFrm`;
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
     
     
     
     