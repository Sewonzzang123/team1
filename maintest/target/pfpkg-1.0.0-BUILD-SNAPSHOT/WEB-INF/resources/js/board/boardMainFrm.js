'use strict'
        
  const writeBtn = document.getElementById("writeBtn");   
    
    
  writeBtn.addEventListener("click",writeBtn_f);
    
  function writeBtn_f(e){
         console.log("글쓰기 버튼 클릭");
   //글쓰기 창으로 이동
   
   // 현재 보던 페이지 유지를 위한 returnPage값을 같이 보냄.
   const returnPage = document.getElementById("returnPage").value;
   const catnum = document.getElementById("catnum").value;
   console.log("글쓰기 returnPage 클릭" +returnPage);
   console.log("글쓰기 catnum 클릭" +catnum);
            
            const urI = `/pfpkg/board/boardWriteFrm/${catnum}/${returnPage}`
            
            window.location.href  = urI;
  }
  
  

  
  
  
  
  
  
  
 