<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	
	
	
					<!-- 검색 -->
        <div class="search">
          <form action="">
            <select name="searchType" id="searchType">
              <option value="A"
                <c:if test="${pagingComponent.searchCriteria.searchType == 'A'}"> selected="selected" </c:if>>전체</option>
              <option value="TC"
                <c:if test="${pagingComponent.searchCriteria.searchType == 'TC'}"> selected="selected" </c:if>>제목+내용</option>
              <option value="T" 
                <c:if test="${pagingComponent.searchCriteria.searchType == 'T'}"> selected="selected" </c:if>>제목</option>
              <option value="C"
                <c:if test="${pagingComponent.searchCriteria.searchType == 'C'}"> selected="selected" </c:if>>내용</option>
              <option value="N"
                <c:if test="${pagingComponent.searchCriteria.searchType == 'N'}"> selected="selected" </c:if>>작성자</option>
            </select> 
                  </form>
            <input type="text" name="searchKeyword" id="searchKeyword"
              value="${pagingComponent.searchCriteria.searchKeyword}">
            <button type="button" id="searchBtn">검색</button>
          </div>