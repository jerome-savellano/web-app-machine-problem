package com.qbryx.domain;

import java.math.BigDecimal;

public class InventoryProduct extends Product {
	
	private Product product;
	private int stock;

	public InventoryProduct(Product product) {
		super(product.getUpc(), product.getCategory(), product.getName(), product.getDescription(), product.getPrice());
		// TODO Auto-generated constructor stub
	}
	
	public InventoryProduct(Product product, int stock) {
		super(product.getUpc(), product.getCategory(), product.getName(), product.getDescription(), product.getPrice());
		this.stock = stock;
	}
	
	public InventoryProduct(String upc, int stock){
		setUpc(upc);
		this.stock = stock;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
