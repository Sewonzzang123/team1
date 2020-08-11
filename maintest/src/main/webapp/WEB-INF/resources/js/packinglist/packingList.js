window.addEventListener('load',init);

const addItemTag = document.getElementById("additem");
let idTag = null;
let classTag = null;

function init(){
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
		newitem.className+='tab_box  tab_box'+idTag ;
		newitem.innerHTML = '<td><a href="#"><i class="fas fa-plus"></i></a></td><td><div class="newitem">'+add+'</div></td>';

		document.querySelector('.tab_box').parentElement.prepend(newitem);		
		addItemTag.parentElement.firstElementChild.nextElementSibling.firstElementChild.nextElementSibling.prepend(newitem);

 		document.querySelector('i.fa-plus').addEventListener('click', addlist_f);
		}
		
		document.querySelectorAll('i.fa-plus').forEach(i => {
		i.addEventListener('click', function() {
				
					//플러스 클릭하면 안보이게 만들기
					i.parentElement.parentElement.parentElement.style.display="none";


					//카테고리 아래에 li생성
					let addListItem = document.createElement('li');
					//클릭한 아이템의 정보
					let itemid = i.parentElement.parentElement.nextElementSibling.innerHTML;
					//여기에 체크박스, 숫자, 마이너스 버튼 생성해야됨
					addListItem.innerHTML ='<div><input type="checkbox">'+ itemid+'<input type="text" name="num" value="1"/><button class="plusBtn" onClick="plus(this)">증가</button><button class="minusBtn" onClick="minus(this)">감소</button><a href="#"><i class="fas fa-minus"></i></a></div>';		
					let inputclass = document.querySelector("."+classTag);
					inputclass.parentElement.lastElementChild.append(addListItem);
					
					/*
					//아이템 리스트에 마이너스 버튼이 생기게 하기			
					let deleteListItem = document.createElement('tr');
					deleteListItem.className+='tab_box  tab_box'+idTag ;
					deleteListItem.innerHTML = '<td><a href="#"><i class="fas fa-minus"></i></a></td><td>'+itemid+'</td>';
	   			i.parentElement.parentElement.parentElement.parentElement.appendChild(deleteListItem);
	   			document.querySelector('.additem').firstElementChild.appendChild(deleteListItem);
	   			
	   			//마이너스 버튼 생긴거 이벤트 처리
	   			document.querySelectorAll('i.fa-minus').forEach(i => {
					i.addEventListener('click', deletelist_f);
		

				
				});
							*/
	    });
	});
	

	
}
	function deletelist_f(e){
	
					document.getElementsByClassName(e.target.parentElement.parentElement.nextElementSibling.firstElementChild.className)[0].parentElement.parentElement.remove();
					document.getElementsByClassName(e.target.parentElement.parentElement.nextElementSibling.firstElementChild.className)[1].parentElement.parentElement.remove();
					document.getElementsByClassName(e.target.parentElement.parentElement.nextElementSibling.firstElementChild.className)[0].parentElement.parentElement.style.display="table-row";
					
	}
			function addlist_f(e){
					let itemid = document.querySelector('i.fa-plus').parentElement.parentElement.nextElementSibling.innerHTML;

					let addListItem = document.createElement('li');
					//여기에 체크박스, 숫자, 마이너스 버튼 생성해야됨
					addListItem.innerHTML ='<div><input type="checkbox">'+ itemid+'<input type="text" name="num" value="1"/><button class="plusBtn">증가</button><button class="minusBtn">감소</button><a href="#"><i class="fas fa-minus"></i></a></div>';
					
					let inputclass = document.querySelector("."+classTag);
					inputclass.parentElement.lastElementChild.prepend(addListItem);
					/*
					//아이템 리스트에 마이너스 버튼이 생기게 하기			
					let deleteListItem = document.createElement('tr');
					deleteListItem.innerHTML = '<td><a href="#"><i class="fas fa-minus"></i></a></td><td>'+itemid+'</td>';
	   			e.target.parentElement.parentElement.parentElement.parentElement.appendChild(deleteListItem);
	   			document.querySelector('.additem').firstElementChild.appendChild(deleteListItem);
	   			
	   			//마이너스 버튼 생긴거 이벤트 처리
	   			document.querySelectorAll('i.fa-minus').forEach(i => {
					i.addEventListener('click', deletelist_f);
	
				});
								*/
					
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



	

