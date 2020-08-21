<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form target="_blank" method="post"  name="form">
<div id="wrapper">
	<div class="container additem tab_box_container">
		<table>
			<thead>
				<tr>
					<td hidden="true">체크버튼</td>
					<td hidden="true">이름</td>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="#{itemList }" var="itemVO">
					<tr class="tab_box tab_box${itemVO.itemCategoryVO.ca_num }">
						<td>
						<a href="#">
						<i class="fas fa-minus" onClick="deletelist2_f(this)" style="display:none"></i>
						<i class="fas fa-plus" onClick="addlist_f(this)"></i></a>
						</td>
						<td><div class="item${itemVO.i_num }" selected="false">${itemVO.i_name }</div></td>
					</tr>
				</c:forEach>
		</table>
		</tbody>
	
		<div class="buttons">
				<input type="button" id="additem" value="아이템 추가"/><span>|</span>
				<input type="button" value="저장" id="saveBtn"/> <span>|</span> 
				<input type="submit" value="리스트 불러오기" formaction="${contextPath }/itemlist/viewListForm"/> <span>|</span>
					<a href="${contextPath }/itemlist/"> 인쇄</a>
		</div>

	</div>