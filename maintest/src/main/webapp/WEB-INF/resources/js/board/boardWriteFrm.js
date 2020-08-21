'use strict'


        const writeBtn = document.getElementById("writeBtn");
        const writeFrm = document.getElementById("writeFrm");
        const listBtn = document.getElementById("listBtn");
        const bcategoryTag = document.getElementById("bcategory.catnum");
        const filesDropAreaTag = document.getElementById("filesDropArea");      
        const filesTag = document.getElementById("files");
        
        
        writeBtn.addEventListener("click", writeBtn_f);
        listBtn.addEventListener("click", listBtn_f);
       
        filesDropAreaTag.addEventListener("click", filesDropAreaTag_f);
        
        //게시판 분류와 말머리 연동 ajax
  			bcategoryTag.addEventListener("change", getHeadid_f );
			
			
			
			
			
			
			
        
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
     		
          		
     		function filesDropAreaTag_f(e){
     		//첨부파일 버튼 클릭   
     		e.preventDefault();
     		let clickedTag  = e.target;
     		
     		let newWin = window.open("/pfpkg/board/boardListFrm/","width=500px, height=500px");
   	
   			 //버튼 클릭시 해당 버튼 삭제 및 첨부 영역 확장 + 문구 표시 
     		if(clickedTag.tagName === 'BUTTON') {     		     		
   		 	//clickedTag.parentNode.removeChild(clickedTag);     	   
   		 	filesDropAreaTag.style.gridColumn ='1/3';
   		 	filesDropAreaTag.style.border = '1px solid black';
   		 	filesDropAreaTag.style.backgroundColor= '#eeeeee';
   		 	filesDropAreaTag.style.height = '200px';
   		 	filesDropAreaTag.style.textAlign = 'center';
   		 	filesDropAreaTag.style.lineHeight = '200px';
     		//filesDropAreaTag.textContent = "클릭 또는 파일 Drag & Drop 하세요.";
    	}
   		 	// 영역 클릭시 파일 검색창 팝업 
      if(e.target.tagName != 'BUTTON'){
        filesTag.click();
        }
        
        //파일 Drag & Drop 설정        
        
        
        		filesDropAreaTag.addEventListener("dragstart", function(e){
        		e.preventDefault();
        		e.dataTransfer.setData("key", filesTag.files[0]);        		
        		});
        		
        		//마우스가 영역내에 있을때 클래스 추가해서 CSS 반영
        		filesDropAreaTag.addEventListener("dragover", function(e){
        	filesDropAreaTag.classList.add("dragOver")     		
        		e.preventDefault();
        		})
        		
        		//마우스가 영역을 벗어났을때 클래스 삭제  
        		filesDropAreaTag.addEventListener("dragleave", function(e){
        		e.preventDefault();
        	filesDropAreaTag.classList.remove("dragOver")     		
        		})
        		
        		
        		      		
        		//파일 드랍
        		filesDropAreaTag.addEventListener("drop", function(e){
        		e.preventDefault();
        		const file = e.dataTransfer.files[0];        		
        		const url = URL.createObjectURL(e.dataTransfer.files[0]);
        		
        		//정규식 const reg = /image\/\/* /;
        		
        		//표현할 영역 선택
  					console.log("드랍위치" + e.target)
        		
        		})
        		
        		filesTag.addEventListener("change", function (){
        		const url = URL.createObjectURL(filesTag.files[0]);
        		
        		filesDropAreaTag.src=url;
        		})
        		
        		
        		     	
     		}
     		
     		
     		
     		
     		//보드 카테고리에 맞는 말머리 불러오기 
     		//ajax
     	function	getHeadid_f(e){   	 	
     	
     	const selectTag = e.target     	
     console.log("selectTag" + selectTag);     	
     	
     const 	selectValue = selectTag.value;     
        console.log("selectValue" + selectValue);       

			    const xhttp = new XMLHttpRequest();
    			xhttp.addEventListener("readystatechange", ajaxCall);
    
   //---------------------------------------- ajaxCall------------------------------------------------
     function ajaxCall(e){         
       console.log("ajaxCall " + "요청 처리 되었을때 " +  this.responseText);
     const jsonObj = JSON.parse(this.responseText);
     console.log("jsonObj" + jsonObj);
     
     
     if(this.readyState == 4 && this.status == 200){
     console.log("요청 처리 되었을때 " +  this.responseText);    
     
     switch(jsonObj.result){
     case "OK": 
           console.log("요청 OK " +  this.responseText);  
           
     break;
     case "NG" : 
      console.log("요청 NG " +  this.responseText);  
      
     break;    
     }
     }
     }
     //-----------------------------------------------------------------------------------------------
     //요청 파라미터 보내기 
     
     const para = {};
     para.catnum =  selectValue;
         
      
      
     //서비스 요청
     const url = `http://localhost:9080/pfpkg/board/headid`;         
     xhttp.open("get", url);
          
     xhttp.send(para);
     
     
     	
     	
     	}
     		 
     	
     		