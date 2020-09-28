/**
 * 
 */
 "use-strict"

  window.scrollTo(1, 300);

					
 	const saveBtn = document.getElementById('saveBtn');
			saveBtn.addEventListener('click',saveBtn_f);
			function saveBtn_f(event){
				event.preventDefault();
				console.log(getucode);
				//로그인 체크
				if(!checkLogin()){
					return false;}

				//저장할 리스트 이름 입력
				let answer = window.prompt("저장할 리스트 이름을 작성하세요.");
				if(answer.trim().length<2 || answer.trim().length>11){
					alert('2자 이상 10자 이하로 입력하셔야 합니다.');
					return false;
					}else{
						//리스트 저장
						window.opener.name = "parentPage";
						let frm = document.saveList;	
						frm.target="parentPage";	
						frm.action="http://localhost:9080/pfpkg/board/saveList/"+answer;
						frm.submit();
						window.close();
						}
				
				}
			function checkLogin(){
				if(getucode.trim().length==0){
					alert('로그인을 해야 사용가능한 기능입니다.');
					return false;}
				return true;
				}

			function checkItem(e){
				if(e.closest('.item').querySelector('input[name="checked"]').value =='false'){
					e.closest('.item').querySelector('input[name="checked"]').value = "true";
					e.parentElement.classList.add('del');
					} else{
					e.closest('.item').querySelector('input[name="checked"]').value = "false";
					e.parentElement.classList.remove('del');
					}
				}
		
		document.getElementById('printbtn').addEventListener("click",function(){window.print();});
		document.getElementById('pdfbtn').addEventListener("click",function(){
		var x = document.querySelector('html');
    x.style.width = '800px';
			let page = document.getElementsByClassName('page');
    //pdf_wrap을 canvas객체로 변환
    html2canvas(page[0]).then(function (canvas) {
      var doc = new jsPDF('p', 'mm', 'a4'); //jspdf객체 생성
      var imgData = canvas.toDataURL('image/png'); //캔버스를 이미지로 변환
      doc.addImage(imgData, 'PNG', 0, 0); //이미지를 기반으로 pdf생성
      doc.save('mylist.pdf'); //pdf저장
    });    
    
    x.style.width = 'auto';
    
		
		});

					
 