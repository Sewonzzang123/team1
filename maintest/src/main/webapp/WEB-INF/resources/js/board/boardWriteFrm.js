'use strict'


        const writeBtn = document.getElementById("writeBtn");
        const writeFrm = document.getElementById("writeFrm");
        const listBtn = document.getElementById("listBtn");
        const bcategoryTag = document.getElementById("bcategory.catnum");
        
        writeBtn.addEventListener("click", writeBtn_f);
        listBtn.addEventListener("click", listBtn_f);
        bcategoryTag.addEventListener("change", bcategoryTag_f);
        
        							
        
        function writeBtn_f(e){ 
        e.preventDefault();
            console.log("글등록 버튼 클릭");
        writeFrm.submit();
    		 }
     
    	function   listBtn_f(e){
    const returnPage = document.getElementById("returnPage").value;
    const url = `/pfpkg/board/boardListFrm/${returnPage}`;
    window.location.href=url;     
         
     		}
     		
     		function bcategoryTag_f(){
			


				const xhttp = new XMLHttpRequest();

				

     		
     		
     		}
     		
     		
     		
     		