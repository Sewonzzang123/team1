
	function changeList_f(e) {
		let lnum = e.value;
		let url = "http://localhost:9080/pfpkg/board/loadListForm/" + lnum;
		window.location.href = url;
	}

	function uploadBtn_f() {
			let lnum = document.querySelector('option[selected="selected"]').value;
			let url = "http://localhost:9080/pfpkg/board/boardWriteFrm/"+catnum+"/"+returnPage+"/"+lnum ;
			window.opener.location = url;
			window.close();
	}