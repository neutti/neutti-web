package com.neutti.webframe.core.bean;



public class PagingVO {
	/**
	 * 페이지
	 */
	private int pageNum = 1; // 기본값지정
	/**
	 * 현재페이지 전체건수
	 */
	private Integer pageMax = 10;
	/**
	 * 검색어
	 */
	private String query;

	private String searchType;
	private String searchOrganId;
	private String searchPlaceId;
	private String searchPositionId;

	public int getPageFirst() {
		return (this.pageNum - 1) * this.pageMax;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageMax() {
		return pageMax;
	}

	public void setPageMax(Integer pageMax) {
		this.pageMax = pageMax;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getSearchOrganId() {
		return searchOrganId;
	}

	public void setSearchOrganId(String searchOrganId) {
		this.searchOrganId = searchOrganId;
	}

	public String getSearchPlaceId() {
		return searchPlaceId;
	}

	public void setSearchPlaceId(String searchPlaceId) {
		this.searchPlaceId = searchPlaceId;
	}

	public String getSearchPositionId() {
		return searchPositionId;
	}

	public void setSearchPositionId(String searchPositionId) {
		this.searchPositionId = searchPositionId;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}



}
