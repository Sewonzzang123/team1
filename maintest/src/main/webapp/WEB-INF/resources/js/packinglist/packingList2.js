window.addEventListener('load',init);

const addItemTag = document.getElementById("additem");
let idTag = null;
let classTag = null;

function init(){
const saveBtn = document.querySelector("#saveBtn");
saveBtn.addEventListener('click',saveBtn_f);
//탭박스 이벤트
$('.tab_box').hide();

//버튼 색 제거,추가
$('.tab_menu_btn').on('click',function(){
  $('.tab_menu_btn').removeClass('on');
  $(this).addClass('on')
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

		
		let newitem = document.createElement('tr');
		newitem.className +='tab_box  tab_box'+idTag ;
		newitem.innerHTML = '<td><a href="#"><i class="fas fa-minus" onClick="deletelist2_f(this)" style="display:none"></i><i class="fas fa-plus" onClick="addlist_f(this)"></i></a></td><td><div class="newitem" selected="false">'+add+'</div></td>';

		document.querySelector('.tab_box').parentElement.prepend(newitem);		
		}



	
}


	
	function addlist_f(e){
					//플러스 클릭하면 안보이게 만들기
					e.previousElementSibling.style.display="inline";
					e.style.display="none";
				
					
					let itemid = e.parentElement.parentElement.nextElementSibling.innerHTML;
					
					let addListItem = document.createElement('li');
					//여기에 체크박스, 숫자, 마이너스 버튼 생성해야됨
					addListItem.innerHTML ='<div><input type="checkbox" onClick="check_f(this)">'+ itemid+'<input type="text" name="num" value="1"/><button class="plusBtn" onClick="plus(this)">증가</button><button class="minusBtn" onClick="minus(this)">감소</button><a href="#"><i class="fas fa-minus" onClick="deletelist_f(this)"></i></a></div>';
					
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
		let a = e.parentElement.firstElementChild.nextElementSibling.nextElementSibling;
		a.value = Number(a.value)+1;
	}
	
	function minus(e){
		let a = e.parentElement.firstElementChild.nextElementSibling.nextElementSibling;
		if(Number(a.value)-1<1){return;}
		a.value = Number(a.value)-1;
	}
	
	function check_f(e){
		if(e.parentElement.firstElementChild.nextElementSibling.getAttribute('selected')=='false'){	
		e.parentElement.firstElementChild.nextElementSibling.setAttribute('selected','true');
		}else{
		e.parentElement.firstElementChild.nextElementSibling.setAttribute('selected','false');
		}	
	}
	
		function saveBtn_f(event){
		event.preventDefault();
		console.log("saveBtn_f");
		const url 		= event.target.href;
		const fname 	= event.target.id;
		const option 	= "width=460,height=540,location=no,resizable=no";
		console.log(option);
		window.open(url,fname,option);
		
		
		const items = document.getElementsByTagName('li');
		for(var i=0; i<items.length; i++){
		console.log("체크:"+items[i].firstChild.firstElementChild.checked);
		console.log("아이템번호:"+items[i].firstChild.firstElementChild.nextElementSibling.className);
		console.log("아이템이름:"+items[i].firstChild.firstElementChild.nextElementSibling.innerText);
		console.log("카테고리:"+items[i].parentElement.parentElement.firstElementChild.id);
		console.log("수량:"+items[i].firstElementChild.firstElementChild.nextElementSibling.nextElementSibling.value);
		};
		
	}
	
	
	




	

