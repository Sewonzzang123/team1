'use strict'     
	const bCateTag = document.getElementById("bcategory");
	const hidCateTag = document.getElementById("hidcategory");
	const hidnum = document.querySelector('#hidnum').value;
	let selected ='';
	
	//게시판 분류 카테고리 별 말머리 연동 ajax
	bCateTag.addEventListener("change", getHeadid_f);        
      	
	getHeadid_f()	
	
	//보드 카테고리에 맞는 말머리 불러오기 
	//ajax
	function getHeadid_f(e) {
	  const bCateTagVal = bCateTag.value;
	
	  //ajax 
	  const xhttp = new XMLHttpRequest();
	
	  xhttp.addEventListener("readystatechange", ajaxCall);
	
	  function ajaxCall(e) {
	    //처리완료 시 응답처리 	  
	    if (this.readyState == 4 && this.status == 200) {
	      const responseObj = this.responseText;
	      const jsonToJsObj = JSON.parse(responseObj);
	      switch (jsonToJsObj.rtcode) {
	        case "00":
	          const hidcategory = jsonToJsObj.hidcategory;
	          hidCateTag.innerHTML = "";
	          Array.from(hidcategory).forEach(e => {
	          	if(hidnum==e.hidnum){
	          	selected = 'selected';
	          	} else {selected = '';}
	          	console.log(selected);
	            hidCateTag.innerHTML += "<option "+selected+" value='" + e.hidnum + "'>" + e.hidname + "</option>"
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