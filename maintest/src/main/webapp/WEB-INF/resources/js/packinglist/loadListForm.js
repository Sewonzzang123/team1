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
		let lnum = document.querySelector('option[selected="selected"]').value;
		let url = "http://localhost:9080/pfpkg/" + lnum;
		window.opener.location = url;
		window.close();
	}