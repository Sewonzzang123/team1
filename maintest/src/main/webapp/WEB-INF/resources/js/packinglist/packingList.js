window.addEventListener('load',init);
const addItemTag = document.getElementById("additem");
let idTag = null;
let classTag = null;
let preValue = 0;

function init(){
	const saveBtn = document.getElementById("saveBtn");
	saveBtn.addEventListener("click",saveBtn_f);
	const loadBtn = document.getElementById("loadBtn");
	loadBtn.addEventListener("click",loadBtn_f);
	const downloadBtn = document.getElementById("downloadBtn");
	downloadBtn.addEventListener("click",downloadBtn_f);
	const resetBtn = document.getElementById("resetBtn");
	resetBtn.addEventListener("click",resetBtn_f);
	
	//숨김 버튼 클릭시 아이템 숨김
	document.querySelectorAll('.fa-chevron-down').forEach(i=>{
			i.addEventListener('click',(e)=>{

				if(e.target.nextElementSibling.style.display=="none"){
					e.target.nextElementSibling.style.display="block";
				}else{
					e.target.nextElementSibling.style.display="none";
				}
			});
		});
	
	//리스트 불러올시 리스팅에 저장된 아이템들을 -버튼 주기
	Array.from(document.querySelectorAll('input[type="checkbox"]')).forEach((a)=>{		
		let itemClassTag = a.nextElementSibling.classList;
		document.querySelector("."+itemClassTag[0]).parentElement.parentElement.querySelector('.fa-minus').style.display="table-row";
		if(document.querySelector("."+itemClassTag[0]).parentElement.parentElement.querySelector('.fa-plus') !=null){
		document.querySelector("."+itemClassTag[0]).parentElement.parentElement.querySelector('.fa-plus').style.display="none";
		}else{
		console.log('만들기');
		}
		}
	);


//탭박스 이벤트
$('.tab_box').hide();

//버튼 색 제거,추가
$('.tab_menu_btn').on('click',function(){
  $('.tab_menu_btn').removeClass('on');
  $(this).addClass('on');
  $('.tab_menu_btn').parent().css('outline','');
  $(this).parent().css('outline','1px solid #553de8');
});


//1번 컨텐츠
$('.tab_menu_btn1').on('click',function(){
  $('.tab_box').hide();
  $('.tab_box1').show();
  $(this).parent().css('outline','1px solid #553de8');
});

//2번 컨텐츠
$('.tab_menu_btn2').on('click',function(){
  $('.tab_box').hide();
  $('.tab_box2').show();
  $(this).parent().css('outline','1px solid #553de8');
});


//3번 컨텐츠
$('.tab_menu_btn3').on('click',function(){
  $('.tab_box').hide();
  $('.tab_box3').show();
  $(this).parent().css('outline','1px solid #553de8');
});


//4번 컨텐츠
$('.tab_menu_btn4').on('click',function(){
  $('.tab_box').hide();
  $('.tab_box4').show();
  $(this).parent().css('outline','1px solid #553de8');
});


//5번 컨텐츠
$('.tab_menu_btn5').on('click',function(){
  $('.tab_box').hide();
  $('.tab_box5').show();
  $(this).parent().css('outline','1px solid #553de8');
});

//6번 컨텐츠
$('.tab_menu_btn6').on('click',function(){
  $('.tab_box').hide();
  $('.tab_box6').show();
  $(this).parent().css('outline','1px solid #553de8');
});



	//클릭한 클래스 이름 가져오기
	document.querySelectorAll('.tab_menu_btn').forEach(div => {
	    div.addEventListener('click', function() {
	      	idTag = this.id;
	      	let split = this.className.split(' ');
	      	classTag = split[0];
	      	
	    });
	});
	
	//새로운 아이템 추가 이벤트
	addItemTag.addEventListener('click',additem_f);
	function additem_f(e){
		if(!classTag){
		window.alert("카테고리 선택 후 아이템을 추가하세요!");
		return false;
		}
		let add = window.prompt("아이템 이름을 입력하세요");
		if(!checkItem(add)){return;}
		
		let newitem = document.createElement('div');
		newitem.className +='tab_box  tab_box'+idTag ;
		newitem.innerHTML = '<div class="add_or_delete"><i class="fas fa-minus" onClick="deletelist2_f(this)" style="display:none"></i><i class="fas fa-plus" onClick="addlist_f(this)"></i></div><div class="iname_wrapper"><div class="newitem iname" selected="false">'+add+'</div></div>';

		document.querySelector('.tab_box').parentElement.prepend(newitem);		
		}
	
}

function checkItem(e){
	if(e.trim().length==0){
		window.alert("한글자 이상 입력해야합니다!");
		return false;
	}else if(e.trim().length>10){
		window.alert('10자 이하로 작성하세요.');
		return false;
	}
	return true;
}


	
	function addlist_f(e){
					//플러스 클릭하면 안보이게 만들기
					e.previousElementSibling.style.display="inline";
					e.style.display="none";
					let itemNum =null;
				

					let itemid = e.parentElement.nextElementSibling.innerHTML;					
					
					let split =itemid.split(' ');

					let inum = e.parentElement.nextElementSibling.firstElementChild.classList[0];
					let itemName =  e.parentElement.nextElementSibling.innerText;
					

					if(inum=='newitem'){
						itemNum = inum;
					}else{
						itemNum = inum.substring(4);
					 }
					let itemNameTag = split[0];

					itemNameTag += ' name="i_name" value="'+itemName+' " ';

					for(let n=1; n<split.length; n++){								 
							if(n==2){
							itemNameTag +=" ";
							}
							itemNameTag +=split[n];
							
					}
					
					let addListItem = document.createElement('li');
					addListItem.setAttribute("class","row");
					//여기에 체크박스, 숫자, 마이너스 버튼 생성해야됨
					let a = '<div><input type="checkbox" onClick="check_f(this)">'+itemNameTag+'<input type="text" class="icount" name="icount" value="1" max="20" onfocus="getPreValue(this)" onchange="changeValue(this)"/><div class="edit_number">';
					a += '<i class="plusBtn fas fa-caret-up" onClick="plus(this)"></i><i class="minusBtn fas fa-caret-down" onClick="minus(this)"></i></div>';
					a += '<i class="fas fa-minus deleteItem" onClick="deletelist_f(this)"></i></div><input type="hidden" name="icategory" value="'+idTag+'"/>';
					a += '<input type="hidden" name="inum" value="'+itemNum+'"/><input type="hidden" name="i_name" value="'+itemName+'"/><input type="hidden" name="checked" value="false"/>';
					addListItem.innerHTML =a;
					
					let inputclass = document.querySelector("."+classTag);
					inputclass.parentElement.lastElementChild.prepend(addListItem);
					
		//AJAX 사용
		//1)XMLHTTPRequest 객체 생성
		const xhttp = new XMLHttpRequest();
		//2)서버응답 처리
		//readyState
		// 0 : open()가 호출되지 않은 상태
		// 1 : open()가 실행된 상태 server 연결됨
		// 2 : send()가 실행된 상태,  서버가 클라이언트 요청을 받았음.
		// 3 : 서버가 클라이언트 요청 처리중. 응답헤더는 수신했으나 바디가 수신중인 상태
		// 4 : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된상태
		xhttp.addEventListener("readystatechange", ajaxCall);
		function ajaxCall(event) {
			if (this.readyState == 4 && this.status == 200) {
				console.log(this.responseText);
				//json포맷 문자열 => 자바스크립트 ojb
				//const jsonObj = JSON.parse(this.responseText);
				
			}
		}
		//3)요청 파라미터만들기(json포맷) {"ucode":"0", "lname":"리스트이름"}
		const reqParam = {};
		//listingVO 형태로 넣어줌
		reqParam.ca_num = idTag;
		reqParam.checked = false;
		reqParam.icount = 1;
		reqParam.i_name = itemName;
		reqParam.i_num = itemNum;
		
		//js객체를 json포맷 문자열로 변환JSON.stringify()
		//json포맷 문자열을 js객체로 변환JSON.parse()
		const result = JSON.stringify(reqParam);
		//4)서비스요청
		xhttp.open("PUT","http://localhost:9080/pfpkg/packingList");
		
		//post json 요청시 필요
		xhttp.setRequestHeader("Content-Type","application/json;charset=utf-8");
		//querystring 전송 필요시
		//xhttp.send("result=" + result);
		//queryString 불필요시
		xhttp.send(result);
					
											
		}


	function deletelist_f(e){
	
		let cat = e.closest('.row').querySelector('input[name="icategory"]').getAttribute('value');									
		let iname = e.closest('.row').querySelector('div[name="i_name"]').textContent;
	
		//AJAX 사용
		//1)XMLHTTPRequest 객체 생성
		const xhttp = new XMLHttpRequest();
		//2)서버응답 처리
		//readyState
		// 0 : open()가 호출되지 않은 상태
		// 1 : open()가 실행된 상태 server 연결됨
		// 2 : send()가 실행된 상태,  서버가 클라이언트 요청을 받았음.
		// 3 : 서버가 클라이언트 요청 처리중. 응답헤더는 수신했으나 바디가 수신중인 상태
		// 4 : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된상태
		xhttp.addEventListener("readystatechange", ajaxCall);
		function ajaxCall(event) {
			if (this.readyState == 4 && this.status == 200) {
				console.log(this.responseText);
				//json포맷 문자열 => 자바스크립트 ojb
				//const jsonObj = JSON.parse(this.responseText);
				
			}
		}
		//3)요청 파라미터만들기(json포맷) {"ucode":"0", "lname":"리스트이름"}
		const reqParam = {};
		//listingVO 형태로 넣어줌
		reqParam.ca_num = cat;
		reqParam.i_name = iname;

		
		//js객체를 json포맷 문자열로 변환JSON.stringify()
		//json포맷 문자열을 js객체로 변환JSON.parse()
		const result = JSON.stringify(reqParam);
		//4)서비스요청
		xhttp.open("delete","http://localhost:9080/pfpkg/packingList");
		
		//post json 요청시 필요
		xhttp.setRequestHeader("Content-Type","application/json;charset=utf-8");
		//querystring 전송 필요시
		//xhttp.send("result=" + result);
		//queryString 불필요시
		xhttp.send(result);
					
		document.getElementsByClassName(e.previousElementSibling.previousElementSibling.previousElementSibling.classList[0])[0].parentElement.previousElementSibling.firstElementChild.style.display="none";
		document.getElementsByClassName(e.previousElementSibling.previousElementSibling.previousElementSibling.classList[0])[0].parentElement.previousElementSibling.lastElementChild.style.display="inline";				
		document.getElementsByClassName(e.previousElementSibling.previousElementSibling.previousElementSibling.classList[0])[1].parentElement.parentElement.remove();								
								
					
					
	
	}
	
	function deletelist2_f(e){	
		let cat = document.getElementsByClassName(e.parentElement.nextElementSibling.firstElementChild.className)[1].closest('.row').querySelector('input[name="icategory"]').getAttribute('value');									
		let iname = document.getElementsByClassName(e.parentElement.nextElementSibling.firstElementChild.className)[1].closest('.row').querySelector('div[name="i_name"]').textContent;
	console.log(cat);
	console.log(iname);
		//AJAX 사용
		//1)XMLHTTPRequest 객체 생성
		const xhttp = new XMLHttpRequest();
		//2)서버응답 처리
		//readyState
		// 0 : open()가 호출되지 않은 상태
		// 1 : open()가 실행된 상태 server 연결됨
		// 2 : send()가 실행된 상태,  서버가 클라이언트 요청을 받았음.
		// 3 : 서버가 클라이언트 요청 처리중. 응답헤더는 수신했으나 바디가 수신중인 상태
		// 4 : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된상태
		xhttp.addEventListener("readystatechange", ajaxCall);
		function ajaxCall(event) {
			if (this.readyState == 4 && this.status == 200) {
				console.log(this.responseText);
				//json포맷 문자열 => 자바스크립트 ojb
				//const jsonObj = JSON.parse(this.responseText);
				
			}
		}
		//3)요청 파라미터만들기(json포맷) {"ucode":"0", "lname":"리스트이름"}
		const reqParam = {};
		//listingVO 형태로 넣어줌
		reqParam.ca_num = cat;
		reqParam.i_name = iname;

		
		//js객체를 json포맷 문자열로 변환JSON.stringify()
		//json포맷 문자열을 js객체로 변환JSON.parse()
		const result = JSON.stringify(reqParam);
		//4)서비스요청
		xhttp.open("delete","http://localhost:9080/pfpkg/packingList");
		
		//post json 요청시 필요
		xhttp.setRequestHeader("Content-Type","application/json;charset=utf-8");
		//querystring 전송 필요시
		//xhttp.send("result=" + result);
		//queryString 불필요시
		xhttp.send(result);
	
					e.style.display="none";	
					e.nextElementSibling.style.display="inline";
				
					document.getElementsByClassName(e.parentElement.nextElementSibling.firstElementChild.className)[1].parentElement.parentElement.remove();	
												
	}

	function addClass(element, className) 
	{ element.className += " " + className; };
	
	function plus(e){
		let a = e.parentElement.parentElement.firstElementChild.nextElementSibling.nextElementSibling;
		if(Number(a.value)+1 >20){return false;}
		a.value = Number(a.value)+1;
		let cat = e.closest('.row').querySelector('input[name="icategory"]').getAttribute('value');	
		let iname = e.closest('.row').querySelector('div[name="i_name"]').textContent;
		
		//AJAX 사용
		//1)XMLHTTPRequest 객체 생성
		const xhttp = new XMLHttpRequest();
		//2)서버응답 처리
		//readyState
		// 0 : open()가 호출되지 않은 상태
		// 1 : open()가 실행된 상태 server 연결됨
		// 2 : send()가 실행된 상태,  서버가 클라이언트 요청을 받았음.
		// 3 : 서버가 클라이언트 요청 처리중. 응답헤더는 수신했으나 바디가 수신중인 상태
		// 4 : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된상태
		xhttp.addEventListener("readystatechange", ajaxCall);
		function ajaxCall(event) {
			if (this.readyState == 4 && this.status == 200) {
				console.log(this.responseText);
				//json포맷 문자열 => 자바스크립트 ojb
				//const jsonObj = JSON.parse(this.responseText);
				
			}
		}
		//3)요청 파라미터만들기(json포맷) {"ucode":"0", "lname":"리스트이름"}
		const reqParam = {};
		//listingVO 형태로 넣어줌
		reqParam.ca_num = cat;
		reqParam.i_name = iname;
		reqParam.icount = a.value;

		
		//js객체를 json포맷 문자열로 변환JSON.stringify()
		//json포맷 문자열을 js객체로 변환JSON.parse()
		const result = JSON.stringify(reqParam);
		//4)서비스요청
		xhttp.open("PUT","http://localhost:9080/pfpkg/packingList/modifyItem");
		
		//post json 요청시 필요
		xhttp.setRequestHeader("Content-Type","application/json;charset=utf-8");
		//querystring 전송 필요시
		//xhttp.send("result=" + result);
		//queryString 불필요시
		xhttp.send(result);
					
	}
	
	function minus(e){

		let a = e.parentElement.parentElement.firstElementChild.nextElementSibling.nextElementSibling;
		if(Number(a.value)-1<1){return;}
		a.value = Number(a.value)-1;
		let cat = e.closest('.row').querySelector('input[name="icategory"]').getAttribute('value');	
		let iname = e.closest('.row').querySelector('div[name="i_name"]').textContent;
		//AJAX 사용
		//1)XMLHTTPRequest 객체 생성
		const xhttp = new XMLHttpRequest();
		//2)서버응답 처리
		//readyState
		// 0 : open()가 호출되지 않은 상태
		// 1 : open()가 실행된 상태 server 연결됨
		// 2 : send()가 실행된 상태,  서버가 클라이언트 요청을 받았음.
		// 3 : 서버가 클라이언트 요청 처리중. 응답헤더는 수신했으나 바디가 수신중인 상태
		// 4 : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된상태
		xhttp.addEventListener("readystatechange", ajaxCall);
		function ajaxCall(event) {
			if (this.readyState == 4 && this.status == 200) {
				console.log(this.responseText);
				//json포맷 문자열 => 자바스크립트 ojb
				//const jsonObj = JSON.parse(this.responseText);
				
			}
		}
		//3)요청 파라미터만들기(json포맷) {"ucode":"0", "lname":"리스트이름"}
		const reqParam = {};
		//listingVO 형태로 넣어줌
		reqParam.ca_num = cat;
		reqParam.i_name = iname;
		reqParam.icount = a.value;

		
		//js객체를 json포맷 문자열로 변환JSON.stringify()
		//json포맷 문자열을 js객체로 변환JSON.parse()
		const result = JSON.stringify(reqParam);
		//4)서비스요청
		xhttp.open("PUT","http://localhost:9080/pfpkg/packingList/modifyItem");
		
		//post json 요청시 필요
		xhttp.setRequestHeader("Content-Type","application/json;charset=utf-8");
		//querystring 전송 필요시
		//xhttp.send("result=" + result);
		//queryString 불필요시
		xhttp.send(result);
	}
	
		
	function getPreValue(e){
		console.log(e.value);
		preValue = e.value;
		};

	function changeValue(e){
		console.log(e.value);
		if(isNaN(e.value)){
			window.alert('숫자를 입력하셔야 합니다.'); e.value=preValue; return false;
		}else if(e.value>20){
			window.alert('최대 갯수는 20개 입니다.'); e.value=preValue; return false;
		}else if(e.value<1){
				window.alert('최소 갯수는 1개입니다.'); e.value=preValue; return false;
		}else{
			let cat = e.closest('.row').querySelector('input[name="icategory"]').getAttribute('value');	
			let iname = e.closest('.row').querySelector('div[name="i_name"]').textContent;
		//AJAX 사용
		//1)XMLHTTPRequest 객체 생성
		const xhttp = new XMLHttpRequest();
		//2)서버응답 처리
		//readyState
		// 0 : open()가 호출되지 않은 상태
		// 1 : open()가 실행된 상태 server 연결됨
		// 2 : send()가 실행된 상태,  서버가 클라이언트 요청을 받았음.
		// 3 : 서버가 클라이언트 요청 처리중. 응답헤더는 수신했으나 바디가 수신중인 상태
		// 4 : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된상태
		xhttp.addEventListener("readystatechange", ajaxCall);
		function ajaxCall(event) {
			if (this.readyState == 4 && this.status == 200) {
				console.log(this.responseText);
				//json포맷 문자열 => 자바스크립트 ojb
				//const jsonObj = JSON.parse(this.responseText);
				
			}
		}
		//3)요청 파라미터만들기(json포맷) {"ucode":"0", "lname":"리스트이름"}
		const reqParam = {};
		//listingVO 형태로 넣어줌
		reqParam.ca_num = cat;
		reqParam.i_name = iname;
		reqParam.icount = e.value;

		
		//js객체를 json포맷 문자열로 변환JSON.stringify()
		//json포맷 문자열을 js객체로 변환JSON.parse()
		const result = JSON.stringify(reqParam);
		//4)서비스요청
		xhttp.open("PUT","http://localhost:9080/pfpkg/packingList/modifyItem");
		
		//post json 요청시 필요
		xhttp.setRequestHeader("Content-Type","application/json;charset=utf-8");
		//querystring 전송 필요시
		//xhttp.send("result=" + result);
		//queryString 불필요시
		xhttp.send(result);
		}
	};
	
	
	function check_f(e){
		let checked = null;
		if(e.parentElement.firstElementChild.nextElementSibling.getAttribute('selected')=='false'){	
		e.parentElement.firstElementChild.nextElementSibling.setAttribute('selected','true');
		e.parentElement.parentElement.lastElementChild.value ="true";
		checked = "true";
		}else{
		e.parentElement.firstElementChild.nextElementSibling.setAttribute('selected','false');
		e.parentElement.parentElement.lastElementChild.value ="false";
		checked = "false";
		}	
		
		let cat = e.closest('.row').querySelector('input[name="icategory"]').getAttribute('value');	
		let iname = e.closest('.row').querySelector('div[name="i_name"]').textContent;
		
		//AJAX 사용
		//1)XMLHTTPRequest 객체 생성
		const xhttp = new XMLHttpRequest();
		//2)서버응답 처리
		//readyState
		// 0 : open()가 호출되지 않은 상태
		// 1 : open()가 실행된 상태 server 연결됨
		// 2 : send()가 실행된 상태,  서버가 클라이언트 요청을 받았음.
		// 3 : 서버가 클라이언트 요청 처리중. 응답헤더는 수신했으나 바디가 수신중인 상태
		// 4 : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된상태
		xhttp.addEventListener("readystatechange", ajaxCall);
		function ajaxCall(event) {
			if (this.readyState == 4 && this.status == 200) {
				console.log(this.responseText);
				//json포맷 문자열 => 자바스크립트 ojb
				//const jsonObj = JSON.parse(this.responseText);
				
			}
		}
		//3)요청 파라미터만들기(json포맷) {"ucode":"0", "lname":"리스트이름"}
		const reqParam = {};
		//listingVO 형태로 넣어줌
		reqParam.ca_num = cat;
		reqParam.i_name = iname;
		reqParam.checked = checked;

		
		//js객체를 json포맷 문자열로 변환JSON.stringify()
		//json포맷 문자열을 js객체로 변환JSON.parse()
		const result = JSON.stringify(reqParam);
		//4)서비스요청
		xhttp.open("PUT","http://localhost:9080/pfpkg/packingList/modifyItem");
		
		//post json 요청시 필요
		xhttp.setRequestHeader("Content-Type","application/json;charset=utf-8");
		//querystring 전송 필요시
		//xhttp.send("result=" + result);
		//queryString 불필요시
		xhttp.send(result);
		
	}
	
	function saveBtn_f(){	
		if(!checkLogin()){ return false; }
		if(!checkListing()){ window.alert('하나 이상의 아이템을 선택하셔야 합니다.'); return false;}
		
		const option 	= "width=465,height=540,location=no,resizable=no";
		let gsWin=window.open("about:blank","winName",option);
		let frm = document.form;
		frm.action="http://localhost:9080/pfpkg/itemlist/saveListForm";
		frm.target="winName";
		frm.submit();
	}
	
	function checkListing(){
		if(document.querySelector('.category_grid').querySelector('.row') == null)
		{return false;}
		return true;
	}
	
	function checkLogin(){
		if(getid == ''){
			if(window.confirm('로그인을 하셔야 합니다. 로그인페이지로 이동하시겠습니까?')){
				window.location.herf="http://localhost:9080/pfpkg/loginForm";
				}else{return false;} 
				}
		return true;
		}
		
	function loadBtn_f(){	
		if(!checkLogin()){return false;}
			
		const option 	= "width=570,height=680,location=no,resizable=no";
		let gsWin=window.open("about:blank","winName",option);
		let frm = document.form;
		frm.action="http://localhost:9080/pfpkg/itemlist/loadListForm";
		frm.target="winName";
		frm.submit();
	}
	
	function downloadBtn_f(){
		if(!checkListing()){ window.alert('하나 이상의 아이템을 선택하셔야 합니다.'); return false;}
		const option 	= "width=800,height=540,location=no,resizable=no";
		let gsWin=window.open("about:blank","winName",option);
		let frm = document.form;
		frm.action="http://localhost:9080/pfpkg/itemlist/downloadListForm";
		frm.target="winName";
		frm.submit();
	}
	
	function resetBtn_f(){
		let answer = confirm("정말로 초기화 하시겠습니까?");
		if(answer){
			alert('초기화 되었습니다.');
			window.location.href="http://localhost:9080/pfpkg/itemlist/deleteAllItem";
		}else{
		return false;
		}
	}
	



	
	




	

