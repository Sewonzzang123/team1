
        const writeBtn = document.getElementById("writeBtn");
        const writeFrm = document.getElementById("Frm");
        const listBtn = document.getElementById("listBtn");

        writeBtn.addEventListener("click", writeBtn_f);
        listBtn.addEventListener("click", () => {
        });


        const filesBoxTag = document.getElementById("filesBox");
        const filesTag = document.getElementById("files");

        const bCateTag = document.getElementById("bcategory")
        const hidCateTag = document.getElementById("hidcategory")
        // 게시판 분류 카테고리 별 말머리 연동 ajax
        bCateTag.addEventListener("change", getHeadid_f);

        function writeBtn_f(e) {
            e.preventDefault();
            const content_area = document.querySelector('.content_area');
            const bcontent_area = document.querySelector('.bcontent_area');
            const thumbnail_name = document.querySelector('.img_file')

            if (thumbnail_name != null) {
                const thumbnail = document.querySelector('.thumbnail');
                thumbnail.value = thumbnail_name.getAttribute('name');
                console.log("2번" + thumbnail);
                alert("not null");
            }
            bcontent_area.value = content_area.innerHTML;
            writeFrm.submit();
        }

        window.addEventListener("load", init);
        
        
        //대석
        const picsTag =  document.getElementById("pics")
//        function init() {  
//        	
//        
//        		picsTag.addEventListener("change", function () {        		
//                const content_area = document.querySelector('.content_area');
//                const imgTag = document.createElement('img');
//            	 //메모리상에 로딩된 uri정보를 읽어와서 미리보기
//                const url = URL.createObjectURL(picsTag.files[0]);
//                imgTag.src = url;            
//                content_area.appendChild(imgTag)
//                
//                //web server DB 저장 후 물리위치 불러와야함. ajax
//                
//                const xhttp = new XMLHttpRequest();
//                xhttp.addEventListener("readystatechange", ajaxCall(e))
//                //요청 메시지 
//                
//                let formData = new FormData(writeFrm);
//                console.log("formData.has('files') ======boolean=======" +formData.has('file') )               
//                formData.append("file", this.files[0]);
//                
//           
//                
//                const reqMsg = {};
//                reqMsg.files = this.files[0];
//                const intoJson = JSON.stringify(reqMsg);                
//               
//                
//                xhttp.open("post", "http://localhost:9080/pfpkg/board/setphoto")
//                xhttp.setRequestHeader("Content-Type", "multipart/formed-data")
//                xhttp.send(formData);        
//              
//                //xhttp.setRequestHeader("Content-Type","application/json;charset=utf-8");                
//                //xhttp.send(JSON.stringify(reqMsg));
//          
//                
//                
//  
//                
//              });    
//        	
//        	
//        	
//        	
//        	
//        	
//        	
////        	function ajaxCall(e){ 	       		
////        		console.log("e.responstText 응답 OK" + e.responstText)
////        		let Parsed = JSON.parse(this.responseText)
////        		console.log("Parsed    ============" + Parsed)
////        		
////       		 if (this.readyState == 4 && this.status == 200) {
////       			 console.log("데이터 응답 OK")
////       			 console.log("데이터 응답 OK")
////      
////       			 
////       		 }else{
////       			 console.log("데이터 응답 NG")
////       		 }
////       		 }
////        	}
        	
        	
        	
            //=======================================================================
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
                                    img.setAttribute("src", 'http://localhost:9080/pfpkg/photo/' + url);
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
       
        
            const jsObj = {};
            jsObj.catnum = bCateTagVal;
            const jsObjToJson = JSON.stringify(jsObj);
            xhttp.open("POST", "http://localhost:9080/pfpkg/board/headid");
            xhttp.setRequestHeader("Content-Type", "application/json;charset=utf-8");
            xhttp.send(jsObjToJson);
        }
        
        }
   