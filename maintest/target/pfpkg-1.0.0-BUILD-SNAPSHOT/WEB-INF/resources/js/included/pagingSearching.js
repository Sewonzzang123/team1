'use strict'
        

  const searchBtn = document.getElementById("searchBtn");
  searchBtn.addEventListener("click", searchBtn_f);
  
  
  //검색 기능
           function searchBtn_f(){
            const searchType = document.getElementById("searchType").value;
            const searchKeyword = document.getElementById("searchKeyword").value;
            const returnBoard = document.getElementById("catnum").value;
            const url = `/pfpkg/board/${returnBoard}/1/${searchType}/${searchKeyword}`
            window.location.href = url;
        }
  
  
 