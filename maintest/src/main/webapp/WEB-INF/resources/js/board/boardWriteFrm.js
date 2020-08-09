'use strict'


        const writeBtn = document.getElementById("writeBtn");
        const writeFrm = document.getElementById("writeFrm");
        writeBtn.addEventListener("click", writeBtn_f);
        
        
        
        function writeBtn_f(e){
 
            console.log("글등록 버튼 클릭");
         writeFrm.submit();
     }
     