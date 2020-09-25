'use strict'




//hidden 속성들
const bnum = document.getElementById("bnum").value;
const returnPage = document.getElementById("returnPage").value;
const catnumV = document.getElementById("catnum").value;
const picsTag =  document.getElementById("pics")


        const replyBtn = document.getElementById("replyBtn");
        const Frm = document.getElementById("Frm");
         const listBtn = document.getElementById("listBtn");
           
        replyBtn.addEventListener("click", replyBtn_f);
        listBtn.addEventListener("click", listBtn_f);     
        
        /* 사진 추가 */
        const add_img_btn = document.querySelector('.add_img_btn');
     	const add_img = document.querySelector('.add_img');
     	     	
         add_img_btn.addEventListener('click', (e) => {
     		e.preventDefault();
     		picsTag.click();
     		console.log('클릭됨');
     		}
     	)				
             	
        window.addEventListener("load", init);


         function init() {     	
         	picsTag.addEventListener("change", function () {       
                     if (this.files && this.files[0]) {
                         var formData = new FormData();
                         formData.append("file", this.files[0]);
               
                         console.log("업로드");

                         var ajax = new XMLHttpRequest();
                         ajax.onreadystatechange = function () {
                             if (ajax.readyState === ajax.DONE) {
                                 if (ajax.status === 200
                                     || ajax.status === 201) {
                                 	
                                     const url = ajax.responseText;
                                     console.log(" ajax.responseText  =========================" +  url);
                                     
                                     
                                     const content_area = document.querySelector('.content_area');
                                     const img = document    .createElement('img');
                                     img.setAttribute("src", 'http://localhost:9080/pfpkg/tmpphoto/' + url);
                                     img.setAttribute("name", url);
                                     img.setAttribute("class","img_file");

                                     content_area.append(img);
                                 } else {
                                     console.error(ajax.responseText);

                                 }
                             }
                         };
                         ajax.open(
                                 "POST",   "http://localhost:9080/pfpkg/board/setphoto",   true); //
                         ajax.send(formData);
                     }
                 })
  
         }
     	
        
        //답글등록 버튼
         function replyBtn_f(e){  
            e.preventDefault();
            const content_area = document.querySelector('.content_area');
            const tcontent_area = document.querySelector('.tcontent_area');
            const thumbnail_name = document.querySelector('.img_file');
            const listBtn = document.querySelector('#listBtn');
            if (thumbnail_name != null) {
                const thumbnail = document.querySelector('.thumbnail');
                thumbnail.value = thumbnail_name.getAttribute('name');
             
  
            }
      
            
            tcontent_area.value =  content_area.innerHTML.trim();

           
        
            console.log( content_area.innerHTML);
            
            console.log("답글등록 버튼 클릭");
         Frm.submit();
     }
        
            
        
        
        //목록 버튼
  function   listBtn_f(e){        
	   const url = `/pfpkg/board/${catnum}/${returnPage}/${searchType}/${searchKeyword}`;    
	   window.location.href=url;       
    }
   
    

     
     
