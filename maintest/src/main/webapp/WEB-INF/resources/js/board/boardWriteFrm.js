'use strict'


        const writeBtn = document.getElementById("writeBtn");
        const writeFrm = document.getElementById("writeFrm");
         const listBtn = document.getElementById("listBtn");
           
        writeBtn.addEventListener("click", writeBtn_f);
        listBtn.addEventListener("click", listBtn_f);
        							
        
        function writeBtn_f(e){ 
        e.preventDefault();
            console.log("글등록 버튼 클릭");
         writeFrm.submit();
     }
     
    function   listBtn_f(e){    
    
    const url = `/pfpkg/board/boardListFrm`;
    window.location.href=url;
     
     
     }