<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
			<table>
				<thead>
					<tr>
						<td hidden="true">체크버튼</td>
						<td hidden="true">이름</td>
					</tr>
				</thead>
				<tbody>
				
					<c:if test="${empty requestScope.listing}">
						<c:forEach items="#{itemList }" var="itemVO">
							<tr class="tab_box tab_box${itemVO.itemCategoryVO.ca_num }">
								<td><i class="fas fa-minus"
										onClick="deletelist2_f(this)" style="display: none"></i> <i
										class="fas fa-plus" onClick="addlist_f(this)"></i></td>
								<td><div class="item${itemVO.i_num } iname" selected="false">${itemVO.i_name }</div></td>
							</tr>
						</c:forEach>
					</c:if>

					<c:if test="${!empty requestScope.listing}">
						<c:forEach items="#{itemList }" var="itemVO">							
										<tr class="tab_box tab_box${itemVO.itemCategoryVO.ca_num }">
											<td><i class="fas fa-minus"
													onClick="deletelist2_f(this)" style="display: none"></i> <i class="fas fa-plus"
													onClick="addlist_f(this)" ></i></td>
											<td><div class="iname item${itemVO.i_num }" selected="false">${itemVO.i_name }</div></td>
										</tr>									
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		

			<div class="list_buttons">
				<input type="button" id="additem" value="아이템 추가" /><span>|</span> 
				<input type="button" value="저장" 	id="saveBtn"/> <span>|</span>
				<input type="button" value="리스트 불러오기" id="loadBtn" /> <span>|</span> 
				<input type="button" value="PDF/인쇄" id="downloadBtn" />
			</div>


