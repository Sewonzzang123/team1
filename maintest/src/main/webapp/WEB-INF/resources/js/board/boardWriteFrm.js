'use strict'
        const writeBtn = document.getElementById("writeBtn");
        const writeFrm = document.getElementById("Frm");
        const listBtn = document.getElementById("listBtn");
        const catnumV = document.getElementById("catnum");
        const picsTag =  document.getElementById("pics")
        
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
        writeBtn.addEventListener("click", writeBtn_f);
        const bCateTag = document.getElementById("bcategory")
        const hidCateTag = document.getElementById("hidcategory")
        // 게시판 분류 카테고리 별 말머리 연동 ajax
        bCateTag.addEventListener("change", getHeadid_f);

    
        function init() {     	        
        	picsTag.addEventListener("change", function () {      
        		
        		console.log("ajax 호출")
        		
        		
                    if (this.files && this.files[0]) {
                        var formData = new FormData();
                        formData.append("file", this.files[0]);
       
                        var ajax = new XMLHttpRequest();
                        ajax.onreadystatechange = function () {
                            if (ajax.readyState === ajax.DONE) {
                                if (ajax.status === 200   || ajax.status === 201) {                                	
                                    const url = ajax.responseText;
                                    const content_area = document.querySelector('.content_area');
                                    const img = document    .createElement('img');
                                    const divTag = document.createElement("div")
                                    img.setAttribute("src", 'http://localhost:9080/pfpkg/tmpphoto/' + url);
                                    img.setAttribute("name", url);
                                    img.setAttribute("class","img_file");
                                    content_area.append(divTag); //그림 상단에 글자 입력 공백 주는 용도
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
                
            	getHeadid_f();
        }

        function writeBtn_f(e) {
            e.preventDefault();
            const content_area = document.querySelector('.content_area');
            const tcontent_area = document.querySelector('.tcontent_area');
            const thumbnail_name = document.querySelector('.img_file');
            const listBtn = document.querySelector('#listBtn');

            if (thumbnail_name != null) {
                const thumbnail = document.querySelector('.thumbnail');
                thumbnail.value = thumbnail_name.getAttribute('name');
                console.log("2번" + thumbnail);
                alert("1번 파일 썸네일 이름 셋팅 완료");
            }
      
            tcontent_area.value =  content_area.innerHTML;
            writeFrm.submit();
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
   