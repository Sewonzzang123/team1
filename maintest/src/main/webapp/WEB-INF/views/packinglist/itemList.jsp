<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
			<div class="default_table">		
					<c:if test="${empty requestScope.listing}">
						<c:forEach items="#{itemList }" var="itemVO">
							<div class="tab_box tab_box${itemVO.itemCategoryVO.ca_num }">
								<div class="add_or_delete">
									<i class="fas fa-minus" onClick="deletelist2_f(this)" style="display: none"></i> 
									<i class="fas fa-plus" onClick="addlist_f(this)"></i>
								</div>
								<div>
								<div class="item${itemVO.i_num } iname" selected="false">${itemVO.i_name }</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${not empty requestScope.listing}">
						<c:forEach items="#{itemList }" var="itemVO">
							<div class="tab_box tab_box${itemVO.itemCategoryVO.ca_num }">
								<div class="add_or_delete">
									<i class="fas fa-minus" onClick="deletelist2_f(this)" style="display: none"></i> 
									<i class="fas fa-plus" onClick="addlist_f(this)"></i>
								</div>
								<div class="item${itemVO.i_num } iname" selected="false">${itemVO.i_name }</div>
							</div>
						</c:forEach>
					</c:if>
			</div>
		

			<div class="list_buttons">
				<input type="button" id="additem" value="아이템 추가" /><span>|</span> 
				<input type="button" value="저장" 	id="saveBtn"/> <span>|</span>
				<input type="button" value="리스트 불러오기" id="loadBtn" /> <span>|</span> 
				<input type="button" value="PDF/인쇄" id="downloadBtn" /> <span>|</span> 
				<input type="button" value="초기화" id="resetBtn" />
			</div>


