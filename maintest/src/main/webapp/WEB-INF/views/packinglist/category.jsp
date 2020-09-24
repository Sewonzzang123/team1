<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<dt style="margin-top: 10px">카테고리</dt>
<div class="category_grid">
	<c:forEach items="#{categoryList }" var="itemCategoryVO">
		<dd>
			<button class="tab_menu_btn${itemCategoryVO.ca_num } tab_menu_btn"
				id="${itemCategoryVO.ca_num }" type="button">${itemCategoryVO.ca_name }</button>
			<i class="fas fa-chevron-up drop_btn"></i>
			<ul class="pd_item_list">
				<c:if
					test="${empty requestScope.listing and empty sessionScope.listing}"></c:if>
				<c:if test="${!empty requestScope.listing}">
					<c:forEach items="${listing }" var="listingVO">
						<c:if test="${listingVO.ca_num eq itemCategoryVO.ca_num }">
							<li class="row">
								<div>
									<input
										<c:if test="${listingVO.checked }">checked="${listingVO.checked }"
	</c:if>
										type="checkbox" onClick="check_f(this)">
									<div name="i_name" value="${listingVO.i_name }"
										class="item${listingVO.i_num } iname"
										selected="${listingVO.checked  }">${listingVO.i_name }</div>
									<input type="text" name="icount" class="icount"
										value="${listingVO.icount }" max="20"
										onfocus="getPreValue(this)" onchange="changeValue(this)" />
									<div class="edit_number">
										<i class="plusBtn fas fa-caret-up" onClick="plus(this)"></i> <i
											class="minusBtn fas fa-caret-down" onClick="minus(this)"></i>
									</div>
									<i class="fas fa-minus deleteItem" onClick="deletelist_f(this)">
									</i>
								</div> <input type="hidden" name="icategory"
								value="${listingVO.ca_num }" /> <input type="hidden"
								name="inum" value="${listingVO.i_num }" /><input type="hidden"
								name="i_name" value="${listingVO.i_name }" /><input
								type="hidden" name="checked" value="${listingVO.checked }" />
							</li>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${!empty sessionScope.listing}">
					<c:forEach items="${sessionScope.listing }" var="listingVO">
						<c:if test="${listingVO.ca_num eq itemCategoryVO.ca_num }">
							<li class="row">
								<div>
									<input
										<c:if test="${listingVO.checked }">checked="${listingVO.checked }"</c:if>
										type="checkbox" onClick="check_f(this)">
									<div name="i_name" value="${listingVO.i_name }"
										class="item${listingVO.i_num } iname"
										selected="${listingVO.checked  }">${listingVO.i_name }</div>
									<input type="text" name="icount" class="icount"
										value="${listingVO.icount }" max="20"
										onfocus="getPreValue(this)" onchange="changeValue(this)" />
									<div class="edit_number">
										<i class="plusBtn fas fa-caret-up" onClick="plus(this)"></i> <i
											class="minusBtn fas fa-caret-down" onClick="minus(this)"></i>
									</div>
									<i class="fas fa-minus deleteItem" onClick="deletelist_f(this)">
									</i>
								</div> <input type="hidden" name="icategory"
								value="${listingVO.ca_num }" /> <input type="hidden"
								name="inum" value="${listingVO.i_num }" /><input type="hidden"
								name="i_name" value="${listingVO.i_name }" /><input
								type="hidden" name="checked" value="${listingVO.checked }" />
							</li>
						</c:if>
					</c:forEach>
				</c:if>
			</ul>

		</dd>
	</c:forEach>
</div>


