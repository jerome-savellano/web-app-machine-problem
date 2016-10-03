package com.qbryx.dao;

import java.util.List;

import com.qbryx.domain.InventoryProduct;
import com.qbryx.domain.Product;

public interface ProductDao {

	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String categoryName);
	Product getProductByUpcCust(String upc);
	Product getProductByUpcMan(String upc);
	int getStock(String upc);
	
	boolean addProduct(Product product);
	boolean addProductStock(InventoryProduct inventoryProduct);
}
