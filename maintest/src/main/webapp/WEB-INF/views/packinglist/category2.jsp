<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<form action="${contextPath }/itemlist/itemValue" method="get"  name="saveFrm">
		<div class="container category tab_menu_container">
			<dt>카테고리</dt>	
			<c:forEach items="${categoryList }" var="itemCategoryVO">
				<dd>
					<button class ="tab_menu_btn${itemCategoryVO.ca_num } tab_menu_btn" id="${itemCategoryVO.ca_num }"type="button">${itemCategoryVO.ca_name }</button>
					<ul>
					</ul>
				</dd>
			</c:forEach>
		</div>
	</div>
	</form>


