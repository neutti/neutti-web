package com.neutti.webframe.core.bean;



import com.neutti.webframe.jpa.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 게시판
 * @author PIG
 *
 */

public class BoardVO extends PagingVO{
	/**
	 * 결과목록
	 */
	private List<?> result = new ArrayList<Object>();
	/**
	 * 추가 결과목록
	 */
	private List<?> addResult = new ArrayList<Object>();
	/**
	 * 검색조건 엔티티 (페이징정보포함)
	 */
	private BaseEntity queryEntity;
	/**
	 * 검색조건 맵
	 */
	private Map<String, Object> queryMap;
	/**
	 * 페이지타입정보
	 */
	private String type;
	/**
	 * 총결과수
	 */
	private long total;
    /**
     * 최상위 검색 결과수
     */
	private long mainTotal;
	
	public List<?> getResult() {
		return result;
	}
	public void setResult(List<?> result) {
		this.result = result;
	}
	public List<?> getAddResult() {
		return addResult;
	}
	public void setAddResult(List<?> addResult) {
		this.addResult = addResult;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BaseEntity getQueryEntity() {
		return queryEntity;
	}
	public void setQueryEntity(BaseEntity queryEntity) {
		this.queryEntity = queryEntity;
	}
	public Map<String, Object> getQueryMap() {
		return queryMap;
	}
	public void setQueryMap(Map<String, Object> queryMap) {
		this.queryMap = queryMap;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public long getMainTotal() {
		return mainTotal;
	}
	public void setMainTotal(long mainTotal) {
		this.mainTotal = mainTotal;
	}
}
