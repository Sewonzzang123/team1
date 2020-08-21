window.addEventListener('load',init);
const addItemTag = document.getElementById("additem");
let idTag = null;
let classTag = null;

function init(){
	const saveBtn = document.getElementById("saveBtn");
	saveBtn.addEventListener("click",saveBtn_f);
//탭박스 이벤트
$('.tab_box').hide();

//버튼 색 제거,추가
$('.tab_menu_btn').on('click',function(){
  $('.tab_menu_btn').removeClass('on');
  $(this).addClass('on');
});

//1번 컨텐츠
$('.tab_menu_btn1').on('click',function(){
  $('.tab_box').hide();
  $('.tab_box1').show();
});

//2번 컨텐츠
$('.tab_menu_btn2').on('click',function(){
  $('.tab_box').hide();
  $('.tab_box2').show();
});


//3번 컨텐츠
$('.tab_menu_btn3').on('click',function(){
  $('.tab_box').hide();
  $('.tab_box3').show();
});


//4번 컨텐츠
$('.tab_menu_btn4').on('click',function(){
  $('.tab_box').hide();
  $('.tab_box4').show();
});


//5번 컨텐츠
$('.tab_menu_btn5').on('click',function(){
  $('.tab_box').hide();
  $('.tab_box5').show();
});

//6번 컨텐츠
$('.tab_menu_btn6').on('click',function(){
  $('.tab_box').hide();
  $('.tab_box6').show();
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
		let add = window.prompt("아이템 이름을 입력하세요");
		if(!checkItem(add)){return;}
		
		let newitem = document.createElement('tr');
		newitem.className +='tab_box  tab_box'+idTag ;
		newitem.innerHTML = '<td><a href="#"><i class="fas fa-minus" onClick="deletelist2_f(this)" style="display:none"></i><i class="fas fa-plus" onClick="addlist_f(this)"></i></a></td><td><div class="newitem" selected="false">'+add+'</div></td>';

		document.querySelector('.tab_box').parentElement.prepend(newitem);		
		}
	
}

function checkItem(e){
	if(e.trim()==0){
		window.alert("한글자 이상 입력해야합니다!");
		return false;
	}
	return true;
}


	
	function addlist_f(e){
					//플러스 클릭하면 안보이게 만들기
					e.previousElementSibling.style.display="inline";
					e.style.display="none";
					let itemNum =null;
				
					
					let itemid = e.parentElement.parentElement.nextElementSibling.innerHTML;					
					let split =itemid.split(' ');
				
					
					let itemid2 = e.parentElement.parentElement.nextElementSibling.firstChild;
					let itemName =  e.parentElement.parentElement.nextElementSibling.firstChild.innerText;
					
					if(itemid2.className=='newitem'){
					 itemNum = itemid2.className;
					}else{
					 itemNum = itemid2.className.substring(4);
					 }
					 
					
					let itemNameTag = split[0];
					itemNameTag += ' name="iname" value="'+itemid2.innerText+'" ';
					for(let n=1; n<split.length; n++){itemNameTag += split[n];}
			
					let addListItem = document.createElement('li');
					//여기에 체크박스, 숫자, 마이너스 버튼 생성해야됨
					let a = '<div><input name="checked" value="false" type="checkbox" onClick="check_f(this)">'+itemNameTag+'<input type="text" name="icount" value="1" max="100"/><div class="edit_number"><input type="button" class="plusBtn" onClick="plus(this)">증가</input><input type="button"  class="minusBtn" onClick="minus(this)">감소</input></div><a href="#"><i class="fas fa-minus" onClick="deletelist_f(this)">';
					a += '</i></a></div><input type="hidden" name="icategory" value="'+idTag+'"/>';
					a += '<input type="hidden" name="inum" value="'+itemNum+'"/><input type="hidden" name="iname" value="'+itemName+'"/><input type="hidden" name="ichecked" value="false"/>';
					addListItem.innerHTML =a;
					
					let inputclass = document.querySelector("."+classTag);
					inputclass.parentElement.lastElementChild.prepend(addListItem);
								
					
	}

	function deletelist_f(e){
					document.getElementsByClassName(e.parentElement.parentElement.parentElement.firstElementChild.firstElementChild.nextElementSibling.className)[0].parentElement.previousElementSibling.firstElementChild.firstElementChild.style.display="none";
					document.getElementsByClassName(e.parentElement.parentElement.parentElement.firstElementChild.firstElementChild.nextElementSibling.className)[0].parentElement.previousElementSibling.firstElementChild.lastElementChild.style.display="inline";				
					document.getElementsByClassName(e.parentElement.parentElement.parentElement.firstElementChild.firstElementChild.nextElementSibling.className)[1].parentElement.parentElement.remove();								
								
	}
	
	function deletelist2_f(e){	
					e.style.display="none";	
					e.nextElementSibling.style.display="inline";
				
					document.getElementsByClassName(e.parentElement.parentElement.nextElementSibling.firstElementChild.className)[1].parentElement.parentElement.remove();	
												
	}

	function addClass(element, className) 
	{ element.className += " " + className; };
	
	function plus(e){

		let a = e.parentElement.parentElement.firstElementChild.nextElementSibling.nextElementSibling;
		a.value = Number(a.value)+1;
	}
	
	function minus(e){

		let a = e.parentElement.parentElement.firstElementChild.nextElementSibling.nextElementSibling;
		if(Number(a.value)-1<1){return;}
		a.value = Number(a.value)-1;
	}
	
	function check_f(e){

		if(e.parentElement.firstElementChild.nextElementSibling.getAttribute('selected')=='false'){	
		e.parentElement.firstElementChild.nextElementSibling.setAttribute('selected','true');
		e.value="true";
		}else{
		e.parentElement.firstElementChild.nextElementSibling.setAttribute('selected','false');
		e.value="false";
		}	
	}
	
	function saveBtn_f(){
		const option 	= "width=465,height=540,location=no,resizable=no";
		let gsWin=window.open("about:blank","winName",option);
		let frm = document.form;
		frm.action="http://localhost:9080/pfpkg/itemlist/saveListForm";
		frm.target="winName";
		frm.submit();
	}
	




	
	




	

