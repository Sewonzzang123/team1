<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container category tab_menu_container">
	<dt>카테고리</dt>

	<c:forEach items="${categoryList }" var="itemCategoryVO">
		<dd>		
			<button class="tab_menu_btn${itemCategoryVO.ca_num } tab_menu_btn"
				id="${itemCategoryVO.ca_num }" type="button">${itemCategoryVO.ca_name }</button>
			<a href="#"><i class="fas fa-chevron-down"></i></a>
			<ul class="pd_item_list">
				<c:if test="${empty requestScope.listing}"></c:if>
				<c:if test="${!empty requestScope.listing}">
					<c:forEach items="${listing }" var="listingVO">
						<c:if
							test="${listingVO.CategoryVO.ca_num eq itemCategoryVO.ca_num }">
							<li class="row">
								<div>
									<input
										<c:if test="${listingVO.CHECKED }">checked="${listingVO.CHECKED }"</c:if>
										type="checkbox" onClick="check_f(this)">
									<div name="iname" value="${listingVO.itemVO.i_name }"
										class="item${listingVO.itemVO.i_num } iname"
										selected="${listingVO.CHECKED  }">${listingVO.itemVO.i_name }</div>
									<input type="text"  name="icount" class="icount" value="${listingVO.ICOUNT }"
										max="100" />
									<div class="edit_number">
										<i class="plusBtn fas fa-caret-up" onClick="plus(this)"></i>
										<i class="minusBtn fas fa-caret-down" onClick="minus(this)"></i>
									</div>
									<a href="#" class="deleteItem"><i class="fas fa-minus"
										onClick="deletelist_f(this)"> </i></a>
								</div> <input type="hidden" name="icategory"
								value="${listingVO.CategoryVO.ca_num }" /> <input type="hidden"
								name="inum" value="${listingVO.itemVO.i_num }" /><input
								type="hidden" name="iname" value="${listingVO.itemVO.i_name }" /><input
								type="hidden" name="checked" value="${listingVO.CHECKED }" />
							</li>
						</c:if>
					</c:forEach>

				</c:if>
			</ul>
		</dd>
	</c:forEach>
</div>
</div>
</form>


