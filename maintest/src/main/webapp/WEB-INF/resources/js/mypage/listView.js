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

						var g_oBeforeBody = document
								.getElementById('container').innerHTML;

						$('#printbtn').click(function() {

							// 프린트를 보이는 그대로 나오기위한 셋팅
							window.onbeforeprint = function(ev) {
								document.body.innerHTML = g_oBeforeBody;
							};
							window.print();
							location.reload();
						});
					});