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
					addListItem.innerHTML ='<div><input type="checkbox" onClick="check_f(this)">'+ itemid+'<input type="text" name="icount" value="1"/><button class="plusBtn" onClick="plus(this)">증가</button><button class="minusBtn" onClick="minus(this)">감소</button><a href="#"><i class="fas fa-minus" onClick="deletelist_f(this)"></i></a></div>';
		
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
		
		//기존에 있는 아이템들을 담는 itemList
		var itemList = new List();
		//map1을 리스트로 만든 newitemList
		var newitemList = new List();
		
		const items = document.getElementsByTagName('li');
	
		for(var i=0; i<items.length; i++){
		
		if(items[i].firstChild.firstElementChild.nextElementSibling.className.trim()=='newitem'){
			//newitem 담는 map1
			let map1 = new Map();
			map1.set("iname",items[i].firstChild.firstElementChild.nextElementSibling.innerText);
			map1.set("icategory",items[i].parentElement.parentElement.firstElementChild.id);
			map1.set("ichecked",items[i].firstChild.firstElementChild.checked);	
			map1.set("icount",items[i].firstElementChild.firstElementChild.nextElementSibling.nextElementSibling.value);	
			newitemList.add(map1);
		}else{	
			//기존에 있는 아이템을 담는 map2
			let map2 = new Map();
			map2.set("inum",items[i].firstChild.firstElementChild.nextElementSibling.className.split('m')[1]);
			map2.set("icategory",items[i].parentElement.parentElement.firstElementChild.id);
			map2.set("ichecked",items[i].firstChild.firstElementChild.checked);	
			map2.set("icount",items[i].firstElementChild.firstElementChild.nextElementSibling.nextElementSibling.value);	
			itemList.add(map2);
				}
		};
		
		console.log(newitemList.elements);
		console.log(itemList.elements);
		

		 
		const url 		= event.target.href;
		const fname 	= event.target.id;
		const option 	= "width=460,height=540,location=no,resizable=no";
		window.open(url,fname,option);
			
	}

	//리스트 구조	
	function List() {
   	this.elements = {};
   	this.idx = 0;
   	this.length = 0;
	};

	List.prototype.add = function(element) {
   	this.length++;
   	this.elements[this.idx++] = element;
	};

	List.prototype.get = function(idx) {
  	 return this.elements[idx];
	};



	
	




	
