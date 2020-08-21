      'use strict'
        
  const writeBtn = document.getElementById("writeBtn");  
    const searchBtn = document.getElementById("searchBtn");
    
    
  writeBtn.addEventListener("click",writeBtn_f);
    searchBtn.addEventListener("click", searchBtn_f);
  
  function writeBtn_f(e){
         console.log("글쓰기 버튼 클릭");
   //글쓰기 창으로 이동
   
   // 현재 보던 페이지 유지를 위한 returnPage값을 같이 보냄.
   const returnPage = document.getElementById("returnPage").value;
            
            const urI = `/pfpkg/board/boardWriteFrm/${returnPage}`
            
            window.location.href  = urI;
  }
  
  
  
  //검색 기능
           function searchBtn_f(){
            const searchType = document.getElementById("searchType").value;
            const searchKeyword = document.getElementById("searchKeyword").value;
           // const returnPage = document.getElementById("returnPage").value;
            const url = `/pfpkg/board/boardListFrm/1/${searchType}/${searchKeyword}`
            window.location.href = url;
        }
  
  
 