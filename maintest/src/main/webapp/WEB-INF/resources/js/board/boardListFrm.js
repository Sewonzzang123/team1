      'use strict'
        
  const writeBtn = document.getElementById("writeBtn");
  
  writeBtn.addEventListener("click",writeBtn_f);
  
  function writeBtn_f(e){
         console.log("글쓰기 버튼 클릭");
   //글쓰기 창으로 이동
            window.location.href = "/pfpkg/board/boardWriteFrm"
  }
  
  
 