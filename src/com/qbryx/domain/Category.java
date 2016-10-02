package com.qbryx.domain;

public class Category {
	
	private String categoryId;
	private String name;
	
	public Category(String categoryId, String name) {
		super();
		this.categoryId = categoryId;
		this.name = name;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
