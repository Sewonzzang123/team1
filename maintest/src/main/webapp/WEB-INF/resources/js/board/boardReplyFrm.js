'use strict'


        const replyBtn = document.getElementById("replyBtn");
        const replyFrm = document.getElementById("replyFrm");
         const listBtn = document.getElementById("listBtn");
           
        replyBtn.addEventListener("click", replyBtn_f);
        listBtn.addEventListener("click", listBtn_f);
        							
        
        function replyBtn_f(e){ 
        e.preventDefault();
            console.log("답글등록 버튼 클릭");
         replyFrm.submit();
     }
     
   
    
    
    
         //목록 버튼
   function   listBtn_f(e){        
   
    const returnPage = document.getElementById("returnPage").value;
    const url = `/pfpkg/board/boardListFrm/${returnPage}`;
    window.location.href=url;     
     
     }

     
     
