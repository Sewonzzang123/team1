package com.my.maintest.common.paging;

public class SearchCriteria {
	
	
	private String searchType;
	private String searchTypeName;
	private String searchKeyword;
	
	public SearchCriteria() {
	}
	public SearchCriteria( String searchType,String searchKeyword ) {
			this.searchType = searchType;
		this.searchKeyword = searchKeyword;
	}
	
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + ", searchKeyword=" + searchKeyword + "]";
	}

	
	
	
	
	
	
}
