package com.neutti.webframe.core.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ItemVO {
	private Long id;
	private String category;
	private String type;
	private String value;
	private String name;
	private String source;
	private List<Map<String,Object>> info = new ArrayList<Map<String,Object>>();

	public ItemVO(Long id, String category, String value, String name) {
		this.id = id;
		this.category = category;
		this.value = value;
		this.name = name;
	}

	public ItemVO(Long id, String category, String value, String name, String type, String source) {
		this.id = id;
		this.category = category;
		this.type = type;
		this.value = value;
		this.name = name;
		this.source = source;
	}
	@Deprecated
	public ItemVO(Long id, String category, String type, String value, String name) {
		this.id = id;
		this.category = category;
		this.type = type;
		this.value = value;
		this.name = name;
	}
	public ItemVO(String value, String name) {
		this.value = value;
		this.name = name;
	}
	public ItemVO() {
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<Map<String,Object>> getInfo() {
		return info;
	}

	public void setInfo(List<Map<String,Object>> info) {
		this.info = info;
	}

	public void addInfo(Map<String, Object> info) {
		this.info.add(info);
	}


}
