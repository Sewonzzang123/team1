'use strict'


        const writeBtn = document.getElementById("writeBtn");
        const writeFrm = document.getElementById("writeFrm");
        writeBtn.addEventListener("click", writeBtn_f);
        
        
        
        function writeBtn_f(){
            console.log("글등록 버튼 클릭");
            writeFrm.addEventListener("click", writeFrm_f);
     }
     
     function writeFrm_f(){
writeFrm.submit();
     }