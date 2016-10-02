package com.qbryx.dao;

import java.util.List;

import com.qbryx.domain.Product;

public interface ProductDao {

	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String categoryName);
	Product getProductByUpc(String upc);
	
	int getStock(String upc);
}
