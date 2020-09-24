'use strict'
        const modifyBtn = document.getElementById("modifyBtn");
        const writeFrm = document.getElementById("Frm");
        const cancelBtn = document.getElementById("cancelBtn");
        const catnumV = document.getElementById("catnum");
        const picsTag =  document.getElementById("pics")

        	//수정페이지 진입시 컨텐트영역에 img 태그 수 및 파일이름
        	const imgTags_ContentArea = document.querySelectorAll(".img_file");
        
        /* 사진 추가 */
       const add_img_btn = document.querySelector('.add_img_btn');
        
        
        //작성영역 및 출력 영역 
        const content_area = document.querySelector('.content_area');
        //바인딩할 데이터 삽입영역
        const tcontent_area = document.querySelector('.tcontent_area');
        //처음 등록한 사진의 파일이름  
        let thumbnail_name = document.querySelector('.img_file');
        //리스트 버튼
        const listBtn = document.querySelector('#listBtn');
    
        
        
   
        
        add_img_btn.addEventListener('click', (e) => {
    		e.preventDefault();
    		picsTag.click();
    		console.log('클릭됨');
    		}
    	)
        
        window.addEventListener("load", init);        
        modifyBtn.addEventListener("click", writeBtn_f);
        cancelBtn.addEventListener('click', () =>{
        	location.href="http://localhost:9080/pfpkg/board/"+catnumV.value;
        })

        const bCateTag = document.getElementById("bcategory")
        const hidCateTag = document.getElementById("hidcategory")
        // 게시판 분류 카테고리 별 말머리 연동 ajax
        bCateTag.addEventListener("change", getHeadid_f);

    
        function init() {     	
        	getHeadid_f();
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

        function writeBtn_f(e) {
            e.preventDefault();        
            thumbnail_name = document.querySelector('.img_file');
            if (thumbnail_name != null) {  
            	 const thumbnail = document.querySelector('.thumbnail');
                thumbnail.value = thumbnail_name.getAttribute('name');
          	   tcontent_area.value = content_area.innerHTML;
                
     }else{            	
     	   tcontent_area.value = content_area.innerHTML;
    }                   
            if(confirm("저장하시겠습니까?")){            
                writeFrm.submit();
     }
           
        }

        
        // 보드 카테고리에 맞는 말머리 불러오기
        // ajax
        function getHeadid_f(e) {
        	
            const bCateTagVal = bCateTag.value;
            
            // ajax
            const xhttp = new XMLHttpRequest();

            xhttp.addEventListener("readystatechange", ajaxCall);


            function ajaxCall(e) {
                // 처리완료 시 응답처리

                if (this.readyState == 4 && this.status == 200) {

                	console.log('작동성공');

                    const responseObj = this.responseText;
                    const jsonToJsObj = JSON.parse(responseObj);
                    switch (jsonToJsObj.rtcode) {
                        case "00":
                        	
                            const hidcategory = jsonToJsObj.hidcategory;
                            hidCateTag.innerHTML = "";
                            Array.from(hidcategory).forEach(e => {
                                hidCateTag.innerHTML += "<option value='" + e.hidnum + "' >" + e.hidname + "</option>"
                            })
                            break;

                        case "01":
                            console.log("말머리 불러오기 실패")
                            break;
                    }
                }
            }
            const jsObj = {};
            jsObj.catnum = bCateTagVal;
            const jsObjToJson = JSON.stringify(jsObj);
            xhttp.open("POST", "http://localhost:9080/pfpkg/board/headid");
            xhttp.setRequestHeader("Content-Type", "application/json;charset=utf-8");
            xhttp.send(jsObjToJson);
               
        }
        
        
        //수정 영역 변경사항 발생시 이벤트 리스너
       
        content_area.addEventListener("blur", content_area_f);
        content_area.addEventListener("keyup", content_area_f);
        content_area.addEventListener("paste", content_area_f);
        content_area.addEventListener("copy", content_area_f);
        content_area.addEventListener("cut", content_area_f);
        content_area.addEventListener("delete", content_area_f);
       // content_area.addEventListener("mouseup", content_area_f);
    	
        
        
        //수정페이지 진입시 컨텐트영역에 img 태그 수 및 파일이름
    	//const imgTags_ContentArea = document.querySelectorAll(".img_file");
        
        function content_area_f(){        	
        	console.log("컨텐트 영역 변경사항 발생")
        	let imgTags_modifying =  document.querySelectorAll(".img_file");
        	if(imgTags_modifying.length == 0 ) {
        		console.log("모든파일 tmp로 ")
        	}
        	let  cnt ;
        		for(let i = 0; i <imgTags_ContentArea.length; i++  ){ 	        			
        			cnt = 0;
        			for(let j = 0; j <imgTags_modifying.length; j++  ){ 	
        				console.log("originF.name=FileNowOn.name ::" +imgTags_ContentArea[i].name + "=" + imgTags_modifying[j].name)
        			if(imgTags_ContentArea[i].name.indexOf(imgTags_modifying[j].name) != -1 ){         				
        				cnt += 1;
        			}        				
        			}
        				
        
        				if(cnt  = 0){   
        				console.log("tmp 폴더로 이동")        	

        				
        				
        				
        				}
        		}
        		
        		
        }
        
        
        
   