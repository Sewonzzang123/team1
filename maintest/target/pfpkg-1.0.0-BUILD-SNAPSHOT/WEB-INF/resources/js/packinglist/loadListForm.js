	const goMyListBtn = document.getElementById('goMyListBtn');
	goMyListBtn.addEventListener('click',goMyListBtn_f);

	function goMyListBtn_f(){
		let url = "http://localhost:9080/pfpkg/mypage/mylist";
		window.opener.location = url;
		window.close();
		}
	

	function changeList_f(e) {
		let lnum = e.value;
		let url = "http://localhost:9080/pfpkg/itemlist/loadListForm/" + lnum;
		window.location.href = url;
	}

	function uploadBtn_f() {
		let answer = confirm("지금까지 작성한 리스트 내용이 사라집니다. 그래도 불러오시겠습니까?");
		if(answer){
			let lnum = document.querySelector('option[selected="selected"]').value;
			let url = "http://localhost:9080/pfpkg/" + lnum;
			window.opener.location = url;
			window.close();
		}else{
			return false;
		}
	}