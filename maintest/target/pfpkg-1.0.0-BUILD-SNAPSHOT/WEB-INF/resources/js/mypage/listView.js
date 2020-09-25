/**
 * 
 */
 "use strict";
 $(document)
			.ready(
					function() {
						var ajax = new XMLHttpRequest();
						ajax.onreadystatechange = function() {
							if (ajax.readyState === ajax.DONE) {
								if (ajax.status === 200 || ajax.status === 201) {
									console.log(ajax.responseText);
								} else {
									console.error(ajax.responseText);
								}
							}
						};

						const ck = $(".ckeck");
						ck
								.change(function(e) {
									console.log('변경됨');
									if (e.target.checked == true) {
										e.target.parentElement.classList
												.add('del');

										ajax
												.open("POST",
														contextPath+"/mypage/check"); //
										ajax.send(e.target
												.getAttribute('linum'));
									} else {
										e.target.parentElement.classList
												.remove('del');

										ajax
												.open("POST",
														contextPath+"/mypage/uncheck"); //
										ajax.send(e.target
												.getAttribute('linum'));
									}
								});

						for ( var i in ck) {

							if (ck[i].checked == true) {
								const parent = ck[i].parentElement
								parent.classList.add('del');
								console.log('변경됨')
							}
						}
		
						$('#printbtn').click(function() {							
							window.print();
						});
					});
					
					
  $('#pdfbtn').click(function () {

    var x = document.querySelector('html');
    x.style.width = '800px';

    //pdf_wrap을 canvas객체로 변환
    html2canvas($('.page')[0]).then(function (canvas) {
      var doc = new jsPDF('p', 'mm', 'a4'); //jspdf객체 생성
      var imgData = canvas.toDataURL('image/png'); //캔버스를 이미지로 변환
      doc.addImage(imgData, 'PNG', 0, 0); //이미지를 기반으로 pdf생성
      doc.save('mylist.pdf'); //pdf저장
    });    
    
    x.style.width = 'auto';
    
  });