package com.qbryx.domain;

import java.math.BigDecimal;

public class InventoryProduct extends Product {
	
	private int stock;

	public InventoryProduct(String upc, Category categoryId, String name, String description, BigDecimal price) {
		super(upc, categoryId, name, description, price);
		// TODO Auto-generated constructor stub
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
